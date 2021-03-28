/*
Melvin Evans
10/19/17
This program will have the user play rock paper scissors against a computer till they want to stop.
*/
import java.util.*;
public class chapter5Lab
{
   public static void main(String[]args)
   {
      String playerChoice = "";
      do
      {
      description();
      System.out.println();
      playerChoice = play();
      if(playerChoice.equalsIgnoreCase("yes"))
      {
         System.out.println();
      }
      }
      while(playerChoice != "No");
      System.out.println("GOOD BYE. COME BACK SOON");
   }
   public static void description()
   {
      System.out.println("Using this app you can play Rock-Paper-Scissors against the computer. When played between");
      System.out.println("two people, each person picks one of the three options at the same time, and the winner");
      System.out.println("Is determined. The program should randomly choose one of the three options, then prompt for the");
      System.out.println("user's selection.  At that point, the program reveals both choices and print a ");
      System.out.println("Statement indicating if the user won, the computer won, or if it was a tie. Continue playing until the");
      System.out.println("user choose to stop. Then print the total number of the games played, total wins, total losses, and ");
      System.out.println("total ties.");
      System.out.println("Ready, Set, Go");
   }
   public static String play()
   {
      Scanner kb = new Scanner(System.in);
      Random choice = new Random();
      String computersChoice = "";
      String usersChoice = "";
      int winCounter = 0;
      int loseCounter = 0;
      int tieCounter = 0;
      int timesPlayed = 0;
      String playersChoice = "";
      do
      {
         usersChoice = getUsersChoice(kb);
         computersChoice = getComputersChoice(choice);
         System.out.println();
         System.out.println("Computer selected: " + computersChoice);
         System.out.println("You selected: " + usersChoice);
         System.out.println();
         if(computersChoice == "ROCK" && usersChoice == "SCISSORS" || computersChoice == "PAPER" && usersChoice == "ROCK" || computersChoice == "SCISSORS" && usersChoice == "PAPER")
         {
           System.out.println("Oh No, you lost");
           System.out.println();
           loseCounter++;
           timesPlayed++; 
         }
         else if(usersChoice == "ROCK" && computersChoice == "SCISSORS" || usersChoice == "PAPER" && computersChoice == "ROCK" || usersChoice == "SCISSORS" && computersChoice == "PAPER")
         {
            System.out.println("Hurray! You won.");
            System.out.println();
            winCounter++;
            timesPlayed++;
         }
         else if(usersChoice == "ROCK" && computersChoice == "ROCK" || usersChoice == "PAPER" && computersChoice == "PAPER" || usersChoice == "SCISSORS" && computersChoice == "SCISSORS")
         {
            System.out.println("There is a tie");
            tieCounter++;
            timesPlayed++;
      }
      usersChoice = usersChoice;
      }
      while(!usersChoice.equalsIgnoreCase("stop"));
      winDecider( timesPlayed, winCounter, loseCounter, tieCounter);
      System.out.print("Do you want to play again? ");
      String playAgainOption = kb.nextLine();
      playAgainOption = playAgainOption.toLowerCase();
      switch(playAgainOption)
      {
         case "yes": playersChoice = "Yes";
         return playersChoice;
         case "no": playersChoice = "No";
         return playersChoice;
      }  
      return playersChoice;
   }
   public static String getComputersChoice(Random choice)
   {
      int computersOption = choice.nextInt(3);
      String computersChoice = "";
      switch(computersOption)
      {
         case 0: computersChoice = "ROCK";
         return computersChoice;
         case 1: computersChoice = "PAPER";
         return computersChoice;
         case 2: computersChoice = "SCISSORS";
         return computersChoice;
      }
      return computersChoice;
   }
   public static String getUsersChoice(Scanner kb)
   {
      String choice = "";
      String userChoice = "";
      do
      {
         System.out.println("your choices");
         System.out.println("       Rock");
         System.out.println("       Paper");
         System.out.println("       Scissors");
         System.out.println("       stop");
         System.out.print("Enter your choice: ");
         choice = kb.nextLine();
      }
      while(!choice.equalsIgnoreCase("rock")&&!choice.equalsIgnoreCase("scissors")&&!choice.equalsIgnoreCase("paper")&&!choice.equalsIgnoreCase("stop"));
      choice = choice.toLowerCase();
      switch(choice)
      {
         case "rock": userChoice = "ROCK";
         return userChoice;
         case "paper": userChoice = "PAPER";
         return userChoice;
         case "scissors": userChoice = "SCISSORS";
         return userChoice;
         case "stop": userChoice += "STOP";
         return userChoice;
      } 
      return userChoice; 
   }
   public static void winDecider(int timesPlayed, int winCounter, int loseCounter, int tieCounter)
   {
      System.out.println("------------------------------------");
      System.out.println("Here is the result of the play:");
      System.out.println("Times played: " + timesPlayed);
      System.out.println("Wins:      " + winCounter);
      System.out.println("Losses:    " + loseCounter);
      System.out.println("Ties:      " + tieCounter);
      if(winCounter > loseCounter)
      {
         System.out.println("Congratulations! You won.");
      }
      else if(loseCounter > winCounter)
      {
         System.out.println("sorry computer won this time. Try again.");
      }
      System.out.println();
      System.out.println("------------------------------------");
   }
}