/*
Lab number: 1
Name: Melvin Evans
Module Description: This program will ask the user for two numbers and then
                    multiplies then together. Along with this it shows the math
                    behind it.
Date: 1/30/2018
*/
import java.util.Scanner;
public class PrintMultipOfNDigits
{
   public static void main(String[]args)
   {
      boolean running = true;
      Scanner kb = new Scanner(System.in);
      description();
      do
      {
         run(kb);
         kb.nextLine();
         System.out.println();
         System.out.println();
         System.out.print("Do you want to run this program again? ");
         String answer = kb.nextLine();
         if(answer.equalsIgnoreCase("no"))
         {
            running = false;
         }
         else
         {
            running = true;
            System.out.println();
         }
      }
      while(running == true);
   }
   public static void description()
   {
      System.out.println("This program will take two number that you give it and multiply them together.");
      System.out.println("Along with that it will show you all of the numbers that you add together to get the answer.");
      System.out.println();
   }
   public static void run(Scanner kb)
   {
      int multiplicand;
      System.out.print("Enter the first integer: ");
      multiplicand = kb.nextInt();
      System.out.print("Enter the second integer: ");
      int multiplier = kb.nextInt();
      System.out.println();
      displayMultiples(multiplicand, multiplier);
      int[] multiplierDigits = new int[5];
      multiplierDigits = fillMultiplierDigits(multiplierDigits,multiplier);
      showWork(multiplierDigits, multiplicand);
      int answer = multiplicand * multiplier;
      System.out.printf("%11d", answer );
   }
   public static void displayMultiples(int multiplicand, int multiplier)
   {
      System.out.printf("%11d%n%5s%6d%n", multiplicand, "X", multiplier);
      for(int i = 0; i <= 11; i++)
      {
         System.out.print("-");
      }
      System.out.println();
   }
   public static int[] fillMultiplierDigits( int[] multiplierDigits, int multiplier)
   {
      for(int i = 0; i < 5; i++)
      {
         if(multiplier != 0)
         {
            multiplierDigits[i] = multiplier % 10;
            multiplier = multiplier / 10;
         }
         else
         {
            multiplierDigits[i] = 99;
         }
      }
      return multiplierDigits;
   }
   public static void showWork(int[] multiplierDigits, int multiplicand)
   {
      boolean actualNum = true;
      int[] shiftedMultiplicands = new int[5];
      for(int i = 0; i < 5; i++)
      {
         int power = 1;
         if(multiplierDigits[i] != 99)
         {
            for(int j = 0; j != i; j++)
            {
              power *= 10;
            }
            shiftedMultiplicands[i] = (multiplierDigits[i] * multiplicand) * power;
         }
         else
         {
            shiftedMultiplicands[i] = 99;
         }
      }
      for(int j = 0; j < 5; j++)
      {
         if( shiftedMultiplicands[j] != 99)
         {
            System.out.printf("%11d%n",shiftedMultiplicands[j]);
         }
      }
      for(int i = 0; i <= 11; i++)
      {
         System.out.print("-");
      }
      System.out.println();
   }
}