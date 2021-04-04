/*
Melvin Evans 
11/16/17
This program allows the user to play the Price is right with the help of three other players
*/
import java.util.*;
import java.io.*;
public class PriceIsRight
{
   //runs the start method so that the whole program can run
   public static void main (String[]args)throws FileNotFoundException
   {
      start();
   }
   //pulls all of the methods together needed in order to play the gamme properly
   public static void start()throws FileNotFoundException
   {
      boolean gameRun = true;
      Scanner kb = new Scanner(System.in);
      Random rand = new Random();
      String[] players = new String[4];
      while(gameRun == true)
      {
         String playAgainChoice = "";
         String[] names = fillArrayNames();        
         String[] items = fillArrayItems();
         double[] prices = fillArrayItemsPrices();
         description();
         for(int i = 0; i <= 3; i++)
         {
            players[i] = getPlayers(names, rand);
         }
         boolean run = true;
         String userChoice = "";
         int itemChoice = -1;
         while(run == true)
         {
            int blankCounter = 0;
            if(itemChoice == -1)
            {
               itemChoice = getItemChoice(items, rand);
            }
            System.out.println("Here is the list of players: ");
            for(int i = 0; i <= 3; i++)
            {
               System.out.print(players[i] + " ");
            }
            System.out.println();
            double[] bids = playerBids(items ,players , itemChoice, kb);
            int indexOfClosestBid = priceIsRight(itemChoice, prices, bids);
            String[] playersContinuing = displayResults(players, bids, itemChoice, prices,indexOfClosestBid);
            for(int i = 0; i <= 3; i++)
            {
               if(!(playersContinuing[i].equals("")))
               {
                  blankCounter++;
               }
            }
            if(blankCounter > 0)
            {
              items[itemChoice] = "";
              itemChoice = -1; 
            }
            System.out.print("Here is the list of the players: ");
            for(int i = 0; i <= 3;i++)
            {
               if(playersContinuing[i].equals(""))
               {
                  players[i] = getPlayers(names, rand);
                  System.out.print(players[i] + " ");
                  if(players[i].equals(" "))
                  {
                     run = false;
                     System.out.println("Sorry there are no players left.");
                  }
               }
               else
               {
                  System.out.print(players[i] + " ");
               }
            }
            System.out.println();
            System.out.println();
            if(run == true)
            {
               kb.nextLine();
               do
               {
                  System.out.print("Do you want to play again? ");
                  userChoice = kb.nextLine();
               }
               while(!(userChoice.equalsIgnoreCase("yes")) && !(userChoice.equalsIgnoreCase("no")));
               if(userChoice.equalsIgnoreCase("no"))
               {
                  run = false;
               }
            }
         }
         System.out.println();
         do
         {
            System.out.print("Do you want to play all over again? ");                
            playAgainChoice = kb.nextLine();
         }
         while(!(playAgainChoice.equalsIgnoreCase("yes")) && !(playAgainChoice.equalsIgnoreCase("no")));
         if(playAgainChoice.equalsIgnoreCase("no"))
         {
            gameRun = false;
         }
      }
   }
   //this pops the description of the game on the screen so that the user will know how to play
   public static void description()throws FileNotFoundException
   {
      System.out.println("Welcome to the price is right.");
      System.out.println("In this game you will be given an");
      System.out.println("item to guess the price of. With the");
      System.out.println("person who gets the closest to the actual price without going over winning.");
      System.out.println("For the bids they all have to be unique so no copying others");
      System.out.println("Along with that if you  bid higher then the price of the object");
      System.out.println("then you will be replaced by another contestant.");
      System.out.println("Good Luck.");
      System.out.println();
      System.out.println();
      System.out.println("Wait let me choose the players");
      System.out.println();
   }
   //makes an array and then fills it with player names from a text file on the computer
   public static String[] fillArrayNames()throws FileNotFoundException
   {
      String[] names = new String[20];
      File f = new File("playerNames.txt");
      Scanner input = new Scanner(f);
      int index = 0;
      while(input.hasNextLine())
      {
         names[index] = input.nextLine();
         index++;
      }
      return names;
   }
   //makes an array and then fills it with items from a text file on the computer
   public static String[] fillArrayItems()throws FileNotFoundException
   {
      String[] items = new String[20];
      File f = new File("itemChoices.txt");
      Scanner input = new Scanner(f);
      int index = 0;
      while(input.hasNextLine())
      {
         String S = input.nextLine();
         Scanner token = new Scanner(S);
         items[index] = token.next();
         index++;
      }
      return items;
   }
   //makes an array and then fills it with item prices from a text file on the computer
   public static double[] fillArrayItemsPrices()throws FileNotFoundException
   {
      File f = new File("itemChoices.txt");
      Scanner input = new Scanner (f);
      String[] items = new String[20];
      double[] price = new double[20];
      int index = 0;
      while(input.hasNextLine())
      {
         String S = input.nextLine();
         Scanner token = new Scanner (S);
         items[index] = token.next();
         price[index] = token.nextDouble();
         index++;
      }
      return price;
   }
   //takes the list of names passed to it and then selects a name avaliable and sends it back to the start method
   public static String getPlayers(String[] names, Random rand)throws FileNotFoundException
   {
      String players = "";
      int num = 0;
      int counter = 0;
      do
      {
         counter = 0;         
         num = rand.nextInt(20);
         for(int j = 0; j <= 19; j++)
         {               
            if(names[j].equals(""))
            {
               counter++;
               if(counter == 20)
               {
                  return " ";
               }                  
            }
         }
      }
      while(names[num].equals(""));
      players = names[num];
      names[num] = "";
      return players;
   }
   //takes the list of items passed to it and then selects an item avaliable and sends its index back to the start method
   public static int getItemChoice(String[] items, Random rand)throws FileNotFoundException
   {
      int num = 0;
      int counter = 0;
      String itemChoice = " ";
      do
      {
         num = rand.nextInt(20);
         for(int j = 0; j <= 19; j++)
         {
            if(items[j].equals(" "))
            {
               counter++;
               if(counter == 20)
               {
                  return -1;
               }
            }
         }  
      }
      while(items[num].equals(""));
      itemChoice = items[num];
      return num;
   }
   // shows an item that the users must bid on and those bids are stored in an array that get sent back to the start method
   public static double[] playerBids(String[] items , String[] players ,int itemChoice ,Scanner kb)
   {
      double[] bids = new double[4];
      System.out.println();
      boolean checkIfNextDouble = false;
      System.out.println("Here is the item you have to bet on: " + items[itemChoice]);
      System.out.println();
      for(int i = 0; i <= 3; i++)
      {
         do
         {
            System.out.print(players[i] + " ,enter your bid: ");
            bids[i]= kb.nextDouble();
         }
         while(!(bids[i] > 0));
      }
      return bids;
   }
   //compares all of the bids and then returns the index of the closest bid without going over
   public static int priceIsRight(int itemChoice,double[] prices,double[] bids)throws FileNotFoundException
   {
      int index = -1;       
      if(bids[0] <= prices[itemChoice])
      {
         if (bids[1] < bids[0] || bids[1] > prices[itemChoice])
         {
            if (bids[2] < bids[0] || bids[2] > prices[itemChoice])
            {
               if (bids[3] < bids[0] || bids[3] > prices[itemChoice])
               {
                  index = 0;
               }
            }
         }
      }
      if(bids[1] <= prices[itemChoice])
      {
         if (bids[0] < bids[1] || bids[0] > prices[itemChoice])
         {
            if (bids[2] < bids[1] || bids[2] > prices[itemChoice])
            {
               if (bids[3] < bids[1] || bids[3] > prices[itemChoice])
               {
                  index = 1;
               }
            }
         }
      }
      if(bids[2] <= prices[itemChoice])
      {
         if (bids[0] < bids[2] || bids[0] > prices[itemChoice])
         {
            if (bids[1] < bids[2] || bids[1] > prices[itemChoice])
            {
               if (bids[3] < bids[2] || bids[3] > prices[itemChoice])
               {
                  index = 2;
               }
            }
         }
      }
      if(bids[3] <= prices[itemChoice])
      {
         if (bids[0] < bids[3]|| bids[0] > prices[itemChoice])
         {
            if (bids[1] < bids[3] || bids[1] > prices[itemChoice])
            {
               if (bids[2] < bids[3] || bids[2] > prices[itemChoice])
               {
                  index = 3;
               }
            }
         }
      }
      return index;
   }
   //takes all of the information passed to it and shows the name, bids, and winner of the round
   public static String[] displayResults(String[] players,double[] bids,int itemChoice,double[] prices,int indexOfClosestBid)throws FileNotFoundException
   {
      String[] playersWhoContinue = new String[4];
      System.out.println();
      System.out.printf("%16s%16s%n","Name","Bid");
      System.out.println("----------------------------------------");
      for(int i = 0; i <= 3; i++)
      {
        System.out.printf("%16s%16s%n",players[i],bids[i]); 
      }
      System.out.println("The acual price of the item is: " + prices[itemChoice]);
      System.out.println();
      if(indexOfClosestBid == -1)
      {
         System.out.println("No winner");
         System.out.println("All bids went over the actual price");
         System.out.println("All Players will be replaced");
         for(int i = 0; i <= 3; i++)
         {
            playersWhoContinue[i] = "";
         }
      }
      else
      {
         System.out.println("The winner is: " + players[indexOfClosestBid]);
         if(bids[0] <= prices[itemChoice] && bids[1] <= prices[itemChoice] && bids[2] <= prices[itemChoice] && bids[3] <= prices[itemChoice])
         {
            System.out.println("All of the bids were under the actual price");
            System.out.println("Same Players will play again");
            for(int i = 0; i <= 3; i++)
            {
               playersWhoContinue[i] = players[i];
            }
         }
         else
         {
            System.out.println("Some of the bids were above the actual price");
            System.out.println("Wait let me replace the following players");
            for(int i = 0; i <= 3; i++)
            {
               if(bids[i] > prices[itemChoice])
               {
                  System.out.println(players[i]);
               }
            }
         }
      }
      for(int i = 0; i <= 3; i++)
      {
         if(bids[i] <= prices[itemChoice])
         {
            playersWhoContinue[i] = players[i];
         }
         else if(bids[i] > prices[itemChoice])
         {
            playersWhoContinue[i] = "";
         }
      }
      return playersWhoContinue;
   }
}