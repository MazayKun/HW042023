package ru.mikheev.kirill.hw13bank.dao;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.mikheev.kirill.hw13bank.dao.general.Generator;
import ru.mikheev.kirill.hw13bank.dao.general.Transactional;

import java.util.Objects;

/**
 * ѕредставление пользовател€ в системе
 */
@Setter
@Getter
@AllArgsConstructor
public class User implements Transactional {

    private static final Generator<Long> USER_ID_GENERATOR = new IdGenerator();
    private static final Generator<String> CARD_NUMBER_GENERATOR = new CardNumberGenerator();
    private static final Generator<String> CARD_PIN_GENERATOR = new PinGenerator();

    private long id;
    private String name;
    private String cardNumber;
    private String pin;

    @Getter(AccessLevel.NONE)
    private boolean stateFixed = false;
    @Getter(AccessLevel.NONE)
    private long fixedId;
    @Getter(AccessLevel.NONE)
    private String fixedName;
    @Getter(AccessLevel.NONE)
    private String fixedCardNumber;
    @Getter(AccessLevel.NONE)
    private String fixedPin;

    public User(String name) {
        this.id = USER_ID_GENERATOR.next();
        this.name = name;
        this.cardNumber = CARD_NUMBER_GENERATOR.next();
        this.pin = CARD_PIN_GENERATOR.next();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public void fixState() {
        if (stateFixed) {
            id = fixedId;
            name = fixedName;
            cardNumber = fixedCardNumber;
            pin = fixedPin;
            return;
        }
        fixedId = id;
        fixedName = name;
        fixedCardNumber = cardNumber;
        fixedPin = pin;
        stateFixed = true;
    }

    @Override
    public void commitState() {
        stateFixed = false;
    }

    @Override
    public void rollbackState() {
        id = fixedId;
        name = fixedName;
        cardNumber = fixedCardNumber;
        pin = fixedPin;
        stateFixed = false;
    }
}
