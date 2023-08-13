package ru.mikheev.kirill.hw17serialization.pojo.result;

import lombok.Data;

import java.util.List;

@Data
public class SimplifiedChatSession {
    private String chatIdentifier;
    private List<MemberMessages> memberMessages;
}
