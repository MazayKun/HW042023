package ru.mikheev.kirill.hw07test.exception;

public class EntityCreationException extends RuntimeException {

    public EntityCreationException(Class<?> entityClass, Throwable cause) {
        super("Failed to instantiate entity of class " + entityClass.getName(), cause);
    }
}
