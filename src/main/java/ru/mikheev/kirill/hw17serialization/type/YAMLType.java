package ru.mikheev.kirill.hw17serialization.type;

import org.yaml.snakeyaml.Yaml;
import ru.mikheev.kirill.hw17serialization.pojo.result.MemberMessages;
import ru.mikheev.kirill.hw17serialization.pojo.result.MessageInfo;
import ru.mikheev.kirill.hw17serialization.pojo.result.SimplifiedChatSession;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@SuppressWarnings("unchecked")
public class YAMLType implements ProcessorType {

    private static final String FILE_NAME = "test.yaml";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    private final Yaml yaml;

    public YAMLType() {
        yaml = new Yaml();
    }

    @Override
    public String serialize(List<SimplifiedChatSession> data) {
        try(var fileWriter = new BufferedWriter(new FileWriter(FILE_NAME))) {
            String fileContent = yaml.dump(toMaps(data));
//            int firstLineBreakIndex = fileContent.indexOf('\n') + 2;
//            fileWriter.write('-');
            fileWriter.write(fileContent);//, firstLineBreakIndex, fileContent.length() - firstLineBreakIndex);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return FILE_NAME;
    }

    @Override
    public List<SimplifiedChatSession> deserialize(String fileName) {
        try(var fis = new FileInputStream(fileName)) {
            List<Map<String, Object>> data = yaml.load(fis);
            List<SimplifiedChatSession> result = new ArrayList<>();
            for(Map<String, Object> session : data) {
                result.add(parseSimplifiedChatSession(session));
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Object> toMaps(List<SimplifiedChatSession> data) {
        List<Object> result = new ArrayList<>();
        for(SimplifiedChatSession simplifiedChatSession : data) {
            result.add(toMap(simplifiedChatSession));
        }
        return result;
    }

    private Map<String, Object> toMap(SimplifiedChatSession data) {
        Map<String, Object> result = new HashMap<>();
        result.put("chatIdentifier", data.getChatIdentifier());
        List<Map<String, Object>> memberMessagesList = new ArrayList<>();
        for(MemberMessages memberMessages : data.getMemberMessages()) {
            memberMessagesList.add(toMap(memberMessages));
        }
        result.put("memberMessages", memberMessagesList);
        return result;
    }

    private Map<String, Object> toMap(MemberMessages data) {
        Map<String, Object> result = new HashMap<>();
        result.put("last", data.getLast());
        result.put("belongNumber", data.getBelongNumber());
        List<Map<String, Object>> messageInfoList = new ArrayList<>();
        for(MessageInfo messageInfo : data.getMessageInfoList()) {
            messageInfoList.add(toMap(messageInfo));
        }
        result.put("messageInfoList", messageInfoList);
        return result;
    }

    private Map<String, Object> toMap(MessageInfo data) {
        Map<String, Object> result = new HashMap<>();
        result.put("sendDate", data.getSendDate().toString());
        result.put("text", data.getText());
        return result;
    }

    private SimplifiedChatSession parseSimplifiedChatSession(Map<String, Object> data) {
        SimplifiedChatSession simplifiedChatSession = new SimplifiedChatSession();
        simplifiedChatSession.setChatIdentifier(Objects.toString(data.get("chatIdentifier")));
        List<MemberMessages> memberMessages = new ArrayList<>();
        for(Object object : (List<Object>)data.get("memberMessages")) {
            memberMessages.add(parseMemberMessages((Map<String, Object>)object));
        }
        simplifiedChatSession.setMemberMessages(memberMessages);
        return simplifiedChatSession;
    }

    private MemberMessages parseMemberMessages(Map<String, Object> data) {
        MemberMessages memberMessages = new MemberMessages();
        memberMessages.setLast(Objects.toString(data.get("last")));
        memberMessages.setBelongNumber(Objects.toString(data.get("belongNumber")));
        List<MessageInfo> messageInfos = new ArrayList<>();
        for(Object object : (List<Object>)data.get("messageInfoList")) {
            messageInfos.add(parseMessageInfo((Map<String, Object>)object));
        }
        memberMessages.setMessageInfoList(messageInfos);
        return memberMessages;
    }

    private MessageInfo parseMessageInfo(Map<String, Object> data) {
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setSendDate(LocalDateTime.parse(Objects.toString(data.get("sendDate")), FORMATTER));
        messageInfo.setText(Objects.toString(data.get("text")));
        return messageInfo;
    }
}
