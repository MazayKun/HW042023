package ru.mikheev.kirill.hw16patterns.bridge;

import ru.mikheev.kirill.hw16patterns.bridge.color.Green;
import ru.mikheev.kirill.hw16patterns.bridge.color.Red;
import ru.mikheev.kirill.hw16patterns.bridge.shape.Circle;
import ru.mikheev.kirill.hw16patterns.bridge.shape.Rectangle;
import ru.mikheev.kirill.hw16patterns.bridge.shape.Shape;

public class BridgeTest {
    public static void main(String[] args) {
        Shape circle = new Circle(new Red());
        circle.draw();
        Shape rectangle = new Rectangle(new Green());
        rectangle.draw();
    }
}
