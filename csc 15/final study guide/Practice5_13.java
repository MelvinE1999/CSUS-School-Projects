public class Practice5_13
{
   public static void main(String[] args)
   {
      if(consecutive(8,6,7) == true)
      {
         System.out.println("They are consecutive");
      }
      else
      {
         System.out.println("They are not consecutive");
      }
      if(consecutive(1,7,6) == true)
      {
         System.out.println("They are consecutive");
      }
      else
      {
         System.out.println("They are not consecutive");
      }
      if(consecutive(1,2,3) == true)
      {
         System.out.println("They are consecutive");
      }
      else
      {
         System.out.println("They are not consecutive");
      }
   }
   public static boolean consecutive (int a, int b, int c)
   {
       if((b == a+1 && c==b+1) || (c == a+1 && b==c+1) || (a == b+1 && c==a+1)|| (c == b+1 && a==c+1))
       {
           return true;
       }
       else if((a == c+1 && b==a+1)|| (b == c+1 && a==b+1))
               {
           return true;
       }
       else
               {
           return false;
       }
   }
}