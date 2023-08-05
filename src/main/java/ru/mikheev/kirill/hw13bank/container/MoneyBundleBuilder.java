package ru.mikheev.kirill.hw13bank.container;

public class MoneyBundleBuilder {

    private int banknote5000Counter = 0;
    private int banknote1000Counter = 0;
    private int banknote500Counter = 0;
    private int banknote100Counter = 0;
    private int banknote50Counter = 0;
    private int banknote10Counter = 0;

    public MoneyBundleBuilder banknote5000Counter(int banknote5000Counter) {
        this.banknote5000Counter = banknote5000Counter;
        return this;
    }

    public MoneyBundleBuilder banknote1000Counter(int banknote1000Counter) {
        this.banknote1000Counter = banknote1000Counter;
        return this;
    }

    public MoneyBundleBuilder banknote500Counter(int banknote500Counter) {
        this.banknote500Counter = banknote500Counter;
        return this;
    }

    public MoneyBundleBuilder banknote100Counter(int banknote100Counter) {
        this.banknote100Counter = banknote100Counter;
        return this;
    }

    public MoneyBundleBuilder banknote50Counter(int banknote50Counter) {
        this.banknote50Counter = banknote50Counter;
        return this;
    }

    public MoneyBundleBuilder banknote10Counter(int banknote10Counter) {
        this.banknote10Counter = banknote10Counter;
        return this;
    }

    public MoneyBundle build() {
        return new MoneyBundle(
                banknote5000Counter,
                banknote1000Counter,
                banknote500Counter,
                banknote100Counter,
                banknote50Counter,
                banknote10Counter
        );
    }
}
