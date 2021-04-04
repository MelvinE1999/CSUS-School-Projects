import java.util.Scanner;
public class FibonacciSequence
{
       public static void main(String[]args)
       {
           Scanner kb = new Scanner(System.in);
           System.out.println("This program lists the Fibonacci sequence.");
           int maxValue;
           System.out.print("Max value? ");
           maxValue = kb.nextInt();
           int num1 = 0;
           int num2 = 1;
           System.out.print(num1 + ", " + num2 + ", ");
           int addedNum;
           do
           {
               addedNum = num1 + num2;
               System.out.print(addedNum + ", ");
               num1 = num2;
               num2 = addedNum;
           }while(num1 + num2 <= maxValue);
       }
}