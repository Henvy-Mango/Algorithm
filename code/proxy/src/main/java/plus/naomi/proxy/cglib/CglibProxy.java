package plus.naomi.proxy.cglib;

import plus.naomi.proxy.cglib.service.ExtendService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Naomi
 * @description
 * @date 2021/11/24 15:45
 */
public class CglibProxy {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // CGLIB 可以代理未实现任何接口的类
        // CGLIB 动态代理是通过生成一个被代理类的子类来拦截被代理类的方法调用，因此不能代理声明为 final 类型的类和方法。
        ExtendService proxy = (ExtendService) CglibProxyFactory.getProxy(ExtendService.class);
        proxy.send("java");

        System.out.println("========================================");

        try {
            proxy.getClass().getDeclaredMethod("get", String.class);
        } catch (Exception e) {
            System.out.println("invoke private method get error -> " + e.getMessage());
        }

        Method get = ExtendService.class.getDeclaredMethod("get", String.class);
        get.setAccessible(true);
        get.invoke(proxy, "hello");
    }
}
