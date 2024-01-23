import java.util.ArrayList;
import java.util.Arrays;

public class Family {
    public static void main(String[] args) {
        Human son1 = new Human("Lucy", true, 24, new ArrayList<>());
        Human son4 = new Human("Chioma", true, 24, new ArrayList<>());
        Human son5 = new Human("Emmanuel", true, 24, new ArrayList<>());

        Human f1 = new Human("Elias", true, 24, new ArrayList<>(Arrays.asList(son1, son5, son4)));
        Human m1 = new Human("Nwamaka", true, 24, new ArrayList<>(Arrays.asList(son1, son5, son4)));

        Human gf = new Human("Raphael", false, 21, new ArrayList<>(Arrays.asList(f1)));
        Human gm = new Human("Ifeoma", true, 22, new ArrayList<>(Arrays.asList(f1)));
        Human gf1 = new Human("Roselyn", true, 18, new ArrayList<>(Arrays.asList(m1)));
        Human gm1 = new Human("Innocent", true, 20, new ArrayList<>(Arrays.asList(m1)));

        System.out.println(gf);
        System.out.println(gm);
        System.out.println(gf1);
        System.out.println(gm1);
        System.out.println(f1);
        System.out.println(m1);
        System.out.println(son1);
        System.out.println(son4);
        System.out.println(son5);

    }

    public static class Human {
        //write your code here
        String name;
        boolean sex;
        int age;
        ArrayList<Human> children;

        public Human(String name, boolean sex, int age, ArrayList<Human> children) {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.children = children;
        }

        public String toString() {
            String text = "";
            text += "Name: " + this.name;
            text += ", sex: " + (this.sex ? "male" : "female");
            text += ", age: " + this.age;

            int childCount = this.children.size();
            if (childCount > 0) {
                text += ", children: " + this.children.get(0).name;

                for (int i = 1; i < childCount; i++) {
                    Human child = this.children.get(i);
                    text += ", " + child.name;
                }
            }
            return text;
        }
    }
}
