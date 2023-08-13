package ru.mikheev.kirill.hw17serialization.pojo.result;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MessageInfo {
    private LocalDateTime sendDate;
    private String text;
}
