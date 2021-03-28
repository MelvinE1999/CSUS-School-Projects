import java.util.*;
public class Suits
{
   public static void main(String[] args)
   {
      showSuits();
   }
   public static void showSuits()
   {
      Scanner kb = new Scanner(System.in);
      System.out.print(" Enter the card: ");
      String cardChoice = kb.nextLine();
      String[] cardSplit = cardChoice.split(" ");
      if(cardSplit[0] == "1")
      {
         System.out.print("One of ");
      }
      if(cardSplit[0] == "2")
      {
         System.out.print("Two of ");
      }
      if(cardSplit[0] == "3")
      {
         System.out.print("Three of ");
      }
      if(cardSplit[0] == "4")
      {
         System.out.print("Four of ");
      }
      if(cardSplit[0] == "5")
      {
         System.out.print("Five of ");
      }
      if(cardSplit[0] == "6")
      {
         System.out.print("Six of ");
      }
      if(cardSplit[0] == "7")
      {
         System.out.print("Seven of ");
      }
      if(cardSplit[0] == "8")
      {
         System.out.print("Eight of ");
      }
      if(cardSplit[0] == "9")
      {
         System.out.print("Nine of ");
      }
      if(cardSplit[0].equalsIgnoreCase("j"))
      {
         System.out.print("Jack of ");
      }
      if(cardSplit[0].equalsIgnoreCase("Q"))
      {
         System.out.print("Queen of ");
      }
      if(cardSplit[0].equalsIgnoreCase("K"))
      {
         System.out.print("King of ");
      }
      if(cardSplit[1].equalsIgnoreCase("c"))
      {
         System.out.println("Clubs");
      }
      if(cardSplit[1].equalsIgnoreCase("S"))
      {
         System.out.println("Spades");
      }
      if(cardSplit[1].equalsIgnoreCase("H"))
      {
         System.out.println("Hearts");
      }
      if(cardSplit[1].equalsIgnoreCase("D"))
      {
         System.out.println("Diamonds");
      }
   }
}