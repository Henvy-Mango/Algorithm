package dynamicProxy.JDK.service;

public interface SmsService {
    String send(String message);

    String check(String message);
}