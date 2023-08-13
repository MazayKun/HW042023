package ru.mikheev.kirill.hw17serialization.type;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import ru.mikheev.kirill.hw17serialization.pojo.result.MemberMessages;
import ru.mikheev.kirill.hw17serialization.pojo.result.MessageInfo;
import ru.mikheev.kirill.hw17serialization.pojo.result.SimplifiedChatSession;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CSVType implements ProcessorType {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    private static final String SESSION_FILE_NAME = "session.csv";
    private static final String MEMBER_FILE_NAME = "member.csv";
    private static final String MESSAGE_FILE_NAME = "message.csv";

    private static final CSVFormat SESSION_WRITER_FORMAT = CSVFormat.DEFAULT.builder()
            .setHeader("session_id", "chatIdentifier")
            .setNullString("NULL")
            .build();
    private static final CSVFormat MEMBER_WRITER_FORMAT = CSVFormat.DEFAULT.builder()
            .setHeader("session_id", "member_id", "last", "belongNumber")
            .setNullString("NULL")
            .build();
    private static final CSVFormat MESSAGE_WRITER_FORMAT = CSVFormat.DEFAULT.builder()
            .setHeader("member_id", "sendDate", "text")
            .setNullString("NULL")
            .build();

    private static final CSVFormat SESSION_READER_FORMAT = CSVFormat.DEFAULT.builder()
            .setHeader("session_id", "chatIdentifier")
            .setNullString("NULL")
            .setSkipHeaderRecord(true)
            .build();
    private static final CSVFormat MEMBER_READER_FORMAT = CSVFormat.DEFAULT.builder()
            .setHeader("session_id", "member_id", "last", "belongNumber")
            .setNullString("NULL")
            .setSkipHeaderRecord(true)
            .build();
    private static final CSVFormat MESSAGE_READER_FORMAT = CSVFormat.DEFAULT.builder()
            .setHeader("member_id", "sendDate", "text")
            .setNullString("NULL")
            .setSkipHeaderRecord(true)
            .build();

    @Override
    public String serialize(List<SimplifiedChatSession> data) {
        String serializationId = UUID.randomUUID().toString();
        try (
                CSVPrinter sessionPrinter = new CSVPrinter(new BufferedWriter(new FileWriter(serializationId + '_' + SESSION_FILE_NAME)), SESSION_WRITER_FORMAT);
                CSVPrinter memberPrinter = new CSVPrinter(new BufferedWriter(new FileWriter(serializationId + '_' + MEMBER_FILE_NAME)), MEMBER_WRITER_FORMAT);
                CSVPrinter messagePrinter = new CSVPrinter(new BufferedWriter(new FileWriter(serializationId + '_' + MESSAGE_FILE_NAME)), MESSAGE_WRITER_FORMAT)
        ){
            UUID currentSessionId;
            UUID currentMemberId;
            for(SimplifiedChatSession session : data) {
                currentSessionId = UUID.randomUUID();
                for(MemberMessages member : session.getMemberMessages()) {
                    currentMemberId = UUID.randomUUID();
                    for (MessageInfo message : member.getMessageInfoList()) {
                        messagePrinter.printRecord(currentMemberId, Objects.toString(message.getSendDate()), message.getText());
                    }
                    memberPrinter.printRecord(currentSessionId, currentMemberId, member.getLast(), member.getBelongNumber());
                }
                sessionPrinter.printRecord(currentSessionId, session.getChatIdentifier());
            }
            return serializationId;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<SimplifiedChatSession> deserialize(String serializationId) {

        try (
                Reader sessionReader = new FileReader(serializationId + '_' + SESSION_FILE_NAME);
                Reader memberReader = new FileReader(serializationId + '_' + MEMBER_FILE_NAME);
                Reader messageReader = new FileReader(serializationId + '_' + MESSAGE_FILE_NAME)
        ){
            Iterable<CSVRecord> records = MESSAGE_READER_FORMAT.parse(messageReader);
            Map<UUID, List<MessageInfo>> messages = new HashMap<>();
            for (CSVRecord record : records) {
                messages.computeIfAbsent(
                        UUID.fromString(record.get("member_id")),
                        uuid -> new ArrayList<>()
                ).add( new MessageInfo(
                        LocalDateTime.parse(record.get("sendDate"), FORMATTER),
                        record.get("text")
                ));
            }
            records = MEMBER_READER_FORMAT.parse(memberReader);
            Map<UUID, List<MemberMessages>> members = new HashMap<>();
            UUID currentMemberId;
            for (CSVRecord record : records) {
                members.computeIfAbsent(
                        UUID.fromString(record.get("session_id")),
                        uuid -> new ArrayList<>()
                ).add(
                        new MemberMessages(
                                record.get("last"),
                                record.get("belongNumber"),
                                messages.getOrDefault(UUID.fromString(record.get("member_id")), Collections.emptyList())
                        )
                );
            }
            records = SESSION_READER_FORMAT.parse(sessionReader);
            List<SimplifiedChatSession> sessions = new ArrayList<>();
            for (CSVRecord record : records) {
                String str = record.get("session_id");
                sessions.add(
                        new SimplifiedChatSession(
                                record.get("chatIdentifier"),
                                members.getOrDefault(UUID.fromString(record.get("session_id")), Collections.emptyList())
                        )
                );
            }
            return sessions;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
