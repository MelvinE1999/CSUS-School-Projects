public class nfc
{
    public static void main(String[] args) {
        if(args.length == 0)
        {
            howToWork();
        }
        else
        {
            header();
            // same as running for every element in array args
            for (String arg : args)
            {
                int number = Integer.valueOf(arg);
                printer(number);
                // Separator for each number passed to it
                System.out.println();
            }
        }
    }

    public static void header()
    {
        System.out.printf("%-16s", "Decimal:");
        System.out.printf("%-16s", "Hexadecimal:");
        System.out.printf("%-16s", "Octal:");
        System.out.printf("%-16s", "Binary:");
        System.out.println();
    }

    // Prints guide on program for people who didn't pass in values.
    private static void howToWork()
    {
        System.out.println("\nThis program works by calling the program in" +
                " the command prompt \nusing the java nfc.java n1 ... nk " +
                "format when you call it. \n(Where all the n represent " +
                "numbers.)");
        System.out.println("Please run again with that call format.\n");
    }

    public static void printer(int number)
    {
        printDecimal(number);
        printHexadecimal(number);
        printOctal(number);
        printBinary(number);
    }

    public static void printDecimal(int number)
    {
        String temp = String.valueOf(number);
        char[] decimalChar = temp.toCharArray();
        int counter = 0;
        if( number > 0)
        {
            counter++;
            System.out.print(" ");
        }
        for( char decimal: decimalChar)
        {
            System.out.print(decimal);
            counter++;
        }
        spacePrinter(counter);
    }

    // Receives decimal number to work with.
    public static void printHexadecimal(int number)
    {
        String temp = Integer.toHexString(number);
        char[] hexChar = temp.toCharArray();
        int counter = 0;
        for(int i = 0; i < hexChar.length; i++)
        {
            //gets rid of the padded f's
            if(number <0)
            {
                if (i < 4)
                {
                    if (hexChar[i] == 'f' && hexChar[i + 1] == 'f') {
                        continue;
                    }
                }
            }

            System.out.print(hexChar[i]);
            counter++;
        }
        spacePrinter(counter);
    }

    // Receives decimal number to work with.
    public static void printOctal(int number)
    {
        int counter = 0;
        String temp = Integer.toOctalString(number);
        char[] octalChar = temp.toCharArray();
        if(number < 0)
        {
            for (int i = 0; i < octalChar.length; i++)
            {
                //skips the first 6 digits as since this is octal number should
                //be only 6 long at max(1 for sign and 5 for the number)
                if( i < 5)
                {
                    continue;
                }
                else if(i == 5)
                {
                    System.out.print("1");
                    counter++;
                }
                else
                {
                    System.out.print(octalChar[i]);
                    counter++;
                }
            }
        }
        else {
            for (char octal : octalChar) {
                counter++;
                System.out.print(octal);
            }
        }
        spacePrinter(counter);
    }

    // Prints the binary conversion with 2's compliment.
    // Receives decimal number to work with.
    public static void printBinary(int numbers)
    {
        String temp = Integer.toBinaryString(numbers);
        char[] holder = temp.toCharArray();
        char[] binaryChar = new char[16];
        int counter = 0;
        int holderCounter = holder.length - 1;
        while(counter < 16)
        {
            if(counter < holder.length)
            {
                binaryChar[counter] = holder[holderCounter];
                holderCounter--;
            }
            //adds the padded to get the conversion to the 16 bit representation
            else
            {
                binaryChar[counter] = '0';
            }
            counter++;
        }
        for(int i = binaryChar.length - 1; i > -1 ; i--)
        {
            System.out.print(binaryChar[i]);
        }
    }

    // Used in order to provide a uniform spacing between conversions.
    public static void spacePrinter(int counter)
    {
        while( counter < 16)
        {
            System.out.print(" ");
            counter++;
        }
    }
}
