package ru.mikheev.kirill.hw17serialization;

import ru.mikheev.kirill.hw17serialization.pojo.result.SimplifiedChatSession;
import ru.mikheev.kirill.hw17serialization.type.ProcessorType;

import java.util.List;

public class InternalDataProcessor {

    private ProcessorType processor;

    public InternalDataProcessor(ProcessorType processor) {
        this.processor = processor;
    }

    public List<SimplifiedChatSession> testProcessor(List<SimplifiedChatSession> data) {
        String fileName = processor.serialize(data);
        return processor.deserialize(fileName);
    }
}
