import java.util.*;
import java.io.*;
public class Exam
{
   public static void main(String[]args)
   {
      /*Stack s = new Stack();
      s.push(3);
      s.push(-5);
      s.push(1);
      s.push(2);
      s.push(-4);
      StackQueue(s);*/
       //System.out.println(mystery3(6));
       //System.out.println(mystery3(17));
       //System.out.println(mystery3(259));
       //System.out.println(mystery3(977));
       //System.out.println(mystery3(-479));
       One var1 = new Two();
       One var2 = new Three();
       One var3 = new Four();
       Three var4 = new Four();
       Object var5 = new Three();
       Object var6 = new One();
       //var1.method1();
       //var2.method1();
       //var3.method1();
       //var4.method1();
       //var5.method1(); 
       //var6.method1();
       //var4.method2();
       //var4.method3();
       //((Two)var1).method2();
       //((Three)var1).method2();
       //((Two)var1).method3();
       //((Four)var2).method1();
       //((Four)var3).method1();
       //((Four)var4).method3();
       //((One)var5).method1();
       //((Four)var5).method2();
       //((Three)var5).method2();
       //((One)var6).method1();
       //((One)var6).method2();
       //((Two)var6).method3();
      //System.out.println(mystery4("varriable", 'r'));   
   }
   public static void StackQueue(Stack s)
   {
      Stack tmp = new Stack();
      int[] a = new int[s.size()];
      int i = 0;
      int c = 0;
      do
      {
         if(s.peek() > 0)
         {
            a[i] = s.pop();
            i++;
         }
         else
         {
            tmp.push(s.pop());
         }
        
      }while(s.empty() != true);
      while(a[c] != null)
      {
         tmp.push(a[c]);
         c++;
      }
   }
    public static int mystery3(int n)         
    {           
    if (n < 0)
    return -mystery3(-n);
    else if (n < 10) 
    return n; 
    else
    return mystery3(n/10 + n % 10);
    } 
   public static String mystery4(String s, char ch) {    
   if (s.length() < 2) 
   {        
   return s;    
   } 
   else 
   {        
   char first = s.charAt(0);        
   if (first == ch && first == s.charAt(1)) 
   {            
   return mystery4(s.substring(1), ch);        
   } 
   else 
   {            
   return s.charAt(0) + mystery4(s.substring(1), ch);        
   }    
   } 
   }
}