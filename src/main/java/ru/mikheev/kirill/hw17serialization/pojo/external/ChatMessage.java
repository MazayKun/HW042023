package ru.mikheev.kirill.hw17serialization.pojo.external;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ChatMessage {
    @JsonProperty("ROWID")
    private long rowId;
    @JsonProperty("attributedBody")
    private String attributedBody;
    @JsonProperty("belong_number")
    private String belongNumber;
    @JsonProperty("date")
    private long date;
    @JsonProperty("date_read")
    private long dateRead;
    @JsonProperty("guid")
    private UUID guid;
    @JsonProperty("handle_id")
    private long handleId;
    @JsonProperty("has_dd_results")
    private int hasDDResults;
    @JsonProperty("is_deleted")
    private int isDeleted;
    @JsonProperty("is_from_me")
    private int isFromMe;
    @JsonProperty("send_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm:ss")
    private LocalDateTime sendDate;
    @JsonProperty("send_status")
    private int sendStatus;
    @JsonProperty("service")
    private String service;
    @JsonProperty("text")
    private String text;
}
