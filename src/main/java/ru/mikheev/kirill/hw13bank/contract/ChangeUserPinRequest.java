package ru.mikheev.kirill.hw13bank.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mikheev.kirill.hw13bank.dto.UserDto;

/**
 * Pin code change request
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeUserPinRequest {
    private UserDto user; // User data
    private String newPin; // new pin
}
