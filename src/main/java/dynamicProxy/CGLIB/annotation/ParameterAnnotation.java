package dynamicProxy.CGLIB.annotation;

import java.lang.annotation.*;


@Inherited  // 可继承
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ParameterAnnotation {
    String value() default "";
}
