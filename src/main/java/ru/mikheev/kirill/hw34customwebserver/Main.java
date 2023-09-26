package ru.mikheev.kirill.hw34customwebserver;

import ru.mikheev.kirill.hw34customwebserver.property.PropertiesReader;
import ru.mikheev.kirill.hw34customwebserver.property.ServerProperties;
import ru.mikheev.kirill.hw34customwebserver.server.Server;

public class Main {

    public static void main(String[] args) {
        PropertiesReader propertiesReader = new PropertiesReader();
        ServerProperties properties = propertiesReader.read("application.yml");
        properties.updateForDefault();
        Server server = new Server(properties);
        server.startServer();
    }
}
