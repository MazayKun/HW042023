package ru.mikheev.kirill.hw13bank;

import ru.mikheev.kirill.hw13bank.container.ATMContainer;
import ru.mikheev.kirill.hw13bank.container.BanknoteHolder;
import ru.mikheev.kirill.hw13bank.container.MoneyBundle;
import ru.mikheev.kirill.hw13bank.container.MoneyBundleBuilder;
import ru.mikheev.kirill.hw13bank.exception.IllegalATMUsageException;
import ru.mikheev.kirill.hw13bank.exception.NoMatchingBanknotesException;
import ru.mikheev.kirill.hw13bank.exception.NotEnoughMoneyInATMException;

public class ATM {

    private ATMContainer atmContainer = new ATMContainer();

    public void loadBanknotes(BanknoteHolder banknoteHolder) {
        atmContainer.loadBanknotes(banknoteHolder);
    }

    public int getTotalAmount() {
        return atmContainer.getTotalAmount();
    }

    public MoneyBundle getMoney(int requiredAmount) {
        if(requiredAmount <= 0) {
            throw new IllegalATMUsageException("Attempt to receive a negative or zero amount of money");
        }
        if(requiredAmount % 10 != 0) {
            throw new IllegalATMUsageException("Minimal banknote denomination - 10");
        }
        int totalAmount = atmContainer.getTotalAmount();
        if(atmContainer.getTotalAmount() < requiredAmount) {
            throw new NotEnoughMoneyInATMException(requiredAmount, totalAmount);
        }

        atmContainer.fixState();
        MoneyBundleBuilder moneyBundleBuilder = MoneyBundle.builder();

        int requiredAmountCopy = requiredAmount;
        int currentBanknoteRequirements = requiredAmountCopy / 5000;
        if(atmContainer.get5000BanknotesCount() < currentBanknoteRequirements) {
            currentBanknoteRequirements = atmContainer.get5000BanknotesCount();
        }
        atmContainer.take5000Banknotes(currentBanknoteRequirements);
        moneyBundleBuilder.banknote5000Counter(currentBanknoteRequirements);
        requiredAmountCopy -= currentBanknoteRequirements * 5000;

        currentBanknoteRequirements = requiredAmountCopy / 1000;
        if(atmContainer.get1000BanknotesCount() < currentBanknoteRequirements) {
            currentBanknoteRequirements = atmContainer.get1000BanknotesCount();
        }
        atmContainer.take1000Banknotes(currentBanknoteRequirements);
        moneyBundleBuilder.banknote1000Counter(currentBanknoteRequirements);
        requiredAmountCopy -= currentBanknoteRequirements * 1000;

        currentBanknoteRequirements = requiredAmountCopy / 500;
        if(atmContainer.get500BanknotesCount() < currentBanknoteRequirements) {
            currentBanknoteRequirements = atmContainer.get500BanknotesCount();
        }
        atmContainer.take500Banknotes(currentBanknoteRequirements);
        moneyBundleBuilder.banknote500Counter(currentBanknoteRequirements);
        requiredAmountCopy -= currentBanknoteRequirements * 500;

        currentBanknoteRequirements = requiredAmountCopy / 100;
        if(atmContainer.get100BanknotesCount() < currentBanknoteRequirements) {
            currentBanknoteRequirements = atmContainer.get100BanknotesCount();
        }
        atmContainer.take100Banknotes(currentBanknoteRequirements);
        moneyBundleBuilder.banknote100Counter(currentBanknoteRequirements);
        requiredAmountCopy -= currentBanknoteRequirements * 100;

        currentBanknoteRequirements = requiredAmountCopy / 50;
        if(atmContainer.get50BanknotesCount() < currentBanknoteRequirements) {
            currentBanknoteRequirements = atmContainer.get50BanknotesCount();
        }
        atmContainer.take50Banknotes(currentBanknoteRequirements);
        moneyBundleBuilder.banknote50Counter(currentBanknoteRequirements);
        requiredAmountCopy -= currentBanknoteRequirements * 50;

        currentBanknoteRequirements = requiredAmountCopy / 10;
        if(atmContainer.get10BanknotesCount() < currentBanknoteRequirements) {
            currentBanknoteRequirements = atmContainer.get10BanknotesCount();
        }
        atmContainer.take10Banknotes(currentBanknoteRequirements);
        moneyBundleBuilder.banknote10Counter(currentBanknoteRequirements);
        requiredAmountCopy -= currentBanknoteRequirements * 10;

        if(requiredAmountCopy != 0) {
            atmContainer.rollbackState();
            throw new NoMatchingBanknotesException(requiredAmount, atmContainer);
        }else{
            atmContainer.commitNewState();
            return moneyBundleBuilder.build();
        }
    }
}
