package dynamicProxy.JDK;

import dynamicProxy.JDK.service.Person;
import dynamicProxy.JDK.service.SmsService;
import dynamicProxy.JDK.service.ServiceImpl;

public class JdkProxy {
    public static void main(String[] args) {
        // JDK 动态代理只能代理实现了接口的类或者直接代理接口
        SmsService smsService = (SmsService) JdkProxyFactory.getProxy(new ServiceImpl());
        // 只能代理接口方法
        smsService.send("msg");
        smsService.check("notice");

        // 不同接口需要强转不同的接口类型
        Person person = (Person) JdkProxyFactory.getProxy(new ServiceImpl());
        person.doWork("homework");
    }
}
