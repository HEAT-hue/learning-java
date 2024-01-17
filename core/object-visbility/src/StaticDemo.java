class A {
    int name;

    public  void show(){
        System.out.println("In class A show");
    }

    class B {
        void config() {
            System.out.println("In config of B");
        }
    }
}


public class StaticDemo {
    public static void main(String[] args) {
        A obj = new A();
        obj.show();

    }
}
