package ru.mikheev.kirill.hw17serialization.pojo.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ChatMember {

    @JsonProperty("first")
    private String first;
    @JsonProperty("handle_id")
    private long handleId;
    @JsonProperty("image_path")
    private String imagePath;
    @JsonProperty("last")
    private String last;
    @JsonProperty("middle")
    private String middle;
    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("service")
    private String service;
    @JsonProperty("thumb_path")
    private String thumbPath;
}
