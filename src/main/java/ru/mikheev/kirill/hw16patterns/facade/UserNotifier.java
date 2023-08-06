package ru.mikheev.kirill.hw16patterns.facade;

import ru.mikheev.kirill.hw16patterns.facade.email.EmailMessageFormatter;
import ru.mikheev.kirill.hw16patterns.facade.email.EmailSender;
import ru.mikheev.kirill.hw16patterns.facade.push.PushSender;
import ru.mikheev.kirill.hw16patterns.facade.sms.NumberAvailabilityChecker;
import ru.mikheev.kirill.hw16patterns.facade.sms.SmsSender;

public class UserNotifier {

    private final String userPhoneNumber;
    private final String userEmailAddress;
    private final long userDeviceId;

    private final EmailMessageFormatter emailMessageFormatter = new EmailMessageFormatter("Some config");
    private final NumberAvailabilityChecker numberAvailabilityChecker;

    private final EmailSender emailSender = new EmailSender();
    private final PushSender pushSender = new PushSender();
    private final SmsSender smsSender  = new SmsSender();

    public UserNotifier(String userPhoneNumber, String userEmailAddress, long userDeviceId) {
        if( ! Validator.validatePhoneNumber(userPhoneNumber)) throw new RuntimeException("Bad phone number");
        if( ! Validator.validateEmailAddress(userEmailAddress)) throw new RuntimeException("Bad email address");
        if( ! Validator.validateDeviceId(userDeviceId)) throw new RuntimeException("Bad device id");
        this.userPhoneNumber = userPhoneNumber;
        this.userEmailAddress = userEmailAddress;
        this.userDeviceId = userDeviceId;
        this.numberAvailabilityChecker = new NumberAvailabilityChecker(userPhoneNumber);
    }

    public void notifyBySms(String smsText) {
        if( ! numberAvailabilityChecker.ping()) throw new RuntimeException("Phone number is not available now");
        smsSender.send(userPhoneNumber, smsText);
    }

    public void notifyByPush(String pushText) {
        pushSender.send(userDeviceId, pushText);
    }

    public void notifyByEmailMessage(String emailText) {
        String preparedText = emailMessageFormatter.prepareEmailMessageFromRawText(emailText);
        emailSender.send(userEmailAddress, preparedText);
    }
}
