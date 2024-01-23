public class Main {

    public static void method1() {
        method2();
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

        String name = stackTraceElements[1].getMethodName();

        System.out.println(name);
    }

    public static void method2() {
        method3();
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

        String name = stackTraceElements[1].getMethodName();

        System.out.println(name);
    }

    public static void method3() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

        String name = stackTraceElements[1].getMethodName();

        System.out.println(name);
    }

    public static void main(String[] args) {
        method1();
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

        String name = stackTraceElements[1].getMethodName();

        System.out.println(name);
    }
}