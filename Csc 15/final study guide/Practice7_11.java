import java.util.*;
public class Practice7_11
{
   public static void main(String[] args)
   {
      Scanner kb = new Scanner(System.in);
      System.out.print("Enter the number of numbers: ");
      int amountOfNumbers = kb.nextInt();
      int[] nums = new int [amountOfNumbers];
      for(int i = 0; i < amountOfNumbers; i++)
      {
         System.out.print("Enter the num: ");
         nums[i] = kb.nextInt();
      }
      double averageNum = average(nums, amountOfNumbers);
      System.out.println("The average of the numbers = " + averageNum);
   }
   public static double average(int[] nums, int amountOfNumbers)
   {
      double averages = 0;
      for(int i = 0; i < amountOfNumbers; i++)
      {
         averages += nums[i];
      }
      return averages / amountOfNumbers;
   }
}