// Define objects abilities and roles
interface Student {
    String getName();
}

public class StudentImpl implements Student {
    private String name;

    public StudentImpl(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Student student = new StudentImpl("ban");
        System.out.println(student.getName());
    }
}
