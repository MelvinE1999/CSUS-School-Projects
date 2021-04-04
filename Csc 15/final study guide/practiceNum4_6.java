import java.util.*;
public class practiceNum4_6
{
   public static void main(String[] args)
   {
     printRange(2,7);
     printRange(19,11);
     printRange(5,5);
   }
   public static void printRange(int a, int b)
   {
      if(a < b)
      {
         for(int i = a; i <= b; i++)
         {
            System.out.print(i + " ");
         }
      }  
      for(int i = a; i >= b;i--)
      {
         System.out.print(i + " ");
      }
      System.out.println();
   }
}