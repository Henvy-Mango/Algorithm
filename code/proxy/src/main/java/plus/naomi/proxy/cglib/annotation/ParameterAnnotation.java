package plus.naomi.proxy.cglib.annotation;

import java.lang.annotation.*;


/**
 * @author Naomi
 * @description
 * @date 2021/11/26 15:45
 */
@Inherited  // 可继承
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ParameterAnnotation {
    String value() default "";
}
