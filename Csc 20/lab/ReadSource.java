/*
Lab number: final                                         
Name: Melvin Evans                                      
Section number: 13                                      
Module: This program will print a table from a file to the screen.
While at the same time will store all of the text into an array so it will
calculate the average,low, and high of the scores.
Date: 4/13/18                                           
*/
import java.io.*;
import java.util.*;
public class ReadSource
{
   //pre-condition: n/a
   //post-condition:this will run all of the other methods making it print the table and the high, low, and average score
   public static void main(String[] arguments)
   {
      ArrayList<Student> studentInfo = new ArrayList<Student>();
      readAndFill(studentInfo);
      System.out.println();
      Statistics student = new Statistics();
      student.calculateHigh(studentInfo);
      student.calculateLow(studentInfo);
      student.calculateAvg(studentInfo);
   }
   //pre-condition: must pass in an array of students and have an actual file to read from 
   //post-condition: it will read the given file, print the table, and store all the objects in the array
   public static void readAndFill(ArrayList<Student> studentInfo)
   {
      try
      {
         FileReader file = new FileReader("Scores.txt"); 
         BufferedReader buff = new BufferedReader(file); 
         String line;
         int count = 0;
         line = buff.readLine();
         System.out.printf("%4s %-4s%-4s%-4s%-4s%-4s%n", "Stud", "L1", "L2", "L3", "L4", "L5"); 
         while (line != null)
         {
            String[] Scores = new String[7];
            String[] result = line.split(" ");
            String temp = " ";
            for(int j = 0, k = 1; j != result.length; j++, k++)
            {
              Scores[j] = result[j];
            }
            for(int i = 1; i != 6; i++)
            {
               temp = Scores[i];
               if(temp.indexOf("1") != 0)
               {
                  temp = temp.substring(1,3);
               }
               Scores[i] = temp;
            }
            for(int i = 0; i < 6; i++)
            {
               if(i == 0)
               {
                  System.out.print(Scores[i] + " ");
               }
               else
               {
                  System.out.print(Scores[i] + "  ");
               }
            } 
            System.out.println();
            Student students = new Student();
            for(int i = 0; i < 6; i++)
            {
               if(i == 0)
               {
                  students.setSID(Integer.valueOf(Scores[i]));
               }
               else
               {
                  students.setScore(Integer.valueOf(Scores[i]));
               }
            }
            studentInfo.add(students);
            count++; 
            line = buff.readLine();
         }
         buff.close();
      }
      catch (IOException e)
      {
         System.out.println("Error " + e.toString());
      }
   }
}