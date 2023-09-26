package ru.mikheev.kirill.hw34customwebserver.property;


import lombok.Getter;
import lombok.Setter;

import static java.util.Objects.isNull;

/**
 * General server config
 */
@Setter
@Getter
public class Config {

    private static final int DEFAULT_PORT = 80;
    private static final int DEFAULT_CONCURRENCY = 4;

    private Integer port;
    private Integer concurrency;

    public void updateForDefault() {
        if (isNull(port)) {
            port = DEFAULT_PORT;
        }
        if (isNull(concurrency)) {
            concurrency = DEFAULT_CONCURRENCY;
        }
    }
}
