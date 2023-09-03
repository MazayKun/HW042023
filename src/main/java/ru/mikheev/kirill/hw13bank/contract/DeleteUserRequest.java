package ru.mikheev.kirill.hw13bank.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mikheev.kirill.hw13bank.dto.MoneyBundleDto;

/**
 * ������ �� �������� ������������
 *
 * ����� - {@link MoneyBundleDto}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteUserRequest {
    private String name; // ��� ������������
    private String cardNumber; // ����� ����� ������������
    private String pin; // ��� ��� �� ����� ������������
}
