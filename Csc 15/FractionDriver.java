import java.util.*;
import java.io.*; 
public class FractionDriver
{
   public static void main(String[] args)
   {
      Scanner kb = new Scanner (System.in);
      Fraction[] fractions = new Fraction [10];
      fillArray(fractions, kb);
      print(fractions);
      math(fractions);
   }
   public static void fillArray(Fraction[] fractions, Scanner kb)
   {
      for (int i = 0; i < fractions.length; i++)
      {
      System.out.print("Enter the nuem: ");
      int n = kb.nextInt();
      System.out.print("Enter the deno: ");
      int d = kb.nextInt();
      Fraction f1 = new Fraction(n,d);
      fractions[i] = f1;
      }
   }
   public static void print(Fraction[] fractions)
   {
      for(int i = 0; i < fractions.length; i++)
      {
         System.out.println(fractions[i].toString());
      }
   }
   public static void math(Fraction[] fractions)
   {
      Fraction f1 = fractions[0].add(fractions[1]);
      System.out.println(fractions[0] + " + " + fractions[1] + " = " + f1);
      Fraction f2 = fractions[1].multiply(fractions[2]);
      System.out.println(fractions[1] + " * " + fractions[2] + " = " + f2);
      Fraction f3 = fractions[3].divide(fractions[4]);
      System.out.println(fractions[3] + " / " + fractions[4] + " = " + f3);
      Fraction f4 = fractions[4].subtract(fractions[0]);
      System.out.println(fractions[4] + " - " + fractions[0] + " = " + f4);
      Fraction f5 = fractions[3].simplify();
      System.out.println(fractions[3] + " = " + f5);
      boolean result= fractions[0].equals(fractions[1]);
      if( result == true)
      {
         System.out.println("The two fractions are equal");
      }
      else
      {
         System.out.println("The two fractions are not equal");
      }
   }  
}