package ru.mikheev.kirill.hw13bank;

/**
 * Custom attributes names for servlets
 */
public class ContextAttributes {

    public static final String ATM_SERVICE_NAME = "atmService"; // ATMService attribute name
    public static final String OBJECT_MAPPER_NAME = "objectMapper"; // JSON-objects mapper attribute name
    public static final String OBJECT_WRITER_NAME = "objectWriter"; // object-to-JSON writer attribute name

    private ContextAttributes() {}
}
