package ru.mikheev.kirill.hw13bank.util.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mikheev.kirill.hw13bank.util.dto.UserDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeUserPinRequest {
    private UserDto user;
    private String newPin;
}
