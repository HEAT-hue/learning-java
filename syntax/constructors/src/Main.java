public class Main {


    public static void main(String[] args) {
        Man man = new Man("m", 12, "s");
        Man man1 = new Man("m", 12, "s");

        Woman woman = new Woman("m", 12, "s");
        Woman woman1 = new Woman("m", 12, "s");

        man.display();
        man1.display();

        woman1.display();
        woman.display();
    }

    public static class Man {
        String name;
        int age;
        String address;

        public Man(String name, int age, String address) {
            this.name = name;
            this.age = age;
            this.address = address;
        }

        void display() {
            System.out.println(this.name + " " + this.age + " " + this.address);
        }
    }

    public static class Woman {
        String name;
        int age;
        String address;

        public Woman(String name, int age, String address) {
            this.name = name;
            this.age = age;
            this.address = address;
        }

        void display() {
            System.out.println(this.name + " " + this.age + " " + this.address);
        }
    }
}