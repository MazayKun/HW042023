package ru.mikheev.kirill.hw13bank.logic.service;

import ru.mikheev.kirill.hw13bank.logic.container.MoneyAccount;
import ru.mikheev.kirill.hw13bank.logic.storage.CustomStorage;
import ru.mikheev.kirill.hw13bank.logic.container.User;
import ru.mikheev.kirill.hw13bank.util.dto.BalanceDto;
import ru.mikheev.kirill.hw13bank.util.dto.MoneyBundleDto;
import ru.mikheev.kirill.hw13bank.util.dto.UserDto;
import ru.mikheev.kirill.hw13bank.util.exception.service.WrongCardNumberException;
import ru.mikheev.kirill.hw13bank.util.exception.service.WrongPinException;

import java.util.function.Supplier;

import static ru.mikheev.kirill.hw13bank.util.converter.DtoConverter.toDto;
import static ru.mikheev.kirill.hw13bank.util.converter.DtoConverter.toModel;

public class ATMService {

    private final CustomStorage<String, User> userStorage = new CustomStorage<>("User");
    private final CustomStorage<User, MoneyAccount> accountStorage = new CustomStorage<>("Account");

    public UserDto registerNewClient(String userName) {
        return fetchTransactional(() -> {
            User user = new User(userName);
            userStorage.add(userName, user);
            accountStorage.add(user, new MoneyAccount());
            return toDto(user);
        });
    }

    public void changePin(UserDto userDto, String newPin) {
        executeTransactional(() -> {
            User user = userStorage.get(userDto.getName());
            validateCredentials(user, userDto);
            user.setPin(newPin);
        });
    }

    public MoneyBundleDto unregisterUser(UserDto userDto) {
        return fetchTransactional(() -> {
            User user = userStorage.get(userDto.getName());
            validateCredentials(user, userDto);
            userStorage.remove(user.getName());
            return toDto(
                    accountStorage.remove(user)
                            .getAllMoney()
            );
        });
    }

    public void putMoney(UserDto userDto, MoneyBundleDto moneyBundleDto) {
        executeTransactional(() ->
                getAccount(userDto)
                        .loadBanknotes(toModel(moneyBundleDto))
        );
    }

    public MoneyBundleDto getMoney(UserDto userDto, int amount) {
        return fetchTransactional(() -> toDto(
                getAccount(userDto).getMoney(amount)
        ));
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

    private void executeTransactional(Runnable runnable) {
        userStorage.fixState();
        accountStorage.fixState();
        try {

            runnable.run();

            userStorage.commitState();
            accountStorage.commitState();
        } catch (Exception e) {
            userStorage.rollbackState();
            accountStorage.rollbackState();
            throw e;
        }
    }

    private <T> T fetchTransactional(Supplier<T> supplier) {
        userStorage.fixState();
        accountStorage.fixState();
        try {

            T result = supplier.get();

            userStorage.commitState();
            accountStorage.commitState();

            return result;

        } catch (Exception e) {
            userStorage.rollbackState();
            accountStorage.rollbackState();
            throw e;
        }
    }
}
