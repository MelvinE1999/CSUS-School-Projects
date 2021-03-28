import java.util.*;
import java.io.*;
public class GroceryDriver
{
   public static void main(String[] args)
   {
      boolean repeat = true;
      while(repeat == true)
      {
         repeat = start();
      }
   }
   public static boolean start()
   {
      Scanner kb = new Scanner(System.in);
      String userChoice = "";
      GroceryList myList = new GroceryList();
      Item[] allItems = new Item[50];
      purchase(myList, kb, allItems);
      displayList(myList);
      modifyList(myList, kb, allItems);
      if(myList.isEmpty() == false)
      {
         displayFinalCost(myList);
      }
      do
      {
         System.out.print("Are there anymore customers left? ");
         userChoice = kb.nextLine();
         System.out.println();
      }
      while(!(userChoice.equalsIgnoreCase("no")) && !(userChoice.equalsIgnoreCase("yes")));
      if(userChoice.equals("no"))
      {
         return false;
      }
      return true;
   }
   public static void purchase(GroceryList myList, Scanner kb, Item[] allItems)
   {
      boolean repeat = true;
      String answer = "";
      double price = 0;
      boolean dataCheck = true;
      int indexOfAllItems = 0;
      while(repeat == true)
      {
         System.out.print("Enter the name of the item: ");
         String name = kb.nextLine();
         System.out.print("Enter the price of the item: ");
         price = kb.nextDouble();
         System.out.print("Enter the quantity of the item: ");
         int quantity = kb.nextInt();
         kb.nextLine();
         Item myItem = new Item(name, quantity, price);
         myList.add(myItem);
         allItems[indexOfAllItems] = myItem;
         System.out.println();
         do
         {
            System.out.println("Do you have another item? ");
            answer = kb.nextLine();
            System.out.println();
         }
         while(!(answer.equalsIgnoreCase("no")) && !(answer.equalsIgnoreCase("yes")));
         if(answer.equalsIgnoreCase("no"))
         {
            repeat = false;
         }
         indexOfAllItems++;
      }
   }
   public static void displayList(GroceryList myList)
   {
         System.out.println(myList.toString());
   }
   public static void modifyList(GroceryList myList, Scanner kb, Item[] allItems)throws InputMismatchException
   {
      String modifyChoice = "";
      String itemChoice = "";
      double priceChoice = 0;
      int quantityChoice = 0;
      String modifyItemChoice = "";
      String moreModifications = "";
      do
      {
         do
         {
            System.out.print("Do you want to modify the list of items? ");
            modifyChoice = kb.nextLine();
         }
         while(!(modifyChoice.equalsIgnoreCase("no")) && !(modifyChoice.equalsIgnoreCase("yes")));
         System.out.println();
         if(modifyChoice.equalsIgnoreCase("yes"))
         {
            System.out.print("What is the name of the item that you want to modify? ");
            itemChoice = kb.nextLine();
            System.out.print("Enter the new price of the item: ");
            priceChoice = kb.nextDouble();
            System.out.print("Enter the new quantity of the item: ");
            quantityChoice = kb.nextInt();
            kb.nextLine();
            System.out.println();
            do
            {
               System.out.println("If you want remove this item type \" rem \"");
               System.out.println("If you want to modify this item type \"mod\"");
               System.out.print("Enter your choice: ");
               modifyItemChoice = kb.nextLine();
            }
            while(!(modifyItemChoice.equalsIgnoreCase("mod")) && !(modifyItemChoice.equalsIgnoreCase("rem")));
            Item modItem = new Item(itemChoice, quantityChoice, priceChoice);
            int modCounter = 0;
            if(myList.find(itemChoice) > -1)
            {
              if(modifyItemChoice.equals("mod"))
              {                 
                  for(int i = 0; i <= GroceryList.count - 1; i++)
                  {
                     if(itemChoice.equals(allItems[i].getName()))
                     {
                        myList.remove(allItems[i]);
                     }
                  }
                  myList.add(modItem);
                  //myList.setCount(GroceryList.count - 1);
              }
              else
              {
                for(int i = 0; i <= GroceryList.count - 1; i++)
                {              
                     myList.remove(allItems[i]);
                }
                              
              }
            }
            else
            {
               System.out.println("That item is not on the list");
            } 
         }
         System.out.println();
         if(myList.isEmpty() == true)
         {
            System.out.println("There is no items in the Grocery List to Display");
         }
         else
         {
            do
            {
               System.out.println("Do you want to modify anymore items? ");
               moreModifications = kb.nextLine();
            }
            while(!(moreModifications.equalsIgnoreCase("yes")) && !(moreModifications.equalsIgnoreCase("no")));
         }
      }
      while(moreModifications.equalsIgnoreCase("yes"));
      System.out.println();
   }
   public static void displayFinalCost(GroceryList myList)
   {
      double finalCost = 0;
      finalCost += myList.getTotalCost();
      finalCost = Math.round(finalCost * 100.0) / 100.0;
      System.out.println("The final cost of items ordered is: " + finalCost);
      System.out.println();
   }
}