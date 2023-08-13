package ru.mikheev.kirill.hw17serialization.type;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ru.mikheev.kirill.hw17serialization.pojo.result.SimplifiedChatSession;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class XMLType implements ProcessorType {
    private static final String FILE_NAME = "test.xml";

    private final XmlMapper mapper;

    public XMLType() {
        mapper = new XmlMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public String serialize(List<SimplifiedChatSession> data) {
        try {
            mapper.writeValue(new File(FILE_NAME), data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return FILE_NAME;
    }

    @Override
    public List<SimplifiedChatSession> deserialize(String fileName) {
        try(var fis = new FileInputStream(FILE_NAME)) {
            return mapper.readValue(fis, new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
