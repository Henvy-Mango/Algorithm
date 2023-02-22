package plus.naomi.proxy.jdk.service;

/**
 * @author Naomi
 * @description
 * @date 2021/11/24 15:45
 */
public class ServiceImpl implements SmsService, Person {

    @Override
    public String doWork(String message) {
        System.out.println("do work:" + message);
        return message;
    }

    @Override
    public String send(String message) {
        System.out.println("send message:" + message);
        return message;
    }

    @Override
    public String check(String message) {
        System.out.println("check message:" + message);
        return message;
    }
}

