package ru.mikheev.kirill.hw34customwebserver.property;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

import static java.util.Objects.isNull;

/**
 * All application properties
 */
@Getter
@Setter
public class ServerProperties {

    private Config config;
    private List<String> uri;

    public void updateForDefault() {
        if(isNull(config)) {
            config = new Config();
        }
        config.updateForDefault();
        if(isNull(uri)) {
            uri = Collections.emptyList();
        }
    }
}
