package ru.mikheev.kirill.hw16patterns.facade.email;

public class EmailMessageFormatter {

    public EmailMessageFormatter(String someConfig) {}

    public String prepareEmailMessageFromRawText(String rawText) {
        return "Formatted text : " + rawText;
    }
}
