public class Task1 {
    String name;
    int age = 7;
    int weight = 5;
    String color = "red";
    String address;

    public void initialize(String name) {
        this.name = name;
    }
    public void initialize(String name, int weight, int age) {
        this.name = name;
        this.weight = weight;
        this.age = age;
    }
    public void initialize(String name,  int age) {
        this.name = name;
        this.age = age;
    }
    public void initialize(int weight,  String color) {
        this.weight = weight;
        this.color = color;
    }
    public void initialize(int weight,  String color, String address) {
        this.weight = weight;
        this.color = color;
        this.address = address;
    }

    public static void main(String[] args) {

    }
}
