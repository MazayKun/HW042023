package ru.mikheev.kirill.hw13bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Результат запроса баланса для пользователя
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BalanceDto {
    private int balance; // Текущий баланс карты
}
