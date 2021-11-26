package dynamicProxy.CGLIB.service;

import dynamicProxy.CGLIB.annotation.MethodAnnotation;
import dynamicProxy.CGLIB.annotation.ParameterAnnotation;

public class Service {
    @MethodAnnotation(value = "method annotation", type = "method")
    public String send(@ParameterAnnotation("param annotation") String message) {
        System.out.println("invoke method send message -> " + message);
        return message;
    }
}
