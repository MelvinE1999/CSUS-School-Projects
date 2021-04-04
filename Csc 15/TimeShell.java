/*
Melvin Evans
9/28/17
This code makes a time converter where you input your name and the time that you wwant to get converted.
*/

import java.util.Scanner;
public class TimeShell
{
   //declare all your constants
   public static final int SEC_PER_MINUTE = 60;
   public static final int SEC_PER_HOUR = 3600;
   public static void main(String[] args)
   {
       Scanner kb = new Scanner(System.in);
       start(kb);            
        
   }
   /* call the method description
   prompt the user for the name and the number of the times that they want to use this program
   get the hours, minutes, seconds
   call the method hoursToSec
   call the method minToSec
   calculate the total seconds
   output the total seconds
   call the method minSec to calulate the minutes and sec
   call the method hourMinSec*/
   public static void start(Scanner kb)
   {
       description();
       System.out.print("How many times do you want to use the app?");
       double numRun = kb.nextDouble();
       for( int run = 1; run <= numRun; run++)
       {
         kb.nextLine();
         System.out.print("What is your name?");
         String name = kb.nextLine();
         System.out.println("Hi " + name + " Lets start!!");
         System.out.println();
         System.out.print("Enter the number of the hours:");
         int hour = kb.nextInt();
         System.out.print("Enter the number of the minutes:");
         int minutes = kb.nextInt();
         System.out.print("Enter the number of the seconds:");
         int seconds = kb.nextInt();
         System.out.println(hour + " Hours, " + minutes + " Minutes, and " + seconds + " Seconds is:");
         int sec = hourToSec(hour);
         int secs = minToSec(minutes);
         int totalSeconds = sec + secs + seconds;
         System.out.println();
         System.out.println(totalSeconds + " Seconds");
         minSec(totalSeconds);
         hourMinSec(totalSeconds);
         System.out.println();
         }
   }
   /*This method get the totlal number of the seconds and 
   finds the number of the minutes and sec and outputs the result*/
   public static void minSec(int totalSeconds)
   {
     int secM = totalSeconds / SEC_PER_MINUTE;
     int secS = totalSeconds % SEC_PER_MINUTE;
     System.out.println( secM + " Minutes " + secS + " Seconds");
      
      //output the result
     
   }
   /*This method get the total number of the seconds and calculates the number of hours, minutes and second, then outputs the result*/
   public static void hourMinSec(int totalSeconds)
   {
     int secH = totalSeconds / SEC_PER_HOUR;
     int secM = totalSeconds % SEC_PER_HOUR / SEC_PER_MINUTE ;
     int secS = totalSeconds % SEC_PER_HOUR % SEC_PER_MINUTE ;
     System.out.println( secH + " Hours " + secM + " Minutes " + secS + " Seconds");
   } 
   /*calulates the number of the seconds in the given hours and returns the value*/  
   public static int hourToSec(int hours)
   {
       int sec = hours * SEC_PER_HOUR;
       return sec;         
   }
   /*This method calculates the number of the seconds in the given number of minutes*/
   public static int minToSec(int min)
   {
      int secs = min * SEC_PER_MINUTE;
      return secs;
   }
   /*outputs the description of the program*/
   public static void description()
   {
      for (int percent = 1; percent <= 58; percent ++)
      {
         System.out.print("%");
      }
      System.out.println();
      System.out.println("This program converts hours, minutes, second to second and display in on the screen.");
      for (int percent = 1; percent <= 58; percent ++)
      {
         System.out.print("%");
      }
      System.out.println();

   }
   
 }  