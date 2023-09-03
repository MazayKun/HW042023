package ru.mikheev.kirill.hw13bank.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mikheev.kirill.hw13bank.dto.UserDto;

/**
 * Запрос на создание в системе нового пользователя
 *
 * Ответ - {@link UserDto}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    private String userName; // Имя пользователя
}
