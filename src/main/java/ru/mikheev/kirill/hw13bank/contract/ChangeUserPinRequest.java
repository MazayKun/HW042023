package ru.mikheev.kirill.hw13bank.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mikheev.kirill.hw13bank.dto.UserDto;

/**
 * ��������� �� ����� ��� ���� ��� ������������
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeUserPinRequest {
    private UserDto user; // ������ �� ������������
    private String newPin; // ����� ���
}
