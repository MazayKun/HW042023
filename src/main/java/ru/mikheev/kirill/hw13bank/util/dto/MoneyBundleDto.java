package ru.mikheev.kirill.hw13bank.util.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoneyBundleDto {
    private int counter5000;
    private int counter1000;
    private int counter500;
    private int counter100;
    private int counter50;
    private int counter10;
}
