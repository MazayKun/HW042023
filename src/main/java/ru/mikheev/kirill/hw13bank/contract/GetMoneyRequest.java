package ru.mikheev.kirill.hw13bank.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mikheev.kirill.hw13bank.dto.MoneyBundleDto;
import ru.mikheev.kirill.hw13bank.dto.UserDto;

/**
 * Запрос на снятие денег
 *
 * Ответ - {@link MoneyBundleDto}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetMoneyRequest {
    private UserDto user; // Информация о пользователе
    private int amount; // Количество снимаемых денег
}
