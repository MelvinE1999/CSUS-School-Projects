/*
Lab number: final                                         
Name: Melvin Evans                                      
Section number: 13                                      
Module: This program will calculate the high,low, and average
for each lab from the given student array.
Date: 4/13/18                                           
*/
import java.util.*;
import java.io.*;
public class Statistics
{
   final int NUMBER_OF_CSC20_LABS = 5;
   private int [] lowscores = new int [NUMBER_OF_CSC20_LABS];   
   private int [] highscores = new int [NUMBER_OF_CSC20_LABS]; 
   private float [] avgscores = new float [NUMBER_OF_CSC20_LABS];
   //pre-condition: must have an array of student objects
   //post-condition:it will calculate the low for every lab and print it to the screen
   public void calculateLow(ArrayList<Student> a)
   {
      int count;
      int lowest;
      int lab = 1;
      String lowestForEachLab = "Low Score ";
      for (int i = 0; i < NUMBER_OF_CSC20_LABS; i++)
      {
         count = 0;   
         lowest = 100;
         while(count < 15)
         {
            int score = a.get(count).getScore(lab);
            int labScore = Integer.valueOf(score);
            if( labScore < lowest)
            {
               lowest = labScore;
            }
            count++;
         }
         lowestForEachLab += " " + lowest;
         lab++;
      }
      System.out.println(lowestForEachLab);
   }
   //pre-condition: must have an array of student objects
   //post-condition:it will calculate the high for every lab and print it to the screen
   public void calculateHigh(ArrayList<Student> a)
   {
      int count;
      int highest;
      int lab = 1;
      String highestForEachLab = "High Score ";
      for (int i = 0; i < NUMBER_OF_CSC20_LABS; i++)
      {
         count = 0;   
         highest = 0;
         while(count < 15)
         {
            int score = a.get(count).getScore(lab);
            int labScore = Integer.valueOf(score);
            if( labScore > highest)
            {
               highest = labScore;
            }
            count++;
         }
         highestForEachLab += " " + highest;
         lab++;
      }
      System.out.println(highestForEachLab);
   }
   //pre-condition: must have an array of student objects
   //post-condition:it will calculate the average for every lab and print it to the screen
   public void calculateAvg(ArrayList<Student> a)
   {
      int count;
      double total;
      int lab = 1;
      double Average;
      String AverageForEachLab = "Average Score ";
      for (int i = 0; i < NUMBER_OF_CSC20_LABS; i++)
      {
         count = 0;   
         total = 0;
         while(count < 15)
         {
            int score = a.get(count).getScore(lab);
            int labScore = Integer.valueOf(score);
            total += labScore;
            count++;
         }
         Average = (double)total/count;
         Average = Math.round(Average * 10.0) / 10.0;
         AverageForEachLab += " " + Average;
         lab++;
      }
      System.out.println(AverageForEachLab);
   }
}