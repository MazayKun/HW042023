package ru.mikheev.kirill.hw01gradle;

import java.util.List;
import java.util.StringJoiner;

public class HelloOtus {

    public static void main(String[] args) {
        var animalsList = List.of("Кошка", "Собака", "Суслослон");
        StringJoiner stringJoiner = new StringJoiner(", ", "Список животных: ", "!");
        for (var animal : animalsList) {
            stringJoiner.add(animal);
        }
        System.out.println(stringJoiner);
    }
}
