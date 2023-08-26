package ru.mikheev.kirill.hw13bank.util.converter;

import ru.mikheev.kirill.hw13bank.util.contract.DeleteUserRequest;
import ru.mikheev.kirill.hw13bank.util.dto.UserDto;

public class RequestConverter {

    public static UserDto toDto(DeleteUserRequest request) {
        return new UserDto(
                request.getName(),
                request.getCardNumber(),
                request.getPin()
        );
    }
}
