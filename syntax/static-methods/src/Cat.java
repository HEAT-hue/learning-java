import java.util.ArrayList;

public class Cat {

//    Static list of cats
    public static ArrayList<Cat> cats = new ArrayList<>();

//    Cat constructor
    public Cat() {
        cats.add(this);
    }

//    Main method
    public static void main(String[] args) {
        for (int i = 0; i < 10; ++i) {
            Cat cat = new Cat();
        }

        printCats();
    }

//    print list of available cats
    public static void printCats() {
        for (Cat cat: cats) {
            System.out.println(cat);
        }
    }
}