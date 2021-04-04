import java.util.Scanner;
public class practiceExam3
{

//convert cents to dollars, quarters, dimes, nickels, pennies
public static void main(String[]args)
{
   Scanner kb = new Scanner(System.in);
   converter(kb);  
}
public static void converter(Scanner kb)
{
   System.out.print("Enter the number of cents you want to convert: ");
   int cents = kb.nextInt();
   centsToDollars(cents);
}
public static void centsToDollars(int cents)
{
   int rCents= cents / 100;
   System.out.println(rCents + " dollars");
   int aCents = cents % 100;
   rCents = aCents / 25;
   System.out.println(rCents + " quarter");
   aCents = aCents % 25;
   rCents = aCents / 10;
   System.out.println(rCents + " dime");
   aCents = aCents % 10;
   rCents = aCents / 5;
   System.out.println(rCents + " nickel");
   aCents = aCents % 5;
   rCents = aCents / 1;
   System.out.println(rCents + " penny");
}
}