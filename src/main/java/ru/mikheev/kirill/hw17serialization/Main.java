package ru.mikheev.kirill.hw17serialization;

public class Main {
    public static void main(String[] args)  {
        var collector = new ExternalInfoCollector();

        System.out.println(collector.collect("./sms.json"));
    }
}

//<chat_identifier> -
//<members.last> -
//<messages.belong_number> -
//<messages.send_date> -
//<messages.text>
//        с группировкой по полю <messages.belong_number>
//        и сортировкой от более старых сообщений к более новым
