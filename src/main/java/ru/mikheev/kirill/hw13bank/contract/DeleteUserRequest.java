package ru.mikheev.kirill.hw13bank.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mikheev.kirill.hw13bank.dto.MoneyBundleDto;

/**
 * User removal request
 *
 * Response - {@link MoneyBundleDto}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteUserRequest {
    private String name; // username
    private String cardNumber; // User card number
    private String pin; // User card pin
}
