package dynamicProxy.JDK.service;

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

