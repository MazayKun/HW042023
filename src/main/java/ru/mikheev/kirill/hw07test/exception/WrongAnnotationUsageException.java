package ru.mikheev.kirill.hw07test.exception;

import java.lang.reflect.Method;

public class WrongAnnotationUsageException extends RuntimeException{

    public WrongAnnotationUsageException(Class<?> firstAnnotation, Class<?> secondAnnotation, Method method) {
        super(
                String.format(
                        "More than one test lifecycle annotations (%s, %s) found on method %s",
                        firstAnnotation.getSimpleName(),
                        secondAnnotation.getSimpleName(),
                        method.getName()
                )
        );
        if(!firstAnnotation.isAnnotation()) {
            throw new IllegalArgumentException("First argument must be an annotation, but found " + firstAnnotation.getName());
        }
        if(!secondAnnotation.isAnnotation()) {
            throw new IllegalArgumentException("Second argument must be an annotation, but found " + secondAnnotation.getName());
        }
    }
}
