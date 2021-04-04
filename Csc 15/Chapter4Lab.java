/*
Melvin Evans
10/5/17
This program will convert your birthay into roman numerals.
*/
import java.util.Scanner;
public class Chapter4Lab
{
   public static void main(String[] args)
   {
      description();
      start();
   }
   
   public static void description()
   {
      System.out.println(" ********    This program converts your birthday to its equivalent roman numbers    ********");
   }
   
   public static void start()
   {
      Scanner kb = new Scanner(System.in);
      System.out.print("How many times do you want to repeat this program: ");
      int rep = kb.nextInt();
      for(int i = 1; i <= rep; i++)
      {
      kb.nextLine();
      birthday(kb);
      }
   }
   
   public static void birthday(Scanner kb)
   {
      System.out.print("Enter your name: ");
      String name = kb.nextLine();
      System.out.println("Hi " + name + " Let's start");
      System.out.print("Enter the month of your birthday: ");
      int interger = kb.nextInt();
      roman(interger);
      String month = roman(interger);
      System.out.print("Enter the day of your birthday: ");
      interger = kb.nextInt();
      roman(interger);
      String day = roman(interger);
      System.out.print("Enter the year of your birthday: ");
      interger = kb.nextInt();
      roman(interger);
      String year = roman(interger);
      System.out.println("Your birthday in Roman is: " + month + "/" + day + "/" + year);
   }
   
   public static String roman(int interger)
   {
      String numeral=("");
      for(int i = interger; i >= 0; i--)
      {
         if(interger >= 1000)
         {
            numeral = numeral.concat("M");
            interger = interger - 1000;
         }
         else if(interger >= 900)
         {
            numeral = numeral.concat("CM");
            interger = interger - 900;
         }
         else if(interger >= 500)
         {
            numeral = numeral.concat("D");
            interger = interger - 500;
         }
         else if(interger >= 400)
         {
            numeral = numeral.concat("CD");
            interger = interger - 400;
         }
         else if(interger >= 100)
         {
            numeral = numeral.concat("C");
            interger = interger - 100;
         }
         else if(interger >= 90)
         {
            numeral = numeral.concat("XC");
            interger = interger - 90;
         }
         else if(interger >= 50)
         {
            numeral = numeral.concat("L");
            interger = interger - 50;
         }
         else if(interger >= 40)
         {
            numeral = numeral.concat("XL");
            interger = interger - 40;
         }
         else if(interger >= 10)
         {
            numeral = numeral.concat("X");
            interger = interger - 10;
         }
         else if(interger >= 9)
         {
            numeral = numeral.concat("IX");
            interger = interger - 9;
         }
         else if(interger >= 5)
         {
            numeral = numeral.concat("V");
            interger = interger - 5;
         }
         else if(interger >= 4)
         {
            numeral = numeral.concat("IV");
            interger = interger - 4;
         }
         else if(interger >= 1)
         {
            numeral = numeral.concat("I");
            interger = interger - 1;
         }
         
     }
     return numeral;
   }
   
}