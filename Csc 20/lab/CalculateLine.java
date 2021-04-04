import java.util.*;
public class CalculateLine
{
   public static void main(String[] args)
   {
      System.out.println("This program calculates y coordinates for a line.");
      run();
   }
   public static void run ()
   {
      Scanner kb = new Scanner(System.in);
      System.out.print("Enter slope (m): ");
      int slope = kb.nextInt();
      System.out.print("Enter intercept (b): ");
      int intercept = kb.nextInt();
      int yValue;
      int xValue;
      do
      {
      System.out.print("Enter x: ");
      xValue = kb.nextInt();
      if(xValue != -1)
      {
         yValue = slope * xValue + intercept;
         System.out.println("f(" + xValue + ") = " + yValue);
      }
      }while(xValue != -1);
   }
   //version control
}