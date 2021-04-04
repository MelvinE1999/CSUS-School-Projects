/*
Lab number: final                                         
Name: Melvin Evans                                      
Section number: 13                                      
Module: This program will make a objext of student so that
it can store the scores of the lab and student id in one place.
Date: 4/13/18                                           
*/
public class Student
{
   final int NUMBER_OF_CSC20_LABS=5;
   private int SID;
   private int scores[] = new int[NUMBER_OF_CSC20_LABS];
   private int count;
   //constructor method
   //pre-condition: n/a
   //post-condition: will make a object of student
   public Student()
   {
      SID = 0;
      count = 0;
   }
   //sets the id of the student
   //pre-condition: must pass a student id into it and it must be a int
   //post-condition: it will set the sid to whatever you pass into it 
   public void setSID(int SIDOther)
   {
      SID = SIDOther;
   }
   //sets the score of the given lab to whatever you pass
   //pre-condition: must pass an int and it must not be over the array bounds
   //post-condition: it will put the lab in the right spot in order of when you pass it
   public void setScore(int lab)
   {
      if(count == NUMBER_OF_CSC20_LABS)
      {
         return;
      }
      scores[count] = lab;
      count++;
   }
   //will get the sid for the student
   //pre-condition: n/a
   //post-condition: will return the sid of the student
   public int getSID()
   {
      return SID;
   }
   //gets the score of the student for the given lab
   //pre-condition: must have labs already stored in the array
   //post-condition: it will return the score at the given lab
   public int getScore(int labNumber)
   {
      return scores[(labNumber - 1)];
   }
}
