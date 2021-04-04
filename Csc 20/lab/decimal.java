public class decimal
{
public static void main(String[]args)
{
   System.out.println(decimalToBinary(8));
}
public static int decimalToBinary(int num)
{
    int power = (int)Math.round(Math.sqrt(num));
    int base = 2;
    int binary = 0;
    for( int i = power; i >= 0; i--)
    {
        if(!(Math.pow(base,i) > num))
        {
            if(num == 1)
            {
                num -= Math.pow(base,i);
                binary += 1;
            }
            else
            {
                num -= Math.pow(base,i);
                binary += Math.pow(10,i);
            }
        }
        else if(Math.pow(base,i) == num)
        {
            if(num == 1)
            {
                num -= Math.pow(base,i);
                binary += 1;
            }
            else
            {
                num -= Math.pow(base,i);
                binary += Math.pow(10,i);
            }
        }
        else
        {
            binary += 0;
        }
    }
    return binary;
}
}
