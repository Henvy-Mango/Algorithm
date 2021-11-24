package dynamicProxy.CGLIB.service;

public class Service {
    public String send(String message) {
        System.out.println("send message:" + message);
        return message;
    }
}
