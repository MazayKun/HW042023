package ru.mikheev.kirill.hw17serialization.type;

import ru.mikheev.kirill.hw17serialization.pojo.result.SimplifiedChatSession;

import java.util.List;

public interface ProcessorType {

    String serialize(List<SimplifiedChatSession> data);

    List<SimplifiedChatSession> deserialize(String fileName);
}
