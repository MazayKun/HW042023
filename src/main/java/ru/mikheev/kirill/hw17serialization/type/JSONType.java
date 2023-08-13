package ru.mikheev.kirill.hw17serialization.type;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ru.mikheev.kirill.hw17serialization.pojo.result.SimplifiedChatSession;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class JSONType implements ProcessorType {

    private static final String FILE_NAME = "test.json";

    private final ObjectMapper objectMapper;
    private final ObjectWriter objectWriter;

    public JSONType() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
    }

    @Override
    public String serialize(List<SimplifiedChatSession> data) {
        try(var fis = new FileOutputStream(FILE_NAME)) {
            fis.write(objectWriter.writeValueAsBytes(data));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return FILE_NAME;
    }

    @Override
    public List<SimplifiedChatSession> deserialize(String fileName) {
        try(var fis = new FileInputStream(FILE_NAME)) {
            return objectMapper.readValue(fis, new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
