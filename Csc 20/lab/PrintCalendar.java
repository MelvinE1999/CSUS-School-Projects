/*********************************************************
*Lab number: 2                                           *
*Name: Melvin Evans                                      *
*Section number: 13                                      *
*Module: This program will take a year given and print   *
*the calendar for it. Along with that if you put the     *
*current year it will put a dash underneath the current  *
*month.                                                  *
*Date: 2/14/18                                           *
*********************************************************/
import java.util.Calendar;
public class PrintCalendar
{
   public static void main(String[]args)
   {
      printCal(args);
   }
   //Takes all the methods in order to make it print the calendar properly
   public static void printCal(String[] args)
   {
      int year;
      if(args.length == 0)
      {
         year = Calendar.getInstance().get(Calendar.YEAR);
      }
      else
      {
         year = Integer.parseInt(args[0]);
      }
      System.out.printf("%14d", year);
      if(determineLeapYear(year) == true)
      {
         System.out.print(" (leapYear)");
         System.out.println();
      }
      if(year ==  Calendar.getInstance().get(Calendar.YEAR))
      {   
         System.out.println();
         for(int i = 0; i != 26; i++)
         {
            System.out.print("-");
         }
      }
      System.out.println();
      printMonth(year);
   }
   //takes the given year and determines if that is a leap year
   public static boolean determineLeapYear(int year)
   {
      boolean leapYear;
      if(year % 4 != 0)
      {
         leapYear = false;
      } 
      else if(year % 100 != 0)
      {
         leapYear = true;
      }
      else if( year % 400 != 0)
      {
         leapYear = false;
      }
      else 
      {
         leapYear = true;
      }
      return leapYear;
   }
   //formats and prints all of the months in the given year
   public static void printMonth(int year)
   {
      int numOfMonth;
      String month = "";
      String space = " ";
      for(int i = 1; i != 13; i++)
      {
         numOfMonth = i;
         switch(numOfMonth)
         {
            case 1:
               month = "Janurary";
               break;
            case 2:
               month = "Feburary";
               break;
            case 3:
               month = "March";
               break;
            case 4:
               month = "April";
               break;
            case 5:
               month = "May";
               break;
            case 6:
               month = "June";
               break;
            case 7:
               month = "July";
               break;
            case 8:
               month = "August";
               break;
            case 9:
               month = "September";
               break;
            case 10:
               month = "October";
               break;
            case 11:
               month = "November";
               break;
            case 12: 
               month = "December";
               break;
         }
         System.out.println();
         System.out.printf("%8s%-18s\n", space, month);
         System.out.println();
         printDaysOfWeek();
         printDayNumbers(year,numOfMonth);
         if(year == Calendar.getInstance().get(Calendar.YEAR))
         {
            if(numOfMonth == (Calendar.getInstance().get(Calendar.MONTH) + 1))
            {
               for(int j = 0; j != 26; j++)
               {
               System.out.print("-");
               }
            }
         }
         System.out.println();
      }
   }
   //print sunday through saturday
   public static void printDaysOfWeek()
   {
      int weekDayNum;
      String weekName = "";
      for(int i = 0; i != 7; i++)
      {
         weekDayNum = i;
         switch(weekDayNum)
         {
            case 0:
               weekName = "S";
               break;
            case 1:
               weekName = "M";
               break;
            case 2:
               weekName = "Tu";
               break;
            case 3:
               weekName = "W";
               break;
            case 4:
               weekName = "Th";
               break;
            case 5:
               weekName = "F";
               break;
            case 6:
               weekName = "S";
         }
         System.out.printf("%-4s", weekName);
      }
      System.out.println();
   }
   public static void printDayNumbers(int year, int numOfMonth)
   {
      int place = 1;
      JulianDate JD = new JulianDate();
      int date = JD.toJulian(year,numOfMonth,1);
      int dayOfWeek = (date + 1) % 7;
      int numOfDays = getNumberOfDays(numOfMonth, year);
      place = (place * dayOfWeek) * 4;
      //puts the cursor at the right spot
      for(int i = 0; i != place; i++)
      {
         System.out.print(" ");
      }
      //use to print the numbers
      for(int i = 1; i <= numOfDays; i++)
      {
         System.out.printf("%-4d", i);
         place += 4;
         if(place > 27)
         {
            System.out.println();
            place = 0;
         }
      }
      place = 1;
      System.out.println();
   }
   public static int getNumberOfDays(int numOfMonth, int year)
   {
      int numOfDays = 0;
      switch(numOfMonth)
      {
         case 1:
            numOfDays = 31;
            break;
         case 2:
            numOfDays = getFeburaryDays(year);
            break;
         case 3:
            numOfDays = 31;
            break;
         case 4:
            numOfDays = 30;
            break;
         case 5:
            numOfDays = 31;
            break;
         case 6:
            numOfDays = 30;
            break;
         case 7:
            numOfDays = 31;
            break;
         case 8:
            numOfDays = 31;
            break;
         case 9:
            numOfDays = 30;
            break;
         case 10:
            numOfDays = 31;
            break;
         case 11:
            numOfDays = 30;
            break;
         case 12:
            numOfDays = 31;
            break;
      }
      return numOfDays;
   }
   public static int getFeburaryDays(int year)
   {
      int numOfDaysFeb = 0;
      if(determineLeapYear(year) == true)
      {
         numOfDaysFeb = 29;
      }
      else
      {
         numOfDaysFeb = 28;
      }
      return numOfDaysFeb;  
   }
}