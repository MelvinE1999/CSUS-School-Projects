/*
Melvin Evans
9/21/17
This program is a converter that will convert any time to seconds, minutes, hours.
*/
import java.util.Scanner;
public class Chapter3Lab 
{
   public static void main(String [] args)
   {
   Scanner kb = new Scanner(System.in);
   inputBirthday(kb);      
   }
public static void inputBirthday(Scanner kb){
    System.out.print("On what day of the month were you born? ");
    int day = kb.nextInt();
    System.out.print("What is the name of the month in which you were born? ");
    String month = kb.nextLine();
    kb.nextLine();
    System.out.print("During what year were you born? ");
    int year = kb.nextInt();
    System.out.print("You were born on " + month + " " + day + ", " + year + ". You're mighty old!");
}
}