package ru.mikheev.kirill.hw17serialization.pojo.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberMessages implements Serializable {
    @Serial
    private static final long serialVersionUID = -1186532202467501409L;

    private String last;
    private String belongNumber;
    private List<MessageInfo> messageInfoList = new ArrayList<>();

    public MemberMessages(String last, String belongNumber) {
        this.last = last;
        this.belongNumber = belongNumber;
    }
}
