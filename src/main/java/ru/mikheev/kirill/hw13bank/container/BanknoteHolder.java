package ru.mikheev.kirill.hw13bank.container;

public abstract class BanknoteHolder {

    static final int BANKNOTE_5000_COUNTER_INDEX = 0;
    static final int BANKNOTE_1000_COUNTER_INDEX = 1;
    static final int BANKNOTE_500_COUNTER_INDEX = 2;
    static final int BANKNOTE_100_COUNTER_INDEX = 3;
    static final int BANKNOTE_50_COUNTER_INDEX = 4;
    static final int BANKNOTE_10_COUNTER_INDEX = 5;

    static final String[] BANKNOTE_NAMES = {"5000", "1000", "500", "100", "50", "10"};

    protected int[] banknotesCounter = new int[6];

    BanknoteHolder(int banknote5000counter, int banknote1000counter, int banknote500counter, int banknote100counter, int banknote50counter, int banknote10counter) {
        banknotesCounter[BANKNOTE_5000_COUNTER_INDEX] = banknote5000counter;
        banknotesCounter[BANKNOTE_1000_COUNTER_INDEX] = banknote1000counter;
        banknotesCounter[BANKNOTE_500_COUNTER_INDEX] = banknote500counter;
        banknotesCounter[BANKNOTE_100_COUNTER_INDEX] = banknote100counter;
        banknotesCounter[BANKNOTE_50_COUNTER_INDEX] = banknote50counter;
        banknotesCounter[BANKNOTE_10_COUNTER_INDEX] = banknote10counter;
    }

    public int get5000BanknotesCount() {
        return banknotesCounter[BANKNOTE_5000_COUNTER_INDEX];
    }

    public int get1000BanknotesCount() {
        return banknotesCounter[BANKNOTE_1000_COUNTER_INDEX];
    }

    public int get500BanknotesCount() {
        return banknotesCounter[BANKNOTE_500_COUNTER_INDEX];
    }

    public int get100BanknotesCount() {
        return banknotesCounter[BANKNOTE_100_COUNTER_INDEX];
    }

    public int get50BanknotesCount() {
        return banknotesCounter[BANKNOTE_50_COUNTER_INDEX];
    }

    public int get10BanknotesCount() {
        return banknotesCounter[BANKNOTE_10_COUNTER_INDEX];
    }

    public int getTotalAmount() {
        return banknotesCounter[BANKNOTE_5000_COUNTER_INDEX] * 5000 +
                banknotesCounter[BANKNOTE_1000_COUNTER_INDEX] * 1000 +
                banknotesCounter[BANKNOTE_500_COUNTER_INDEX] * 500 +
                banknotesCounter[BANKNOTE_100_COUNTER_INDEX] * 100 +
                banknotesCounter[BANKNOTE_50_COUNTER_INDEX] * 50 +
                banknotesCounter[BANKNOTE_10_COUNTER_INDEX] * 10;

    }

    protected String toStringByName(String name) {
        StringBuilder result = new StringBuilder();
        boolean emptyResult = true;
        for(int i = 0; i < 6; i++) {
            if(banknotesCounter[i] > 0) {
                result.append(BANKNOTE_NAMES[i])
                        .append(" : ")
                        .append(banknotesCounter[i])
                        .append("\n");
                emptyResult = false;
            }
        }
        String nameWithLining = name + '\n';
        if(emptyResult) {
            return "Empty " + nameWithLining;
        } else {
            return result.insert(0, nameWithLining).toString();
        }
    }
}
