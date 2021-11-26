package dynamicProxy.CGLIB.service;

import dynamicProxy.CGLIB.annotation.ClassAnnotation;
import dynamicProxy.CGLIB.annotation.FieldAnnotation;

@ClassAnnotation(value = "class annotation", type = "class")
public class ExtendService extends Service {
    @FieldAnnotation("field annotation 1")
    String message = "This is message.";

    @FieldAnnotation("field annotation 2")
    String result = "This is result.";
}
