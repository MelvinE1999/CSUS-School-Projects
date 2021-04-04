// This class provides method to test if a string is a Palindrome using Stack/Queue ADTs.
// Csc20 lab 7 assignment
// Csc20's student to provide more inputs here.
import java.util.Stack;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
class Palindrome {
    // This is a main method to test checkPalindrome method
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String inputString = new String("");
        int error;
        int count = 0;
        String checkPal = "";
        do {
         error = 0;
         if(count > 0)
         {
            in.nextLine();
         }
         // please put your code to test checkPalindrome method here 
         System.out.print("Please enter a string of characters: ");
         checkPal = in.nextLine();
         if(checkPal.length() == 0)
         {
            System.out.println("There was no String.");
         }
         else
         {
            error = checkPalindrome(checkPal);
            if(error > 0)
            {
               System.out.println("The given string is not a palindrome, since the symbol at position " + error);
               System.out.println("from the left is different from the symbol at position " + error + " from the right.");
            }
            else
            {
               System.out.println("The given String is a palindrome.");
            }
         }
            System.out.print("Want to examine another string?(y/n): ");
            inputString = in.next();
            count++;
          } while ( inputString.equals("y"));   
        System.out.print("Bye!"); 
    }
    // This is checkPalindrome method. It checks if an input string is Palindrome or not.
    // It returns 0 if a string is a Palindrome. Otherwise, it returns a position of a character where it finds 
    // a different value.
    // Pre-Condition: string must not be null. 
    // Post-Condition: Return 0 if input string is a Palindrome. Return a positive number indicate the location where
    // a difference found. 
    public static int checkPalindrome(String strValue) {  
        Stack<Character> stack = new Stack<Character>();
        Queue<Character> queue = new LinkedList<Character>();
        int indexVal = 0;
        // check if string is null. If it is null, return a -1
        if(strValue.length() == 0)
        {
            return -1;
        } 
        
        // normalize the string values to lower case, remove spaces
        strValue = strValue.toLowerCase().replaceAll("\\W", "");

        // store data on stack/queue adts first
        String character = strValue;
        int space = 0;
        //fix this
        if((character.length() - 1) == 0)
        {
           stack.push(character.charAt(space));
           queue.add(character.charAt(space));
        }
        else
        {
           while(character.length() != space)
           {
            stack.push(character.charAt(space));
            queue.add(character.charAt(space));
            space++;
           }
        }
         
        // loop: comparing, retrieving text, terminate loop if stack is emptied or found a difference
        do
        {
           indexVal++;
           if(stack.pop() != queue.peek())
           {
               return indexVal;
           }
           queue.remove();
        }while(stack.empty() == false);
        return 0;
    }
}