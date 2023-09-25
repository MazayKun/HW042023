package ru.mikheev.kirill.hw34customwebserver.exception;

/**
 * Error during properties reading from file
 */
public class PropertiesReadException extends RuntimeException {

    public PropertiesReadException(String fileName, Throwable cause) {
        super("Error during properties reading from file with name " + fileName, cause);
    }
}
