package ru.mikheev.kirill.hw17serialization.pojo.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimplifiedChatSession implements Serializable {
    @Serial
    private static final long serialVersionUID = -1331985026258293712L;

    private String chatIdentifier;
    private List<MemberMessages> memberMessages;
}
