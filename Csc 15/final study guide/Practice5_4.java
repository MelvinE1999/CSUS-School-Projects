import java.util.*;
public class Practice5_4
{
   public static void main(String[] args)
   {
      randomX();
   }
   public static void randomX()
   {
      Random num = new Random();
      int wordCount = 0;
      do
      {
         wordCount = num.nextInt(15) + 5;
         for(int i = 1; i <= wordCount; i++)
         {
            System.out.print("x");
         }
         System.out.println();
      }
      while(wordCount < 16);
   }
}