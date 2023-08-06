package ru.mikheev.kirill.hw16patterns.facade;

public class FacadeTest {

    public static void main(String[] args) {
        UserNotifier userNotifier = new UserNotifier(
                "8(800)555-35-35",
                "Ivan.Ivanovich@mail.ru",
                123456789L
        );
        userNotifier.notifyByPush("It is time!");
        userNotifier.notifyBySms("It is time!");
        userNotifier.notifyByEmailMessage("It is time!");
    }
}
