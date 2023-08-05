package ru.mikheev.kirill.hw13bank.container;

public class MoneyBundle extends BanknoteHolder {

    public MoneyBundle(int banknote5000counter, int banknote1000counter, int banknote500counter, int banknote100counter, int banknote50counter, int banknote10counter) {
        super(banknote5000counter, banknote1000counter, banknote500counter, banknote100counter, banknote50counter, banknote10counter);
    }

    public static MoneyBundleBuilder builder() {
        return new MoneyBundleBuilder();
    }

    @Override
    public String toString() {
        return toStringByName("MoneyBundle");
    }
}
