package ru.mikheev.kirill.hw34customwebserver.property;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import ru.mikheev.kirill.hw34customwebserver.exception.PropertiesReadException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import static java.util.Objects.isNull;

/**
 * Read properties from file to {@link ServerProperties}
 */
public class PropertiesReader {

    private final Yaml yamlReader = new Yaml(new Constructor(ServerProperties.class, new LoaderOptions()));

    public ServerProperties read(String fileName) {
        InputStream resourceStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
        if (isNull(resourceStream)) throw new RuntimeException("Properties file not found");
        try (InputStream propertiesStream = new BufferedInputStream(
                resourceStream
        )) {
            return yamlReader.load(propertiesStream);
        } catch (IOException e) {
            throw new PropertiesReadException(fileName, e);
        }
    }
}
