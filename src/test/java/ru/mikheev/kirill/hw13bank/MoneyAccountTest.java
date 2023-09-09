package ru.mikheev.kirill.hw13bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mikheev.kirill.hw13bank.dao.MoneyAccount;
import ru.mikheev.kirill.hw13bank.exception.internal.IllegalATMUsageException;
import ru.mikheev.kirill.hw13bank.exception.internal.NoMatchingBanknotesException;
import ru.mikheev.kirill.hw13bank.exception.internal.NotEnoughMoneyInATMException;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class MoneyAccountTest {

    MoneyAccount testInstance;

    @BeforeEach
    void initATM() {
        testInstance = new MoneyAccount();
    }

    @Test
    void loadTest() {
        testInstance.loadBanknotes(
                Map.of(
                        5000, 1,
                        1000, 1,
                        500, 1,
                        100, 1,
                        50, 1,
                        10, 1
                )
        );
        assertEquals(6660, testInstance.getTotalAmount());
    }

    @Test
    void testEveryDenomination() {
        testInstance.loadBanknotes(
                Map.of(
                        5000, 1,
                        1000, 1,
                        500, 1,
                        100, 1,
                        50, 1,
                        10, 1
                )
        );
        Map<Integer, Integer> result = testInstance.getMoney(5000);
        assertEquals(1, result.remove(5000));
        assertTrue(result.isEmpty());

        result = testInstance.getMoney(1000);
        assertEquals(1, result.remove(1000));
        assertTrue(result.isEmpty());

        result = testInstance.getMoney(500);
        assertEquals(1, result.remove(500));
        assertTrue(result.isEmpty());

        result = testInstance.getMoney(100);
        assertEquals(1, result.remove(100));
        assertTrue(result.isEmpty());

        result = testInstance.getMoney(50);
        assertEquals(1, result.remove(50));
        assertTrue(result.isEmpty());

        result = testInstance.getMoney(10);
        assertEquals(1, result.remove(10));
        assertTrue(result.isEmpty());
        assertEquals(0, testInstance.getTotalAmount());
    }

    @Test
    void testBadArguments() {
        testInstance.loadBanknotes(
                Map.of(
                        5000, 1,
                        1000, 1,
                        500, 1,
                        100, 1,
                        50, 1,
                        10, 1
                )
        );
        assertThrows(IllegalATMUsageException.class, () -> testInstance.getMoney(1));
        assertThrows(IllegalATMUsageException.class, () -> testInstance.getMoney(0));
        assertThrows(IllegalATMUsageException.class, () -> testInstance.getMoney(-10));
    }

    @Test
    void testEmptyATM() {
        testInstance.loadBanknotes(
                Map.of(
                        5000, 0,
                        1000, 0,
                        500, 0,
                        100, 0,
                        50, 0,
                        10, 0
                )
        );
        assertThrows(NotEnoughMoneyInATMException.class, () -> testInstance.getMoney(10));
    }

    @Test
    void testNoMatchingBanknotes() {
        testInstance.loadBanknotes(
                Map.of(
                        5000, 1,
                        1000, 1,
                        500, 1,
                        100, 1,
                        50, 1,
                        10, 1
                )
        );
        assertThrows(NoMatchingBanknotesException.class, () -> testInstance.getMoney(4440));
    }
}
