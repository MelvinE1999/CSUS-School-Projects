public class CircleDriver 
{
   public static void main(String[] args)
   {
      Circle c1 = new Circle(2,3,5);
      Circle c2 = new Circle();
      c1.setX(10);
      c1.setY(20);
      c1.setradius(6);
      System.out.println("x: " + c1.getx());
      System.out.println("y: " + c1.gety());
      c1.equals(c2);
      System.out.println(c1.toString());
      double area = c1.getArea();
      System.out.println("Area: " + area);
      int diameter = c1.getDiameter();
      System.out.println("Diameter: " + diameter);
      double circumference = c1.getCircumference();
      System.out.println("Circumference: " + circumference);
   }
}