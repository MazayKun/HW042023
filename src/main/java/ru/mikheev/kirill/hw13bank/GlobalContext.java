package ru.mikheev.kirill.hw13bank;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import ru.mikheev.kirill.hw13bank.logic.service.ATMService;

public class GlobalContext {

    private static ATMService atmService;
    private static ObjectMapper objectMapper;
    private static ObjectWriter objectWriter;

    public static void init() {
        atmService = new ATMService();
        objectMapper = new ObjectMapper();
        objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
    }

    public static ATMService getAtmService() {
        return atmService;
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public static ObjectWriter getObjectWriter() {
        return objectWriter;
    }
}
