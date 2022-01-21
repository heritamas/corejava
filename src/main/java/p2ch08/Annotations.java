package p2ch08;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Annotations {


    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        Annotated a = new Annotated();

        // invoke methods of a with arguments coming from annotations
        System.out.println(a.methodA("Csovi"));
        Class<? extends Annotated> clazz = a.getClass();
        for (Method m : clazz.getDeclaredMethods()) {
            Foo annotation = m.getAnnotation(Foo.class);
            System.out.println("method name: " + m.getName());
            if (annotation != null) {
                System.out.println(a.methodA(annotation.object()));
            }
        }
    }
}
