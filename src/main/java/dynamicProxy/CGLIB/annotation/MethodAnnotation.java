package dynamicProxy.CGLIB.annotation;


import java.lang.annotation.*;

@Inherited  // 可继承
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodAnnotation {
    String value() default "";

    String type() default "";
}
