import java.util.Stack;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.*;
import java.io.*;
public class tester 
{
   public static void main(String[] args)
   {
      Scanner kb = new Scanner(System.in);
      System.out.print("How many players: ");
      int players = kb.nextInt();
      String[] names = new String[players];
      int[] score = new int[players];
      int[] phase = new int[players];
      int scores = 0;
      String name = "";
      int phases = 0;
      boolean playAgain = true;
      kb.nextLine();
      for(int i = 0; i < players; i++)
      {
            System.out.print("What is your name: ");
            names[i] = kb.nextLine();
      }
      do
      {
         for(int i = 0; i < players; i++)
         {
            name = names[i];
            //System.out.print("How many points did " + name + " get: ");
            //score[i] += kb.nextInt();
            kb.nextLine();
            System.out.print("Did " + name + " pass the phase? ");
            if(kb.nextLine().equals("yes"))
            {
               phase[i] += 1;
            }
         }
         for(int i = 0; i < players; i++)
         {
            name = names[i];
            scores = score[i];
            phases = phase[i];
            System.out.printf("%-8s%-2d%n", name, phases);
         }
         System.out.print("Do you want to play again? ");
         if(kb.nextLine().equals("no"))
         {
            playAgain = false;
         }
         
      }while(playAgain == true);
   }
}