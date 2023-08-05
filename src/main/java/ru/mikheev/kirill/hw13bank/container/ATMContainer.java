package ru.mikheev.kirill.hw13bank.container;

import ru.mikheev.kirill.hw13bank.exception.ATMContainerStateException;
import ru.mikheev.kirill.hw13bank.exception.NotEnoughBanknotesException;

import java.util.Arrays;

public class ATMContainer extends BanknoteHolder {

    private int[] fixedState = null;

    public ATMContainer() {
        super(0,0,0,0,0,0);
    }

    public void loadBanknotes(BanknoteHolder banknoteHolder) {
        this.banknotesCounter[BANKNOTE_5000_COUNTER_INDEX] += banknoteHolder.banknotesCounter[BANKNOTE_5000_COUNTER_INDEX];
        this.banknotesCounter[BANKNOTE_1000_COUNTER_INDEX] += banknoteHolder.banknotesCounter[BANKNOTE_1000_COUNTER_INDEX];
        this.banknotesCounter[BANKNOTE_500_COUNTER_INDEX] += banknoteHolder.banknotesCounter[BANKNOTE_500_COUNTER_INDEX];
        this.banknotesCounter[BANKNOTE_100_COUNTER_INDEX] += banknoteHolder.banknotesCounter[BANKNOTE_100_COUNTER_INDEX];
        this.banknotesCounter[BANKNOTE_50_COUNTER_INDEX] += banknoteHolder.banknotesCounter[BANKNOTE_50_COUNTER_INDEX];
        this.banknotesCounter[BANKNOTE_10_COUNTER_INDEX] += banknoteHolder.banknotesCounter[BANKNOTE_10_COUNTER_INDEX];
    }

    public void take5000Banknotes(int numberOfBanknotes) {
        if(banknotesCounter[BANKNOTE_5000_COUNTER_INDEX] < numberOfBanknotes) {
            throw new NotEnoughBanknotesException("5000", numberOfBanknotes, banknotesCounter[BANKNOTE_5000_COUNTER_INDEX]);
        }
        banknotesCounter[BANKNOTE_5000_COUNTER_INDEX] -= numberOfBanknotes;
    }

    public void take1000Banknotes(int numberOfBanknotes) {
        if(banknotesCounter[BANKNOTE_1000_COUNTER_INDEX] < numberOfBanknotes) {
            throw new NotEnoughBanknotesException("1000", numberOfBanknotes, banknotesCounter[BANKNOTE_1000_COUNTER_INDEX]);
        }
        banknotesCounter[BANKNOTE_1000_COUNTER_INDEX] -= numberOfBanknotes;
    }

    public void take500Banknotes(int numberOfBanknotes) {
        if(banknotesCounter[BANKNOTE_500_COUNTER_INDEX] < numberOfBanknotes) {
            throw new NotEnoughBanknotesException("500", numberOfBanknotes, banknotesCounter[BANKNOTE_500_COUNTER_INDEX]);
        }
        banknotesCounter[BANKNOTE_500_COUNTER_INDEX] -= numberOfBanknotes;
    }

    public void take100Banknotes(int numberOfBanknotes) {
        if(banknotesCounter[BANKNOTE_100_COUNTER_INDEX] < numberOfBanknotes) {
            throw new NotEnoughBanknotesException("100", numberOfBanknotes, banknotesCounter[BANKNOTE_100_COUNTER_INDEX]);
        }
        banknotesCounter[BANKNOTE_100_COUNTER_INDEX] -= numberOfBanknotes;
    }

    public void take50Banknotes(int numberOfBanknotes) {
        if(banknotesCounter[BANKNOTE_50_COUNTER_INDEX] < numberOfBanknotes) {
            throw new NotEnoughBanknotesException("50", numberOfBanknotes, banknotesCounter[BANKNOTE_50_COUNTER_INDEX]);
        }
        banknotesCounter[BANKNOTE_50_COUNTER_INDEX] -= numberOfBanknotes;
    }

    public void take10Banknotes(int numberOfBanknotes) {
        if(banknotesCounter[BANKNOTE_10_COUNTER_INDEX] < numberOfBanknotes) {
            throw new NotEnoughBanknotesException("10", numberOfBanknotes, banknotesCounter[BANKNOTE_10_COUNTER_INDEX]);
        }
        banknotesCounter[BANKNOTE_10_COUNTER_INDEX] -= numberOfBanknotes;
    }

    public void fixState() {
        if(fixedState != null) {
            throw new ATMContainerStateException("Attempt to overwrite state. Previous state " + Arrays.toString(fixedState));
        }
        fixedState = Arrays.copyOf(banknotesCounter, banknotesCounter.length);
    }

    public void commitNewState() {
        fixedState = null;
    }

    public void rollbackState() {
        for(int i = 0; i < fixedState.length; i++) {
            banknotesCounter[i] = fixedState[i];
        }
    }

    @Override
    public String toString() {
        return toStringByName("ATMContainer");
    }
}
