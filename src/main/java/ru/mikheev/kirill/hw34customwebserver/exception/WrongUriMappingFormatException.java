package ru.mikheev.kirill.hw34customwebserver.exception;

import java.util.Arrays;

/**
 * Error means, that there is an error in mapping format (relative_uri:page_name_in_pages_directory)
 */
public class WrongUriMappingFormatException extends RuntimeException {

    public WrongUriMappingFormatException(String[] mapping) {
        super("Wrong uri mapping parts number " + Arrays.toString(mapping));
    }
}
