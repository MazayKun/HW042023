package ru.mikheev.kirill.hw17serialization.pojo.result;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class MemberMessages {
    private String last;
    private String belongNumber;
    private List<MessageInfo> messageInfoList = new ArrayList<>();

    public MemberMessages(String last, String belongNumber) {
        this.last = last;
        this.belongNumber = belongNumber;
    }
}
