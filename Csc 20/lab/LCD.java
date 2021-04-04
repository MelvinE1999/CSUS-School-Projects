import java.util.*;
public class LCD
{
   public static void main(String[]args)
   {
      run();
   }
   public static void run ()
   {
      Scanner kb = new Scanner(System.in);
      System.out.print("What is the first number?");
      int num_1 = kb.nextInt();
      System.out.print("What is the second number?");
      int num_2 = kb.nextInt();
      kb.nextLine();
      System.out.print("The lowest common denominator of " + num_1 + "/" + num_2 + " is: ");
      boolean advanced = true;
      do
      {
         if(num_1 % 3 == 0 & num_2 % 3 == 0)
         {
            num_1 = num_1 / 3;
            num_2 = num_2 / 3; 
         }
         else if(num_1 % 2 == 0 & num_2 % 2 == 0)
         {
            num_1 = num_1 / 2;
            num_2 = num_2 / 2; 
         }
         else
         {
            advanced = false;
         }
      }while(advanced == true);
      System.out.print(num_2);
   }
}