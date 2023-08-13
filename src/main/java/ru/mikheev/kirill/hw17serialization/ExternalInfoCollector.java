package ru.mikheev.kirill.hw17serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ru.mikheev.kirill.hw17serialization.pojo.external.ChatMember;
import ru.mikheev.kirill.hw17serialization.pojo.external.ChatMessage;
import ru.mikheev.kirill.hw17serialization.pojo.external.ChatSession;
import ru.mikheev.kirill.hw17serialization.pojo.external.Root;
import ru.mikheev.kirill.hw17serialization.pojo.result.MemberMessages;
import ru.mikheev.kirill.hw17serialization.pojo.result.MessageInfo;
import ru.mikheev.kirill.hw17serialization.pojo.result.SimplifiedChatSession;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExternalInfoCollector {

    public List<SimplifiedChatSession> collect(String filePath) {
        var objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        Root root;
        try(var fis = new FileInputStream(filePath)) {
             root = objectMapper.readValue(fis, Root.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<SimplifiedChatSession> result = new ArrayList<>();
        for(ChatSession chatSession : root.getChatSessions()) {
            var simplifiedChatSession = new SimplifiedChatSession();
            simplifiedChatSession.setChatIdentifier(chatSession.getChatIdentifier());
            simplifiedChatSession.setMemberMessages(prepareMessageData(chatSession));
            result.add(simplifiedChatSession);
        }
        return result;
    }

    private List<MemberMessages> prepareMessageData(ChatSession chatSession) {
        List<MemberMessages> result = new ArrayList<>();
        Map<String, MemberMessages> memberMessagesByPhone = new HashMap<>();
        for(ChatMember chatMember : chatSession.getMembers()) {
            if(memberMessagesByPhone.putIfAbsent(
                    chatMember.getPhoneNumber(),
                    new MemberMessages(chatMember.getLast(), chatMember.getPhoneNumber())
            ) != null) {
                throw new RuntimeException("Several members with the same phone number");
            }
        }

        for(ChatMessage chatMessage : chatSession.getMessages()) {
            MemberMessages currentMember = memberMessagesByPhone.computeIfAbsent(
                    chatMessage.getBelongNumber(),
                    phoneNumber -> {
                        var memberMessages = new MemberMessages();
                        memberMessages.setBelongNumber(phoneNumber);
                        return memberMessages;
                    }
            );
            addWithSorting(currentMember.getMessageInfoList(), new MessageInfo(chatMessage.getSendDate(), chatMessage.getText()));
        }
        for (Map.Entry<String, MemberMessages> entry : memberMessagesByPhone.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }

    private void addWithSorting(List<MessageInfo> list, MessageInfo newElement) {
        int i = 0;
        for(;i < list.size();i++) {
            if(list.get(i).getSendDate().isAfter(newElement.getSendDate())) {
                break;
            }
        }
        list.add(i, newElement);
    }
}
