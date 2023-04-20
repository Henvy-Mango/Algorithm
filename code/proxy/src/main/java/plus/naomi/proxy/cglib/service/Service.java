package plus.naomi.proxy.cglib.service;

import plus.naomi.proxy.cglib.annotation.MethodAnnotation;
import plus.naomi.proxy.cglib.annotation.ParameterAnnotation;

/**
 * @author Naomi
 * @description
 * @date 2021/11/26 15:45
 */
public class Service {
    @MethodAnnotation(value = "method annotation", type = "method")
    public String send(@ParameterAnnotation("param annotation") String message) {
        System.out.println("invoke public method send message -> " + message);
        return message;
    }
}
