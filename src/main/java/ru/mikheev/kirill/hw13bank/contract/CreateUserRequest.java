package ru.mikheev.kirill.hw13bank.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mikheev.kirill.hw13bank.dto.UserDto;

/**
 * New user creation request
 *
 * Response - {@link UserDto}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    private String userName; // New user name
}
