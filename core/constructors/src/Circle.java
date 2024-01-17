public  class Circle {
//    All uninitialized variables points to null;
    public Color color;

    public Circle() {
        this.color = new Color();
    }

    public static void main(String[] args) {
        Circle circle = new Circle();
        circle.color.setDescription("Red");
        System.out.println(circle.color.getDescription());
    }
     public class Color {
         String description;

         public  String getDescription() {
             return this.description;
         }

         public void setDescription(String description) {
             this.description = description;
         }
     }
}
