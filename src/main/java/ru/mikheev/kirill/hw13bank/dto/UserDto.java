package ru.mikheev.kirill.hw13bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User information dto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String name; // Username
    private String cardNumber; // User card number
    private String pin; //User card pin
}
