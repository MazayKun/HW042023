package ru.mikheev.kirill.hw17serialization.pojo.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Root {
    @JsonProperty("chat_sessions")
    private List<ChatSession> chatSessions;
}
