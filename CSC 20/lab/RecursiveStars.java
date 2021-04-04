public class RecursiveStars
{
   public static void main(String[] args)
   {
   int n = 5;
   recursion(n);
   }
   public static void recursion(int n)
   {
      int max = n;
      printStars(n);
      if(n >= 1)
      {
         n--;
         if(n!=0)
         {
            recursion(n);
            printUp(n, max);
         }
      }
   }
   public static void printUp(int n, int max)
   {
         int x = n;
         if (x == max) { 
         // base case; just print one star 
         System.out.println("*"); } 
         else { 
         // recursive case; print one more star 
         System.out.print("*"); 
         printUp(x + 1, max); 
         } 
   }
   public static void printStars(int n) { 
         int x = n;
         if (x == 1) { 
         // base case; just print one star 
         System.out.println("*"); } 
         else { 
         // recursive case; print one more star 
         System.out.print("*"); 
         printStars(x - 1); 
         } 
   }
}