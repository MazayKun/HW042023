package ru.mikheev.kirill.hw13bank.dao.general;

/**
 * ���������� ������� ��������� ����
 * @param <T> - ��� ������������ ��������
 */
public interface Generator<T> {

    T next();
}
