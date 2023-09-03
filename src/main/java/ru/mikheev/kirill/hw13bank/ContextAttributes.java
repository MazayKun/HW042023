package ru.mikheev.kirill.hw13bank;

/**
 * Набор кастомных атрибутов контекста сервлетов
 */
public class ContextAttributes {

    public static final String ATM_SERVICE_NAME = "atmService"; // Атрибут сервиса банкомата
    public static final String OBJECT_MAPPER_NAME = "objectMapper"; // Атрибут маппера json-объектов в java представление
    public static final String OBJECT_WRITER_NAME = "objectWriter"; // Атрибут конвертера java-объектов в json-объекты

    private ContextAttributes() {}
}
