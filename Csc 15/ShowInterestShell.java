/*
Melvin Evans
10/4/17
This program takes the given amount and interest and calculates the balance you should have.
*/
import java.util.Scanner;
public class ShowInterestShell {
    public static void main(String[] args) {
       Scanner kb = new Scanner(System.in);
       System.out.print("How many times do you want to use this app:");
       double uses = kb.nextDouble();
       kb.nextLine();
       System.out.println();
       System.out.println();
       for ( double count = 1; count <= uses; count++)
       {
            getInfo(kb);
            getInterest(kb);
       }       
    }
    //this method calls getName, getTelephone, getAddress and out put the user's info on the screen
    public static void getInfo(Scanner kb)
    {
               String name = getName(kb);
               String telephone = getTelephone(kb);
               String Adress = getAddress(kb);
               for (int spaces = 1; spaces <= 5; spaces++)
               {
                  System.out.println();
               }
               for( int star = 1; star <= 42; star++)
               {
                  System.out.print("*");
               }
               System.out.println();
               System.out.println();
               System.out.println("Name:    " + name);
               System.out.println("Phone:   " + telephone);
               System.out.println("Adress:  " + Adress);
               System.out.println();
               for( int star = 1; star <= 42; star++)
               {
                  System.out.print("*");
               }
               System.out.println();
        
    }
    //this method gets the user's name and reformat it as shwn in the output, converts it to upper case and returns the result
    public static String getName(Scanner kb)
    {    
       System.out.print("Enter your first and last name seperating with one space(all letters in lowercase):");
       String fullName = kb.nextLine();
       int space = fullName.indexOf(" ");
       String f = fullName.substring(0,space);
       String last = fullName.substring(space + 1);
       String name = last + " " + f;
       name = name.toUpperCase();
       return name;
    }
    //this method gets the phone number and reformat shown in the output and returns the result
    public static  String getTelephone(Scanner kb)
    {  
        System.out.print("Enter your phone number in the format(123 456 7890):");
        String fullNumber = kb.nextLine();
        String area = fullNumber.substring(0,3);
        String numSet1 = fullNumber.substring(4,7);
        String numSet2 = fullNumber.substring(8,12);
        String num = area + "-" + numSet1 + "-" + numSet2;        
        return num;
    }
    //this method gets the address and reformat shown in the output and returns the result    
    public static  String getAddress(Scanner kb)
    {
        System.out.print("Enter your address in the following format(6000 J street:Sacramento:CA 95819):");
        String fullAdress = kb.nextLine();
        int colon = fullAdress.indexOf(":");
        String street = fullAdress.substring(0,colon);
        int colon2 = fullAdress.indexOf(":",colon + 1);
        String city = fullAdress.substring(colon + 1, colon2);
        String state = fullAdress.substring(colon2 + 1);
        String adress = street + "\n         " + city + "\n         "  + state;
        return adress;     
    }     
       
     /*This method asks  the info such as initial amount, number of years, ineterst rate, monthly deposit
     then calls the method printTable and passes the info needed to that method*/ 
    public static void getInterest(Scanner kb)
    {   
         System.out.print("Enter the initial amount:");
         double amounts = kb.nextDouble();
         double amount = amounts;
         System.out.print("Enter the number of the years:");
         int years = kb.nextInt();
         int periods = years;
         System.out.print("Enter the interest rate:");
         double interest = kb.nextDouble();
         double rate = interest;
         System.out.print("Enter the monthly deposit:");
         double deposits = kb.nextDouble();
         double deposit = deposits;   
         printTable( amount, periods, rate, deposit);
         kb.nextLine();  
    }
    /*This program displays a description of the app */
    public static void description()
    {
    System.out.println("Welcome to interest calculator");
    System.out.println("This program shows how money increases over time given a particular interest rate and a regular deposit amount");
    }

    public static void printTable(double amount, int periods, double rate,
                                  double deposit) {                          
        System.out.println("Year     Interest       Deposit New Balance");
        System.out.println("Start                            " + amount);
        double amounts = amount;
        double rates = rate;
        for(int i = 1; i <= periods;i++)
        {
           getInterest(amounts, rates);
           double interest = getInterest(amounts, rates);
           double n = interest;
           round2(n);
           interest = round2(n);
           double balance = amounts + interest + deposit;
           n = balance;
           round2(n);
           balance = round2(n);
           System.out.printf( "%-9d %-13.2f %-8.2f %-8.2f%n", i, interest, deposit, balance);
           amounts = balance;
        }   
    }
    /*calculate the amount of the interest deposited to the acount after each month
    which is amount * rate /100*/
    public static double  getInterest(double amounts, double rates)
    {
       double interest = amounts * rates / 100;
       return  interest;
    }
    //rounds the given double value to two decimal points
    public static double round2(double n) {
        double round = Math.round(n * 100.0) / 100.0;
        return round;
    }
}