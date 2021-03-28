public class PracticeNum4_14
{
   public static void main(String[] args)
   {
      System.out.println(pow2(2.0,4));
      System.out.println(pow2(2.0,-2));
   }
   public static double pow2(double a, int p)
   {
      double finalBase = a;
      double fraction = 0;
      if(p == 0)
      {
         return 1;
      }
      if(p < 0)
      {
         for(int i = -2; i >= p; i--)
         {
            finalBase *=  a;
         }
         fraction = 1/ finalBase;
         return fraction;
      }
      for(int i = 2; i <= p; i++)
      {
         finalBase *=  a;
      }
      return finalBase;
   }
}