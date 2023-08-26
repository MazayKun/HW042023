package ru.mikheev.kirill.hw13bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mikheev.kirill.hw13bank.logic.container.MoneyAccount;
import ru.mikheev.kirill.hw13bank.logic.container.MoneyBundle;
import ru.mikheev.kirill.hw13bank.util.exception.internal.IllegalATMUsageException;
import ru.mikheev.kirill.hw13bank.util.exception.internal.NoMatchingBanknotesException;
import ru.mikheev.kirill.hw13bank.util.exception.internal.NotEnoughMoneyInATMException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoneyAccountTest {

    MoneyAccount testInstance;

    @BeforeEach
    void initATM() {
        testInstance = new MoneyAccount();
    }

    @Test
    void loadTest() {
        testInstance.loadBanknotes(
                new MoneyBundle(
                        1,
                        1,
                        1,
                        1,
                        1,
                        1
                )
        );
        assertEquals(6660, testInstance.getTotalAmount());
    }

    @Test
    void testEveryDenomination() {
        testInstance.loadBanknotes(
                new MoneyBundle(
                        1,
                        1,
                        1,
                        1,
                        1,
                        1
                )
        );
        MoneyBundle result = testInstance.getMoney(5000);
        assertEquals(5000, result.getTotalAmount());

        result = testInstance.getMoney(1000);
        assertEquals(1000, result.getTotalAmount());

        result = testInstance.getMoney(500);
        assertEquals(500, result.getTotalAmount());

        result = testInstance.getMoney(100);
        assertEquals(100, result.getTotalAmount());

        result = testInstance.getMoney(50);
        assertEquals(50, result.getTotalAmount());

        result = testInstance.getMoney(10);
        assertEquals(10, result.getTotalAmount());
        assertEquals(0, testInstance.getTotalAmount());
    }

    @Test
    void testBadArguments() {
        testInstance.loadBanknotes(
                new MoneyBundle(
                        1,
                        1,
                        1,
                        1,
                        1,
                        1
                )
        );
        assertThrows(IllegalATMUsageException.class, () -> testInstance.getMoney(1));
        assertThrows(IllegalATMUsageException.class, () -> testInstance.getMoney(0));
        assertThrows(IllegalATMUsageException.class, () -> testInstance.getMoney(-10));
    }

    @Test
    void testEmptyATM() {
        testInstance.loadBanknotes(
                new MoneyBundle(
                        0,
                        0,
                        0,
                        0,
                        0,
                        0
                )
        );
        assertThrows(NotEnoughMoneyInATMException.class, () -> testInstance.getMoney(10));
    }

    @Test
    void testNoMatchingBanknotes() {
        testInstance.loadBanknotes(
                new MoneyBundle(
                        1,
                        0,
                        0,
                        0,
                        0,
                        0
                )
        );
        assertThrows(NoMatchingBanknotesException.class, () -> testInstance.getMoney(4440));
    }
}
