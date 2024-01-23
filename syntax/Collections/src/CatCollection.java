import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CatCollection {
    public static void main(String[] args) {
        Set<Cat> cats = createCats();

        Iterator<Cat> iterator = cats.iterator();

        if (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }

        printCats(cats);
    }

    public static Set<Cat> createCats() {
        Set<Cat> cats = new HashSet<>();
        cats.add(new Cat());
        cats.add(new Cat());
        cats.add(new Cat());

        return cats;
    }

    public static void printCats(Set<Cat> cats) {
        for (Cat cat : cats) {
            System.out.println(cat);
        }
    }

    public static class Cat {

    }
}
