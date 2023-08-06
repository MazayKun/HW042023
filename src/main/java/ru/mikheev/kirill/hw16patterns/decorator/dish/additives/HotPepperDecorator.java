package ru.mikheev.kirill.hw16patterns.decorator.dish.additives;

import ru.mikheev.kirill.hw16patterns.decorator.dish.Dish;
import ru.mikheev.kirill.hw16patterns.decorator.dish.additives.DishDecorator;

public class HotPepperDecorator implements DishDecorator {

    private Dish dish;

    public HotPepperDecorator(Dish dish) {
        this.dish = dish;
    }

    @Override
    public String dishName() {
        return dish.dishName() + " with hot pepper";
    }
}
