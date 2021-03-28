/*
Melvin Evans
10/17/17
This program compares two applicants based on their gpa and sat/act test score to see who is a better applicant.
*/
import java.util.*;
public class Admit
{
public static void main(String[] args)
//Makes it so that the program can run.
{
   description();
   run();
}
public static void run()
/*
Asks the user how many times they want to run the program and then calls the functions need to ask, 
calculate, and print the test score, gpa, and which applicant is better by the score provided.
*/
{
   Scanner kb = new Scanner(System.in);
   System.out.print("How many times do you want to use the software: ");
   int rep = kb.nextInt();
   System.out.println();
   for (int times = 1; times <= rep; times++)
   {
      if(times >=2)
      {
         description();
         System.out.println();
      }
      double examApp1 = 0;
      double gpaApp1 = 0;
      int testTaken = 0;
      double examApp2 = 0;
      double gpaApp2 = 0;
      for (int applicant = 1; applicant <= 2; applicant++)
      {
         if(applicant == 1)
         {
            System.out.println("Information for applicant #" + applicant + ":");
            System.out.print("do yo have 1) SAT scores or 2) ACT scores? ");
            testTaken = kb.nextInt();
            if(testTaken == 1)
            {
               examApp1 = satInfo(kb);
            } 
            else if(testTaken == 2)
            {
               examApp1 = actInfo(kb);
            }
            gpaApp1 = gpaInfo(kb); 
            kb.nextLine();
            System.out.println(); 
         }
         else
         {
            System.out.println("Information for applicant #" + applicant + ":");
            System.out.print("do yo have 1) SAT scores or 2) ACT scores? ");
            testTaken = kb.nextInt();            
            if(testTaken == 1)
            {
               examApp2 = satInfo(kb);
            } 
            else if(testTaken == 2)
            {
               examApp2 = actInfo(kb);
            }
            gpaApp2 = gpaInfo(kb); 
            System.out.println();
            compareApplicants(examApp1, examApp2, gpaApp1, gpaApp2);    
         }
     }
   }
}
public static void description()
//Prints the description of the program.
{
   System.out.println("This program compares two applicants to determine which one seems like the stronger applicant.");
   System.out.println("For each candidate I will need either SAT or ACT scores plus a weighted GPA.");
}
public static double satInfo(Scanner kb)
/*
Asks the user for their Sat test score information and then passes it to the calclator
so that it can be then returned to run.
*/
{
   System.out.print("SAT math? ");
   double satMathScore = kb.nextInt();
   System.out.print("SAT critical reading? ");
   double satCriticalReading = kb.nextInt();
   System.out.print("SAT writing? ");
   double satWriting = kb.nextInt();
   satCalculator(satMathScore, satCriticalReading, satWriting);
   double examScore = satCalculator(satMathScore, satCriticalReading, satWriting);
   System.out.println("exam score: " + examScore);
   return examScore;
}
public static double satCalculator(double satMathScore, double satCriticalReading,double satWriting)
//Recives the score information from satInfo. Then calculates the score and returns it to satInfo.
{
   double satExamScore = ((2 * satMathScore) + satCriticalReading + satWriting) / 32;
   satExamScore = Math.round(satExamScore * 10.0) / 10.0;
   return satExamScore;
}
public static double actInfo(Scanner kb)
/*
Asks the user for their Act test score information and then passes it to the calclator
so that it can be then returned to run.
*/
{
   System.out.print("ACT English? ");
   int actEnglishScore = kb.nextInt();
   System.out.print("ACT math? ");
   int actMathScore = kb.nextInt();
   System.out.print("ACT reading? ");
   int actReadingScore = kb.nextInt();
   System.out.print("ACT science? ");
   int actScienceScore = kb.nextInt();
   actCalculator(actEnglishScore, actMathScore, actReadingScore, actScienceScore);
   double examScore = actCalculator(actEnglishScore, actMathScore, actReadingScore, actScienceScore);
   System.out.println("exam score = " + examScore);
   return examScore;
}
public static double actCalculator(int actEnglishScore,int actMathScore,int actReadingScore,int actScienceScore)
//Calculates the Act score passed on the info passed to it and then returns it to actInfo.
{
   double actExamScore = (actEnglishScore + (2 * actMathScore + actReadingScore + actScienceScore)) / 1.8;
   actExamScore = Math.round(actExamScore * 10.0) / 10.0;
   return actExamScore;
}
public static double gpaInfo(Scanner kb)
//Asks the user for their gpa, max gpa, and transcript multiplier so that it can calculate and return the score.
{
   System.out.print("overall GPA? ");
   double actualGPA = kb.nextDouble();
   System.out.print("max GPA? ");
   double maxGPA = kb.nextDouble();
   System.out.print("Transcript Multiplier? ");
   double gpaMultiplier = kb.nextDouble();
   double gpaScore = (actualGPA / maxGPA) * 100 * gpaMultiplier;
   gpaScore = Math.round(gpaScore * 10.0) / 10.0;
   System.out.println("GPA score = " + gpaScore);
   return gpaScore;
}
public static void compareApplicants(double examApp1, double examApp2,double gpaApp1,double gpaApp2)
/*
Takes the information for each applicant and adds their exam and gpa score so that it can see
which applicant is better. Then prints the result.
*/
{
   double app1Score = examApp1 + gpaApp1;
   double app2Score = examApp2 + gpaApp2;
   System.out.println("First applicant overall score = " + app1Score);
   System.out.println("Second applicant overall score = " + app2Score);
   if(app1Score > app2Score)
   {
      System.out.println("The first applicant seems to be better.");
   }
   else if(app1Score < app2Score)
   {
      System.out.println("The Second applicant seem to be better.");
   }
   else
   {
      System.out.println("The two applicants seem to be equal.");
   }
   System.out.println();
}
}