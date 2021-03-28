import java.util.*;
public class Chapter7Lab
{
   public static void main (String[]args)
   {
      String userChoice = "";
      Scanner kb = new Scanner(System.in);
      do
      {
         run(kb);
         System.out.println();
         System.out.print("Do you want to run it again? " );
         userChoice = kb.nextLine();
      }
      while(userChoice.equalsIgnoreCase("yes"));
   }
   public static void run(Scanner kb)
   {
      int amountOfStudents = 0;
      System.out.print("Enter the number of the students: ");
      amountOfStudents = kb.nextInt();
      kb.nextLine();
      int[] studentScore = new int[amountOfStudents];
      fillArray(kb, studentScore, amountOfStudents);
   }
   public static void fillArray(Scanner kb, int[] studentScore,int amountOfStudents)
   {
      int studentGrades = 0;
      int maxGrade = 0;
      String grade = "";
      for(int i = 0; i <= amountOfStudents - 1; i++)
      {
      System.out.print("Enter student " + (i + 1) + " score: ");
      studentGrades = kb.nextInt();
      kb.nextLine();
      studentScore[i] = studentGrades; 
      }
      maxGrade = finMaxGrade(studentScore);
      System.out.println();
      System.out.println("Here is the result:");
      System.out.println();
      System.out.println("Student     Score      Grade");
      for(int i = 0;i <= amountOfStudents - 1; i++)
      {
         if((maxGrade - 10) <= studentScore[i])
         {
            grade = "A";
         }
         else if((maxGrade - 20) <= studentScore[i])
         {
            grade = "B";
         }
         else if((maxGrade - 30) <= studentScore[i])
         {
            grade = "C";
         }
         else if((maxGrade - 40) <= studentScore[i])
         {
            grade = "D";
         }
         else
         {
            grade = "F";
         }
         System.out.printf("%7d" , i + 1);
         System.out.printf("%10d", studentScore[i]);
         System.out.printf("%11s" , grade);
         System.out.println();
      }
   }
   public static int finMaxGrade(int[] studentScore)
   {
      int maxScore = 0;
      for(int i = 0; i < studentScore.length; i++)
      { 
         if(studentScore[i] > maxScore)
         {
            maxScore = studentScore[i];
         }
      }
      return maxScore;
   }
}