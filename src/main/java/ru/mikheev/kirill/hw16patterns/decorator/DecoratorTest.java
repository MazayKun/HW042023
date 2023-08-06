package ru.mikheev.kirill.hw16patterns.decorator;

import ru.mikheev.kirill.hw16patterns.decorator.dish.Cheeseburger;
import ru.mikheev.kirill.hw16patterns.decorator.dish.Dish;
import ru.mikheev.kirill.hw16patterns.decorator.dish.additives.FriedOnionDecorator;
import ru.mikheev.kirill.hw16patterns.decorator.dish.additives.HotPepperDecorator;

public class DecoratorTest {

    public static void main(String[] args) {
        Dish burger = new Cheeseburger();
        burger = new FriedOnionDecorator(burger);
        burger = new HotPepperDecorator(burger);
        System.out.println(burger.dishName());
    }
}
