public class Circle {

    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double calculateArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    public static void main(String[] args) {
       
        double radius ;
        Circle circle = new Circle(radius);

        double area = circle.calculateArea();
   
    }
}
