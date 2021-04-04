public class Practice5_1
{
   public static void main(String[] args)
   {
      showTwos(51);
      showTwos(4);
      showTwos(40);
   }
   public static void showTwos(int num)
   {
      int factorCount = 0;
      System.out.print("This is the factors of two inside of " + num + ": ");
      for(int i = 2;i <= num; i = i + 2)
      {
         if(num % i == 0)
         {
            System.out.print(i + ", ");
            factorCount++;
         }
      }
      if(factorCount == 0)
      {
         System.out.print("there is no factors of two");
      }
      System.out.println();
   }
}