package ru.mikheev.kirill.hw13bank.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mikheev.kirill.hw13bank.dto.MoneyBundleDto;
import ru.mikheev.kirill.hw13bank.dto.UserDto;

/**
 * Account replenishment request
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PutMoneyRequest {
    private UserDto user; // User data
    private MoneyBundleDto money; // Set of banknotes to replenishment
}
