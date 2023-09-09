package ru.mikheev.kirill.hw13bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mikheev.kirill.hw13bank.exception.internal.UnknownDenominationException;

import java.util.HashMap;
import java.util.Map;

/**
 * Set of banknotes with different denomination
 */
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

    public MoneyBundleDto(Map<Integer, Integer> moneyBundle) {
        Integer currCounter = moneyBundle.remove(5000);
        counter5000 = currCounter == null ? 0 : currCounter;
        currCounter = moneyBundle.remove(1000);
        counter1000 = currCounter == null ? 0 : currCounter;
        currCounter = moneyBundle.remove(500);
        counter500 = currCounter == null ? 0 : currCounter;
        currCounter = moneyBundle.remove(100);
        counter100 = currCounter == null ? 0 : currCounter;
        currCounter = moneyBundle.remove(50);
        counter50 = currCounter == null ? 0 : currCounter;
        currCounter = moneyBundle.remove(10);
        counter10 = currCounter == null ? 0 : currCounter;
        if (!moneyBundle.isEmpty()) {
            throw new UnknownDenominationException(moneyBundle);
        }
    }

    public Map<Integer, Integer> toMap() {
        Map<Integer, Integer> result = new HashMap<>(6, 1);
        if(counter5000 > 0) result.put(5000, counter5000);
        if(counter1000 > 0) result.put(1000, counter1000);
        if(counter500 > 0) result.put(500, counter500);
        if(counter100 > 0) result.put(100, counter100);
        if(counter50 > 0) result.put(50, counter50);
        if(counter10 > 0) result.put(10, counter10);
        return result;
    }
}
