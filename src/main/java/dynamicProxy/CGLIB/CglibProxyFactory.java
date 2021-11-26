package dynamicProxy.CGLIB;

import dynamicProxy.CGLIB.annotation.ClassAnnotation;
import dynamicProxy.CGLIB.annotation.FieldAnnotation;
import dynamicProxy.CGLIB.annotation.MethodAnnotation;
import dynamicProxy.CGLIB.annotation.ParameterAnnotation;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class CglibProxyFactory {

    public static Object getProxy(Class<?> clazz) {
        // 创建动态代理增强类
        Enhancer enhancer = new Enhancer();
        // 设置类加载器
        enhancer.setClassLoader(clazz.getClassLoader());
        // 设置被代理类
        enhancer.setSuperclass(clazz);
        // 设置方法拦截器
        enhancer.setCallback(new DebugMethodInterceptor());
        // 创建代理类
        return enhancer.create();
    }
}

/**
 * 自定义MethodInterceptor
 */
class DebugMethodInterceptor implements MethodInterceptor {

    /**
     * @param o           代理对象（增强的对象）
     * @param method      被拦截的方法（需要增强的方法）
     * @param args        方法入参
     * @param methodProxy 用于调用原始方法
     */
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        //调用方法之前，我们可以添加自己的操作
        System.out.println("before method -> " + method.getName());

        Class<?> superclass = o.getClass().getSuperclass();

        // 类注解
        ClassAnnotation classAnnotation = superclass.getDeclaredAnnotation(ClassAnnotation.class);
        System.out.println("ClassAnnotation value -> " + classAnnotation.value());
        System.out.println("ClassAnnotation type -> " + classAnnotation.type());

        // 属性注解
        Field[] fields = superclass.getDeclaredFields();
        for (Field field : fields) {
            FieldAnnotation fieldAnnotation = field.getDeclaredAnnotation(FieldAnnotation.class);
            System.out.println("FieldAnnotation value -> " + fieldAnnotation.value());
        }

        // 方法注解
        MethodAnnotation methodAnnotation = method.getDeclaredAnnotation(MethodAnnotation.class);
        System.out.println("MethodAnnotation value -> " + methodAnnotation.value());
        System.out.println("MethodAnnotation type -> " + methodAnnotation.type());

        // 参数注解
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (Annotation[] parameterAnnotation : parameterAnnotations) {
            for (Annotation annotation : parameterAnnotation) {
                if (annotation instanceof ParameterAnnotation) {
                    ParameterAnnotation param = (ParameterAnnotation) annotation;
                    System.out.println("ParameterAnnotation value -> " + param.value());
                }
            }
        }


        // before
        // 代理对象调用原始方法
        Object object = methodProxy.invokeSuper(o, args);
        // after


        //调用方法之后，我们同样可以添加自己的操作
        System.out.println("after method -> " + method.getName());
        return object;
    }
}
