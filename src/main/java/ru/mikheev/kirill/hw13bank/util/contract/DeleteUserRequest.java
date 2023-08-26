package ru.mikheev.kirill.hw13bank.util.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteUserRequest {
    private String name;
    private String cardNumber;
    private String pin;
}
