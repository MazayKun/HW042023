package ru.mikheev.kirill.hw13bank.util.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mikheev.kirill.hw13bank.util.dto.MoneyBundleDto;
import ru.mikheev.kirill.hw13bank.util.dto.UserDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PutMoneyRequest {
    private UserDto user;
    private MoneyBundleDto money;
}
