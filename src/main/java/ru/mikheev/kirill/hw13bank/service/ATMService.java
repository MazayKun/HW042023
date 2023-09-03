package ru.mikheev.kirill.hw13bank.service;

import ru.mikheev.kirill.hw13bank.dao.MoneyAccount;
import ru.mikheev.kirill.hw13bank.dao.User;
import ru.mikheev.kirill.hw13bank.dao.general.CustomStorage;
import ru.mikheev.kirill.hw13bank.dao.general.Transactional;
import ru.mikheev.kirill.hw13bank.dto.BalanceDto;
import ru.mikheev.kirill.hw13bank.dto.MoneyBundleDto;
import ru.mikheev.kirill.hw13bank.dto.UserDto;
import ru.mikheev.kirill.hw13bank.exception.service.WrongCardNumberException;
import ru.mikheev.kirill.hw13bank.exception.service.WrongPinException;

import java.util.function.Supplier;

/**
 * Сервис для работы с пользвоателями и их аккаунтами в системе
 */
public class ATMService {

    private final CustomStorage<String, User> userStorage = new CustomStorage<>("User");
    private final CustomStorage<User, MoneyAccount> accountStorage = new CustomStorage<>("Account");

    public UserDto registerNewClient(String userName) {
        return fetchTransactional(() -> {
            User user = new User(userName);
            userStorage.add(userName, user);
            accountStorage.add(user, new MoneyAccount());
            return new UserDto(
                    user.getName(),
                    user.getCardNumber(),
                    user.getPin()
            );
        }, userStorage, accountStorage);
    }

    public void changePin(UserDto userDto, String newPin) {
        executeTransactional(() -> {
            User user = userStorage.get(userDto.getName());
            validateCredentials(user, userDto);
            user.setPin(newPin);
        }, userStorage);
    }

    public MoneyBundleDto unregisterUser(UserDto userDto) {
        return fetchTransactional(() -> {
            User user = userStorage.get(userDto.getName());
            validateCredentials(user, userDto);
            userStorage.remove(user.getName());
            return new MoneyBundleDto(
                    accountStorage.remove(user)
                            .getAllMoney()
            );
        }, userStorage, accountStorage);
    }

    public void putMoney(UserDto userDto, MoneyBundleDto moneyBundleDto) {
        executeTransactional(() ->
                getAccount(userDto)
                        .loadBanknotes(moneyBundleDto.toMap())
        , userStorage, accountStorage);
    }

    public MoneyBundleDto getMoney(UserDto userDto, int amount) {
        return fetchTransactional(() -> new MoneyBundleDto(
                getAccount(userDto).getMoney(amount)
        ), userStorage, accountStorage);
    }

    public BalanceDto getBalance(UserDto userDto) {
        return new BalanceDto(
                getAccount(userDto)
                        .getTotalAmount()
        );
    }

    private MoneyAccount getAccount(UserDto userDto) {
        User user = userStorage.get(userDto.getName());
        validateCredentials(user, userDto);
        return accountStorage.get(user);
    }

    private void validateCredentials(User user, UserDto userDto) {
        if (!user.getCardNumber().equals(userDto.getCardNumber())) throw new WrongCardNumberException();
        if (!user.getPin().equals(userDto.getPin())) throw new WrongPinException();
    }

    private void executeTransactional(Runnable runnable, Transactional... storages) {
        for(int i = 0; i < storages.length; i++) {
            storages[i].fixState();
        }
        try {

            runnable.run();

            for(int i = 0; i < storages.length; i++) {
                storages[i].commitState();
            }
        } catch (Exception e) {
            for(int i = 0; i < storages.length; i++) {
                storages[i].rollbackState();
            }
            throw e;
        }
    }

    private <T> T fetchTransactional(Supplier<T> supplier, Transactional... storages) {
        for(int i = 0; i < storages.length; i++) {
            storages[i].fixState();
        }
        try {

            T result = supplier.get();

            for(int i = 0; i < storages.length; i++) {
                storages[i].commitState();
            }

            return result;

        } catch (Exception e) {
            for(int i = 0; i < storages.length; i++) {
                storages[i].rollbackState();
            }
            throw e;
        }
    }
}
