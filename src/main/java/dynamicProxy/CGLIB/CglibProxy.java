package dynamicProxy.CGLIB;

import dynamicProxy.CGLIB.service.Service;

public class CglibProxy {
    public static void main(String[] args) {
        // CGLIB 可以代理未实现任何接口的类
        // CGLIB 动态代理是通过生成一个被代理类的子类来拦截被代理类的方法调用，因此不能代理声明为 final 类型的类和方法。
        Service service = (Service) CglibProxyFactory.getProxy(Service.class);
        service.send("java");
    }
}
