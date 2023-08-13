package ru.mikheev.kirill.hw17serialization;

import ru.mikheev.kirill.hw17serialization.pojo.result.SimplifiedChatSession;
import ru.mikheev.kirill.hw17serialization.type.CSVType;
import ru.mikheev.kirill.hw17serialization.type.JSONType;
import ru.mikheev.kirill.hw17serialization.type.XMLType;
import ru.mikheev.kirill.hw17serialization.type.YAMLType;

import java.util.List;

public class Main {
    public static void main(String[] args)  {
        var collector = new ExternalInfoCollector();
        List<SimplifiedChatSession> data = collector.collect("./sms.json");
        var jsonProcessor = new InternalDataProcessor(new JSONType());
        var xmlProcessor = new InternalDataProcessor(new XMLType());
        var yamlProcessor = new InternalDataProcessor(new YAMLType());
        var csvProcessor = new InternalDataProcessor(new CSVType());
        System.out.println(data);
        System.out.println(jsonProcessor.testProcessor(data));
        System.out.println(xmlProcessor.testProcessor(data));
        System.out.println(yamlProcessor.testProcessor(data));
        System.out.println(csvProcessor.testProcessor(data));
    }
}