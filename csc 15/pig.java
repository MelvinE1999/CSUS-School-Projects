/*
   Melvin Evans
   10/27/17
   This program allows the user to play pig against a computer.
*/
import java.util.*;
public class pig
{
   public static final int POINTS = 40;
   public static final int FORFEIT_POINTS = 20;
   public static void main(String[]args)
   {
      description();
      play();
   }
   public static void description()
   {
      //prints the description of the game
      System.out.println("*******************************************************************************");
      System.out.println("****");
      System.out.println("*	You are about to play the pig game against the ");
      System.out.println("computer.");
      System.out.println("*	On each turn, the current player will roll a pair of");
      System.out.println("dice.");
      System.out.println("*	and acumulates points. The goal is to reach to " + POINTS);
      System.out.println("points.");
      System.out.println("*	before your opponent does. If, on any turn, the player ");
      System.out.println("roll");
      System.out.println("*	all the points accumulated for that round are forfeited");
      System.out.println("and");
      System.out.println("*	the control of the dice moves to the other player. If the");
      System.out.println("player");
      System.out.println("*	rolls two 1s in one turn , the player loses all the ");
      System.out.println("points");
      System.out.println("*	accumulated thus far and forfeit and the control moves to");
      System.out.println("the");
      System.out.println("*	other player. The player may voluntarily turn over the dice ");
      System.out.println("after");
      System.out.println("*	each roll. Therefore player must decide to roll again(be a");
      System.out.println("pig)");
      System.out.println("*	and risk losing points , or relinquish control of the dice,");
      System.out.println("possibly");
      System.out.println("*	allowing the other player to ");
      System.out.println("win.");
      System.out.println("*	Computer is going to flip a coin to choose the  first ");
      System.out.println("player");
      System.out.println("*******************************************************************************");
      System.out.println("****");
      System.out.println("Lets start the fun");
      System.out.println();
   }
   public static void play()
   {
      //this method brings all the pieces together so that the game will actually run properly
      Scanner kb = new Scanner(System.in);
      Random rNum = new Random();
      String userDecision = "";
      int player1Points = 0;
      int player2Points = 0;
      boolean player1Turn = false;
      boolean player2Turn = false;
      String foreitChoice = "";
      int die1 = 0;
      int die2 = 0;
      String computersName = getComputersName(rNum);
      System.out.println("Hi my name is " + computersName);
      System.out.print("What is your name? ");
      String userName = kb.nextLine();
      System.out.println();
      System.out.print("press enter key to start the game");
      kb.nextLine();
      System.out.println();
      String coinSide = coinFlip(rNum);
      if(coinSide.equals("Head"))
      {
         player1Turn = true;
         System.out.println(computersName + " is going to start the game");
         System.out.println();
      }
      else
      {
         player2Turn = true;
         System.out.println(userName + " is going to start the game");
         System.out.println();
      }
      do
      {
         while(player1Turn == true && player1Points <= POINTS)
         {
            System.out.println(computersName + "'s turn");
            System.out.println("Points : " + player1Points);
            die1 = rollDice(rNum);
            System.out.println("dice1 : " + die1);
            die2 = rollDice(rNum);
            System.out.println("dice2 : " + die2);
            if((die1 != 1 && die2 != 1) && player1Points <= POINTS)
            {
               player1Points = player1Points + die1 + die2;
               if(player1Points >= POINTS)
               {
                  System.out.println("Hurray!!!!!!");
                  System.out.println("You reached or passed " + POINTS + " points");
                  player1Turn = false;
               }
               else if(player1Points >= FORFEIT_POINTS && !(player1Points >= POINTS))
               {
                  foreitChoice = getComputerForfeit(rNum);
                  System.out.println("Do you want to forfeit your turn since you have 20 or more points? " + foreitChoice);
                  if(foreitChoice.equalsIgnoreCase("yes"))
                  {
                     player1Turn = false;
                     player2Turn = true;
                  }
                  else
                  {
                     player1Turn = true;
                  }
               }
               else
               {
                  player1Turn = true;
               }
            }
            else
            {
               if((die1 == 1 || die2 ==1) && player1Points <= POINTS)
               {
                  if(die1 != 1 || die2 != 1)
                  {
                     System.out.println("Sorry " + computersName + " you lost the points for this turn");
                     player1Points = player1Points;
                     player1Turn = false;
                     player2Turn = true;
                  }
                  else
                  {
                     System.out.println("Sorry " + computersName + " you lost the points for this turn");
                     player1Points = 0;
                     player1Turn = false;
                     player2Turn = true;
                  }
               }
            }
            System.out.println("points : " + player1Points);
            System.out.println();
            System.out.print("press the enter key to continue");
            kb.nextLine();
            System.out.println();
         }   
         while(player2Turn == true && player1Points <= POINTS)
         {
            System.out.println();
            System.out.println();
            System.out.println(userName + "'s turn (You)");
            System.out.println("Points : " + player2Points);
            die1 = rollDice(rNum);
            System.out.println("dice1 : " + die1);
            die2 = rollDice(rNum);
            System.out.println("dice2 : " + die2);
            if((die1 != 1 && die2 != 1) && player2Points <= POINTS)
            {
               player2Points = player2Points + die1 + die2;
               if(player2Points >= POINTS)
               {
                  System.out.println("Hurray!!!!!!");
                  System.out.println("You reached or passed " + POINTS + " points");
                  player2Turn = false;
               }
               else if(player2Points >= FORFEIT_POINTS && !(player2Points >= POINTS))
               {
                  foreitChoice = userChoice(kb);
                  if(foreitChoice.equalsIgnoreCase("yes"))
                  {
                     player2Turn = false;
                     player1Turn = true;
                  }
                  else
                  {
                     player2Turn = true;
                  }
               }
               else
               {
                  player2Turn = true;
               }
            }
            else
            {
               if((die1 == 1 || die2 ==1) && player2Points <= POINTS)
               {
                  if((die1 != 1 || die2 != 1) && player2Points <= POINTS)
                  {
                     System.out.println("Sorry " + userName + " you lost the points for this turn");
                     player2Turn = false;
                     player1Turn = true;
                  }
                  else
                  {
                     System.out.println("Sorry " + userName + " you lost the points for this turn");
                     player2Points = 0;
                     player2Turn = false;
                     player1Turn = true;
                  }
               }
            }           
            System.out.println("points : " + player2Points);
            System.out.println();
            System.out.print("press the enter key to continue");
            kb.nextLine();
            System.out.println();
         }
       }
       while(player1Turn != false || player2Turn != false);
       System.out.println();
       System.out.println("Hurray!! We have a winner");
       System.out.println("Somebody got " + POINTS + " or more");
       System.out.println(computersName + " points: " + player1Points);
       System.out.println(userName + " points: " + player2Points);
       if(player1Points > player2Points)
       {
         System.out.println(computersName + " won the game");
       }
       else
       {
         System.out.println(userName + " won the game");
       }
   }
   public static String getComputersName(Random rNum)
   {
      //generates the computers name from a selection of ten name that were premade.
      int computerNameNumber = rNum.nextInt(10);
      String computersNameChoice = "";
      switch(computerNameNumber)
      {
         case 0: computersNameChoice = "Matt";
         return computersNameChoice;
         case 1: computersNameChoice = "Missy";
         return computersNameChoice;
         case 2: computersNameChoice = "David";
         return computersNameChoice;
         case 3: computersNameChoice = "Amelia";
         return computersNameChoice;
         case 4: computersNameChoice = "Rory";
         return computersNameChoice;
         case 5: computersNameChoice = "Peter";
         return computersNameChoice;
         case 6: computersNameChoice = "Doctor";
         return computersNameChoice;
         case 7: computersNameChoice = "River";
         return computersNameChoice;
         case 8: computersNameChoice = "Melody";
         return computersNameChoice;
         case 9: computersNameChoice = "Sarah";
         return computersNameChoice;
         case 10: computersNameChoice = "Clara";
         return computersNameChoice;
      }
      return computersNameChoice;
   }
   public static String coinFlip(Random rNum)
   {
      //flips the coin and returns the result to decide who goes first
      int coinSideDecider = rNum.nextInt(2);
      String coinSide = "";
      switch(coinSideDecider)
      {
         case 0: coinSide = "Head";
         return coinSide;
         case 1: coinSide = "Tail";
         return coinSide;
      }
      return coinSide;
   }
   public static String userChoice(Scanner kb)
   {
      //prompts the user to see if they want to forfeit their turn everytime their points exceed 20.
      String choice = "";
      do
      {
         System.out.print("Do you want to forfeit your turn since you have 20 or more points? ");
         choice = kb.nextLine();
      }
      while(!choice.equalsIgnoreCase("yes") && !choice.equalsIgnoreCase("no"));
      return choice;
   }
   public static int rollDice(Random rNum)
   {
      //rolls the dice and returns it so that we can get the points for the game
      int die = rNum.nextInt(6)+1;
      return die;
   }
   public static String getComputerForfeit(Random rNum)
   {
      //makes a fifty fifty chance of the computer forfeiting their turn everytime their score exceeds 20
      String computersForfeitChoice = "";
      int computersChoice = rNum.nextInt(2);
      switch(computersChoice)
      {
         case 0: computersForfeitChoice = "yes";
         return computersForfeitChoice;
         case 1: computersForfeitChoice = "no";
         return computersForfeitChoice;
      }
      return computersForfeitChoice;
   }
}
