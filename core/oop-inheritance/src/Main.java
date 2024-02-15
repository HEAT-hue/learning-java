public class Main {
    public static void main(String[] args) {
        Pet pet = new Pet();
        Cat cat = new Cat();

        System.out.println(pet.name);
        System.out.println(cat.name);
    }

    static class Pet {
        final String name = "Pet";

        public final String getName() {
            return this.name;
        }

        public void setName(String name) {
//            this.name = name;
        }
    }

    static class Cat extends Pet {

        @Override
        public void setName(String name) {
            this.name = "Cat";
        }
    }
}