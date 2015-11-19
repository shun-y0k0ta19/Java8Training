package ch08.ex12;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import junit.framework.TestCase;

public class TestClassLoader {
	public static void main(String[] args) throws ReflectiveOperationException {
        Class<?> cls = Class.forName("ch08.ex12.Test.java");
        Object obj = cls.newInstance();
        int count = 0;
        for (Method m : cls.getMethods()) {
            for (Annotation a : m.getAnnotations()) {
                if (a.annotationType() == TestCase.class) {
                    count++;
                    System.out.println(m.getName() + " method: " + m.invoke(obj, 1));
                }
            }
        }
        System.out.println("Invoked " + count + " method(s).");
    }
}
