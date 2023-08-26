package ru.mikheev.kirill.hw13bank.logic.container;

public class MoneyBundle extends BanknoteHolder {

    public MoneyBundle(int banknote5000counter, int banknote1000counter, int banknote500counter, int banknote100counter, int banknote50counter, int banknote10counter) {
        super(banknote5000counter, banknote1000counter, banknote500counter, banknote100counter, banknote50counter, banknote10counter);
    }

    private MoneyBundle() {
        super();
    }

    public static MoneyBundleBuilder builder() {
        return new MoneyBundleBuilder();
    }

    @Override
    public String toString() {
        return toStringByName("MoneyBundle");
    }

    public static class MoneyBundleBuilder {

        private final MoneyBundle moneyBundle;

        private MoneyBundleBuilder() {
            this.moneyBundle = new MoneyBundle();
        }

        public MoneyBundleBuilder banknote5000Counter(int banknote5000Counter) {
            this.moneyBundle.banknotesCounter[BANKNOTE_5000_COUNTER_INDEX] = banknote5000Counter;
            return this;
        }

        public MoneyBundleBuilder banknote1000Counter(int banknote1000Counter) {
            this.moneyBundle.banknotesCounter[BANKNOTE_1000_COUNTER_INDEX] = banknote1000Counter;
            return this;
        }

        public MoneyBundleBuilder banknote500Counter(int banknote500Counter) {
            this.moneyBundle.banknotesCounter[BANKNOTE_500_COUNTER_INDEX] = banknote500Counter;
            return this;
        }

        public MoneyBundleBuilder banknote100Counter(int banknote100Counter) {
            this.moneyBundle.banknotesCounter[BANKNOTE_100_COUNTER_INDEX] = banknote100Counter;
            return this;
        }

        public MoneyBundleBuilder banknote50Counter(int banknote50Counter) {
            this.moneyBundle.banknotesCounter[BANKNOTE_50_COUNTER_INDEX] = banknote50Counter;
            return this;
        }

        public MoneyBundleBuilder banknote10Counter(int banknote10Counter) {
            this.moneyBundle.banknotesCounter[BANKNOTE_10_COUNTER_INDEX] = banknote10Counter;
            return this;
        }

        public MoneyBundle build() {
            return this.moneyBundle;
        }
    }
}
