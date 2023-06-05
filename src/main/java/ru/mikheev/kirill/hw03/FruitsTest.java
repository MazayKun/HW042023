package ru.mikheev.kirill.hw03;

import ru.mikheev.kirill.hw03.fruits.Apple;
import ru.mikheev.kirill.hw03.fruits.Fruit;
import ru.mikheev.kirill.hw03.fruits.Orange;

public class FruitsTest {
    public static void main(String[] args) {
        Apple apple = new Apple();
        Orange orange = new Orange();
        Fruit fruit = new Apple();

        Box<Apple> appleBox = new Box<>();
        appleBox.put(apple);
        System.out.println("Apple box weight : " + appleBox.weight());

        Box<Orange> orangeBox = new Box<>();
        orangeBox.put(orange);
        System.out.println("Orange box weight : " + orangeBox.weight());
        System.out.println("Comparing apple and orange boxes result : " + appleBox.compare(orangeBox));

        Box<Fruit> fruitBox = new Box<>();
        fruitBox.put(fruit);
        System.out.println("Fruit box weight : " + fruitBox.weight());

        fruitBox.putAll(appleBox);
        fruitBox.putAll(orangeBox);
        System.out.println("Fruit box weight after merging : " + fruitBox.weight());
        System.out.println("Apple box weight after merging : " + appleBox.weight());
        System.out.println("Orange box weight after merging : " + orangeBox.weight());
        System.out.println("Comparing apple and orange boxes after merging result : " + appleBox.compare(orangeBox));
    }
}
