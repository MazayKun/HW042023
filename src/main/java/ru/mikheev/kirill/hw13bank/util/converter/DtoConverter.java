package ru.mikheev.kirill.hw13bank.util.converter;

import ru.mikheev.kirill.hw13bank.logic.container.MoneyBundle;
import ru.mikheev.kirill.hw13bank.logic.container.User;
import ru.mikheev.kirill.hw13bank.util.dto.MoneyBundleDto;
import ru.mikheev.kirill.hw13bank.util.dto.UserDto;

public class DtoConverter {

    public static MoneyBundle toModel(MoneyBundleDto moneyBundleDto) {
        var moneyBundleBuilder = MoneyBundle.builder();
        moneyBundleBuilder.banknote5000Counter(moneyBundleDto.getCounter5000());
        moneyBundleBuilder.banknote1000Counter(moneyBundleDto.getCounter1000());
        moneyBundleBuilder.banknote500Counter(moneyBundleDto.getCounter500());
        moneyBundleBuilder.banknote100Counter(moneyBundleDto.getCounter100());
        moneyBundleBuilder.banknote50Counter(moneyBundleDto.getCounter50());
        moneyBundleBuilder.banknote10Counter(moneyBundleDto.getCounter10());
        return moneyBundleBuilder.build();
    }

    public static MoneyBundleDto toDto(MoneyBundle moneyBundle) {
        return new MoneyBundleDto(
                moneyBundle.get5000BanknotesCount(),
                moneyBundle.get1000BanknotesCount(),
                moneyBundle.get500BanknotesCount(),
                moneyBundle.get100BanknotesCount(),
                moneyBundle.get50BanknotesCount(),
                moneyBundle.get10BanknotesCount()
        );
    }

    public static UserDto toDto(User user) {
        return new UserDto(
                user.getName(),
                user.getCardNumber(),
                user.getPin()
        );
    }
}
