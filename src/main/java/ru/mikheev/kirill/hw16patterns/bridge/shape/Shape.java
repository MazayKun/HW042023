package ru.mikheev.kirill.hw16patterns.bridge.shape;

import ru.mikheev.kirill.hw16patterns.bridge.color.Color;

public abstract class Shape {

    protected Color color;

    public Shape(Color color) {
        this.color = color;
    }

    public abstract void draw();
}
