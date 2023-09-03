package ru.mikheev.kirill.hw13bank.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mikheev.kirill.hw13bank.dto.UserDto;

/**
 * Сообщение на смену пин кода для пользователя
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeUserPinRequest {
    private UserDto user; // Данные по пользователю
    private String newPin; // Новый пин
}
