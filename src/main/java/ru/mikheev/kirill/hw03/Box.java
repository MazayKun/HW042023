package ru.mikheev.kirill.hw03;

import ru.mikheev.kirill.hw03.fruits.Fruit;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Box<T extends Fruit> implements HasWeight {

    private final List<T> fruits = new ArrayList<>();

    @Override
    public int weight() {
        int acc = 0;
        for(T fruit : fruits) {
            acc += fruit.weight();
        }
        return acc;
    }

    public void put(T newFruit) {
        fruits.add(newFruit);
    }

    public void putAll(Box<? extends T> anotherBox) {
        if(Objects.isNull(anotherBox)) {
            throw new NullPointerException("Argument box is null");
        }
        if(this == anotherBox) {
            throw new IllegalArgumentException("It is not allowed to add a box to itself");
        }
        fruits.addAll(anotherBox.fruits);
        anotherBox.fruits.clear();
    }

    public boolean compare(Box<?> anotherBox) {
        return Objects.nonNull(anotherBox)
                && (this == anotherBox || this.weight() == anotherBox.weight());
    }
}
