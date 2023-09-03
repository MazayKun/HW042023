package ru.mikheev.kirill.hw13bank.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mikheev.kirill.hw13bank.dto.MoneyBundleDto;
import ru.mikheev.kirill.hw13bank.dto.UserDto;

/**
 * Запрос на пополнение счета
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PutMoneyRequest {
    private UserDto user; // Информация о пользователе
    private MoneyBundleDto money; // Набор купюр, которые были внесены
}
