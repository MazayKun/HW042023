package ru.mikheev.kirill.hw34customwebserver.util;

/**
 * Utility class for storing general info about http codes
 */
public final class ResponseCode {

    public static final  int OK_CODE = 200;
    public static final String OK_MESSAGE = "OK";

    public static final int NOT_FOUND_CODE = 404;
    public static final String NOT_FOUND_MESSAGE = "Not Found";

    public static final int INTERNAL_SERVER_ERROR_CODE = 500;
    public static final String INTERNAL_SERVER_ERROR_MESSAGE = "Internal Server Error";


    private ResponseCode(){}
}
