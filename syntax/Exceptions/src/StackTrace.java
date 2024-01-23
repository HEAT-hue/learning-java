public class StackTrace {
    public static void main(String[] args) {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();

        for (StackTraceElement el: elements) {
            System.out.println(el.getMethodName());
        }
    }
}
