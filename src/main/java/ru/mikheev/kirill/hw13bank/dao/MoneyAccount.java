package ru.mikheev.kirill.hw13bank.dao;

import ru.mikheev.kirill.hw13bank.dao.general.Transactional;
import ru.mikheev.kirill.hw13bank.exception.internal.IllegalATMUsageException;
import ru.mikheev.kirill.hw13bank.exception.internal.NoMatchingBanknotesException;
import ru.mikheev.kirill.hw13bank.exception.internal.NotEnoughMoneyInATMException;

import java.util.HashMap;
import java.util.Map;

import static ru.mikheev.kirill.hw13bank.dao.ATMContainer.BANKNOTE_DENOMINATIONS;

/**
 * User money storage
 */
public class MoneyAccount implements Transactional {

    private final ATMContainer atmContainer = new ATMContainer();

    public void loadBanknotes(Map<Integer, Integer> moneyBundle) {
        moneyBundle.forEach(atmContainer::loadBanknotesByDenomination);
    }

    public int getTotalAmount() {
        return atmContainer.getTotalAmount();
    }

    public Map<Integer, Integer> getMoney(int requiredAmount) {
        if (requiredAmount <= 0) {
            throw new IllegalATMUsageException("Attempt to receive a negative or zero amount of money");
        }
        if (requiredAmount % 10 != 0) {
            throw new IllegalATMUsageException("Minimal banknote denomination - 10");
        }
        int totalAmount = atmContainer.getTotalAmount();
        if (atmContainer.getTotalAmount() < requiredAmount) {
            throw new NotEnoughMoneyInATMException(requiredAmount, totalAmount);
        }

        Map<Integer, Integer> result = new HashMap<>(6, 1);

        int remainingRequiredAmount = requiredAmount;
        int currentBanknoteRequirements;
        for(int denomination : BANKNOTE_DENOMINATIONS) {
            currentBanknoteRequirements = remainingRequiredAmount / denomination;
            int maxPossible = atmContainer.getMaxPossibleBanknoteNumberByDenomination(denomination, currentBanknoteRequirements);
            if(maxPossible != 0) {
                result.put(denomination, maxPossible);
                remainingRequiredAmount -= denomination * maxPossible;
            }
            if(remainingRequiredAmount <= 0) break;
        }

        if (remainingRequiredAmount != 0) {
            throw new NoMatchingBanknotesException(requiredAmount, atmContainer);
        } else {
            return result;
        }
    }

    public Map<Integer, Integer> getAllMoney() {
        Map<Integer, Integer> result = new HashMap<>(6, 1);
        for(int denomination : BANKNOTE_DENOMINATIONS) {
            result.put(denomination, atmContainer.takeAllBanknotesByDenomination(denomination));
        }
        return result;
    }

    @Override
    public void fixState() {
        atmContainer.fixState();
    }

    @Override
    public void commitState() {
        atmContainer.commitState();
    }

    @Override
    public void rollbackState() {
        atmContainer.rollbackState();
    }
}
