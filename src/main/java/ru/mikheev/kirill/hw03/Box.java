package ru.mikheev.kirill.hw03;

import ru.mikheev.kirill.hw03.fruits.Fruit;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> implements HasWeight {

    private int currentWeight = 0;
    private final List<T> fruits = new ArrayList<>();

    @Override
    public int weight() {
        return currentWeight;
    }

    public void put(T newFruit) {
        fruits.add(newFruit);
        currentWeight += newFruit.weight();
    }

    public void putAll(Box<? extends T> anotherBox) {
        fruits.addAll(anotherBox.fruits);
        currentWeight += anotherBox.currentWeight;
        anotherBox.clearBox();
    }

    public boolean compare(Box<?> anotherBox) {
        return this.currentWeight == anotherBox.currentWeight;
    }

    private void clearBox() {
        currentWeight = 0;
        fruits.clear();
    }
}
