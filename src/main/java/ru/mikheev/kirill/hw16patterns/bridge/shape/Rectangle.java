package ru.mikheev.kirill.hw16patterns.bridge.shape;

import ru.mikheev.kirill.hw16patterns.bridge.color.Color;

public class Rectangle extends Shape {

    public Rectangle(Color color) {
        super(color);
    }

    @Override
    public void draw() {
        System.out.println(color.getColorName() + " rectangle");
    }
}
