package plus.naomi.proxy.cglib.service;

import plus.naomi.proxy.cglib.annotation.ClassAnnotation;
import plus.naomi.proxy.cglib.annotation.FieldAnnotation;

/**
 * @author Naomi
 * @description
 * @date 2021/11/26 15:45
 */
@ClassAnnotation(value = "class annotation", type = "class")
public class ExtendService extends Service {
    @FieldAnnotation("field annotation 1")
    String message = "This is message.";

    @FieldAnnotation("field annotation 2")
    String result = "This is result.";
}
