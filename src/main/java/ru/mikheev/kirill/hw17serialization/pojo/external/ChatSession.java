package ru.mikheev.kirill.hw17serialization.pojo.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ChatSession {
    @JsonProperty("chat_id")
    private long chatId;
    @JsonProperty("chat_identifier")
    private String chatIdentifier;
    @JsonProperty("display_name")
    private String displayName;
    @JsonProperty("is_deleted")
    private int isDeleted;
    @JsonProperty("members")
    private List<ChatMember> members;
    @JsonProperty("messages")
    private List<ChatMessage> messages;
}
