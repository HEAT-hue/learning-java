import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

        String grandFatherName = r.readLine();
        Cat catGrandFather = new Cat(grandFatherName);

        String grandMotherName = r.readLine();
        Cat catGrandMother = new Cat(grandMotherName);

        String fatherName = r.readLine();
        Cat catFather = new Cat(fatherName, catGrandFather, null);

        String motherName = r.readLine();
        Cat catMother = new Cat(motherName, null, catGrandMother);

        String sonName = r.readLine();
        Cat catSon = new Cat(sonName, catFather, catMother);

        String daughterName = r.readLine();
        Cat catDaughter = new Cat(daughterName, catFather, catMother);

        System.out.println(catGrandFather);
        System.out.println(catGrandMother);
        System.out.println(catFather);
        System.out.println(catMother);
        System.out.println(catSon);
        System.out.println(catDaughter);
    }

    public static class Cat {
        private String name;
        private Cat father;
        private Cat mother;

        Cat(String name) {
            this.name = name;
        }

        Cat(String name, Cat father, Cat mother) {
            this.name = name;
            this.father = father;
            this.mother = mother;
        }

        @Override
        public String toString() {
            if (father == null && mother == null) {
                return ("The cat's name is " + this.name + ", no mother, no father");
            }

            if (father != null && mother == null) {
                return ("The cat's name is " + this.name + ", no mother, " + this.father.name + " is the father");
            }

            if (mother != null && father == null) {
                return ("The cat's name is " + this.name + ", " + this.mother.name + " is the mother, no father");
            }

            return ("The cat's name is " + this.name + ", " + this.mother.name + " is the mother, " + this.father.name + " is the father");
        }
    }
}
