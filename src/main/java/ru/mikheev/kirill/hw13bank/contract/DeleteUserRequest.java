package ru.mikheev.kirill.hw13bank.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mikheev.kirill.hw13bank.dto.MoneyBundleDto;

/**
 * Запрос на удаление пользователя
 *
 * Ответ - {@link MoneyBundleDto}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteUserRequest {
    private String name; // Имя пользователя
    private String cardNumber; // Номер карты пользователя
    private String pin; // Пин код от карты пользователя
}
