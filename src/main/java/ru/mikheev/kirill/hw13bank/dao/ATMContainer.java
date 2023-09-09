package ru.mikheev.kirill.hw13bank.dao;

import ru.mikheev.kirill.hw13bank.dao.general.Transactional;
import ru.mikheev.kirill.hw13bank.exception.internal.UnknownDenominationException;

import java.util.Arrays;

/**
 * Banknotes storage
 */
public class ATMContainer implements Transactional {

    static final int BANKNOTE_5000_COUNTER_INDEX = 0;
    static final int BANKNOTE_1000_COUNTER_INDEX = 1;
    static final int BANKNOTE_500_COUNTER_INDEX = 2;
    static final int BANKNOTE_100_COUNTER_INDEX = 3;
    static final int BANKNOTE_50_COUNTER_INDEX = 4;
    static final int BANKNOTE_10_COUNTER_INDEX = 5;

    public static final int BANKNOTE_5000 = 5000;
    public static final int BANKNOTE_1000 = 1000;
    public static final int BANKNOTE_500 = 500;
    public static final int BANKNOTE_100 = 100;
    public static final int BANKNOTE_50 = 50;
    public static final int BANKNOTE_10 = 10;

    static final int[] BANKNOTE_DENOMINATIONS = {BANKNOTE_5000, BANKNOTE_1000, BANKNOTE_500, BANKNOTE_100, BANKNOTE_50, BANKNOTE_10};

    private final int[] banknotesCounter = new int[6];
    private int[] fixedState = null;


    ATMContainer() {}

    public void loadBanknotesByDenomination(int denomination, int counter) {
        this.banknotesCounter[getBanknoteIndex(denomination)] += counter;
    }

    public int takeAllBanknotesByDenomination(int denomination) {
        int index = getBanknoteIndex(denomination);
        int counter = banknotesCounter[index];
        banknotesCounter[index] = 0;
        return counter;
    }

    public int getMaxPossibleBanknoteNumberByDenomination(int denomination, int requiredCounter) {
        int index = getBanknoteIndex(denomination);
        if(banknotesCounter[index] > requiredCounter) {
            banknotesCounter[index] -= requiredCounter;
            return requiredCounter;
        }
        int counter = banknotesCounter[index];
        banknotesCounter[index] = 0;
        return counter;
    }

    public int getTotalAmount() {
        return banknotesCounter[BANKNOTE_5000_COUNTER_INDEX] * 5000 +
                banknotesCounter[BANKNOTE_1000_COUNTER_INDEX] * 1000 +
                banknotesCounter[BANKNOTE_500_COUNTER_INDEX] * 500 +
                banknotesCounter[BANKNOTE_100_COUNTER_INDEX] * 100 +
                banknotesCounter[BANKNOTE_50_COUNTER_INDEX] * 50 +
                banknotesCounter[BANKNOTE_10_COUNTER_INDEX] * 10;

    }

    private int getBanknoteIndex(int denomination) {
        return switch (denomination) {
            case BANKNOTE_5000 -> BANKNOTE_5000_COUNTER_INDEX;
            case BANKNOTE_1000 -> BANKNOTE_1000_COUNTER_INDEX;
            case BANKNOTE_500 -> BANKNOTE_500_COUNTER_INDEX;
            case BANKNOTE_100 -> BANKNOTE_100_COUNTER_INDEX;
            case BANKNOTE_50 -> BANKNOTE_50_COUNTER_INDEX;
            case BANKNOTE_10 -> BANKNOTE_10_COUNTER_INDEX;
            default -> throw new UnknownDenominationException(denomination);
        };
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int index;
        for (int banknoteDenomination : BANKNOTE_DENOMINATIONS) {
            index = getBanknoteIndex(banknoteDenomination);
            if (banknotesCounter[index] > 0) {
                result.append(banknoteDenomination)
                        .append(" : ")
                        .append(banknotesCounter[index])
                        .append("\n");
            }
        }
        if (result.isEmpty()) {
            return "Empty ATMContainer\n";
        } else {
            return result
                    .insert(0, '\n')
                    .insert(0, "ATMContainer")
                    .toString();
        }
    }

    @Override
    public void fixState() {
        if (fixedState != null) {
            System.arraycopy(fixedState, 0, banknotesCounter, 0, fixedState.length);
            return;
        }
        fixedState = Arrays.copyOf(banknotesCounter, banknotesCounter.length);
    }

    @Override
    public void commitState() {
        fixedState = null;
    }

    @Override
    public void rollbackState() {
        System.arraycopy(fixedState, 0, banknotesCounter, 0, fixedState.length);
        fixedState = null;
    }
}
