import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class AnimalSet {
    public static void main(String[] args) {
        Set<Cat> cats = createCats();
        Set<Dog> dogs = createDogs();

        Set<Object> pets = join(cats, dogs);
        printPets(pets);

        removeCats(pets, cats);
        printPets(pets);
    }

    public static Set<Cat> createCats() {
        HashSet<Cat> result = new HashSet<Cat>();

        result.add(new Cat());
        result.add(new Cat());
        result.add(new Cat());
        result.add(new Cat());

        return result;

    }

    public static Set<Dog> createDogs() {
        HashSet<Dog> result = new HashSet<Dog>();

        result.add(new Dog());
        result.add(new Dog());
        result.add(new Dog());

        return result;
    }

    public static Set<Object> join(Set<Cat> cats, Set<Dog> dogs) {
        Set<Object> animals = new HashSet<>();
        Iterator<Cat> catIterator = cats.iterator();
        Iterator<Dog> dogIterator = dogs.iterator();

        while (catIterator.hasNext()) {
            Cat cat = catIterator.next();
            animals.add(cat);
        }

        while (dogIterator.hasNext()) {
            Dog dog = dogIterator.next();
            animals.add(dog);
        }

        return animals;
    }

    public static void removeCats(Set<Object> pets, Set<Cat> cats) {
        //write your code here
        Iterator<Cat> iterator = cats.iterator();

        while (iterator.hasNext()) {
            Cat cat = iterator.next();
            pets.remove(cat);
        }

    }

    public static void printPets(Set<Object> pets) {
        for (Object pet : pets) {
            System.out.println(pet);
        }
    }

    //write your code here
    public static class Dog {

    }

    public static class Cat {

    }
}
