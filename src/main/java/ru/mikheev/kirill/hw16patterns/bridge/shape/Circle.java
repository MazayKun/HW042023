package ru.mikheev.kirill.hw16patterns.bridge.shape;

import ru.mikheev.kirill.hw16patterns.bridge.color.Color;

public class Circle extends Shape {

    public Circle(Color color) {
        super(color);
    }

    @Override
    public void draw() {
        System.out.println(color.getColorName() + " circle");
    }
}
