package ch08.ex14;

import java.util.Objects;
import java.util.function.Supplier;

public class NonNullMethod {
	public static void print(String str) {
        Objects.requireNonNull(str, createNullMessageWithInvoker("str"));
        System.out.println(str);
    }

    public static void main(String[] args) {
        print("non-null");
        try {
            print(null);
        } catch (NullPointerException e) {
            System.out.println("NPE: " + e.getMessage());
        }
    }

    private static Supplier<String> createNullMessageWithInvoker(String paramName) {
        return () -> {
            StackTraceElement[] elements = new RuntimeException().getStackTrace();
            StackTraceElement invoker = elements[4]; // lambda <- createNMWI() <- print() <- invoker
            return paramName + " is null, invoked from " + invoker.getClassName() + "." + invoker.getMethodName();
        };
    }
}
