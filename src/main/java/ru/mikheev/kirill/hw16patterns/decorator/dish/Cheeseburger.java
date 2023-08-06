package ru.mikheev.kirill.hw16patterns.decorator.dish;

import ru.mikheev.kirill.hw16patterns.decorator.dish.Dish;

public class Cheeseburger implements Dish {
    @Override
    public String dishName() {
        return "Cheeseburger";
    }
}
