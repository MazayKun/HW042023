package ru.mikheev.kirill.hw13bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User account balance request result
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BalanceDto {
    private int balance; // Current balance of card
}
