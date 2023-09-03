package ru.mikheev.kirill.hw13bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Представление сообщения с информацией о пользователе
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String name; // Имя пользователя
    private String cardNumber; // Номер карты пользователя
    private String pin; // Пин код от карты пользователя
}
