// ****************************************************************
// MagicSquare.java
//
// Text below is to be filled by student. 
//
// ****************************************************************
import java.util.Scanner;
public class MagicSquare { 

    int[][] square;

    public MagicSquare(int size)
    {
          square = new int[size][size];   
    }
//--------------------------------------
//return the sum of the values in the given row
//--------------------------------------
 
    private int sumMagicRow(int row)
    {
      int sum = 0;
      for(int i = 0; i < square.length;i ++)
      {
         sum += square[row][i];
      }
      return sum;
    }
//--------------------------------------
//return the sum of the values in the given column
//--------------------------------------
 
    private int sumMagicCol(int col)
    {
      int sum = 0;
      for(int i = 0; i < square.length;i ++)
      {
         sum += square[i][col];
      }
      return sum;
    }
//--------------------------------------
//return the sum of the values in the main diagonal
//--------------------------------------
 
    private int sumMagicDiagMain()
    {
      int sum = 0;
      int row = 0;
      int col = 0;
      for(int i = 0; i < square.length; i++)
      {
         sum += square[row][col];
         row++;
         col++;
      } 
      return sum;
    }
//--------------------------------------
//return the sum of the values in the other ("reverse") diagonal
//--------------------------------------
 
    private int sumMagicDiagRev()
    {
      int sum = 0;
      int row = 0;
      int col = square.length - 1;
      for(int i = 0; i < square.length; i++)
      {
         sum += square[row][col];
         row++;
         col--;
      } 
      return sum;
    }
 
//--------------------------------------
//return true if the square is magic (all rows, cols, and diags
// have same sum), false otherwise
//-------------------------------------- 
    public boolean isMagicSquare()
    {
       boolean answer = true;
       int comparison = sumMagicDiagMain();
       if(comparison == sumMagicDiagRev())
       {
         for(int i = 0; i < 0; i++)
         {
            if(comparison != sumMagicRow(i))
            {
               return false;
            }
            if(comparison != sumMagicCol(i))
            {
               return false;
            }
         }
       }
       else
       {
         answer = false;
       }
       return answer;
    }
//--------------------------------------
//compute and display sums of square including row, column, main diagonal, and other diagonal
//--------------------------------------
    public void printMagicSquareSums()
    {
      for(int i = 0; i < square.length; i++)
      {
         System.out.println("The sum of row # " + i + " = " + sumMagicRow(i));
      }
      System.out.println();
      for(int i = 0; i < square.length; i++)
      {
         System.out.println("The sum of collum # " + i + " = " + sumMagicCol(i));
      }
      System.out.println();
      System.out.println("The sum of the main diagonal = " + sumMagicDiagMain());
      System.out.println();
      System.out.println("The sum of the Reverse diagonal = " + sumMagicDiagRev());
      System.out.println();
    }

//--------------------------------------
//read info into the square from the input stream associated with
//the Scanner parameter
//--------------------------------------
    public void readSquare(Scanner scan)
    {
        for (int row = 0; row < square.length; row++)
           for (int col = 0; col < square.length; col++)
              square[row][col] = scan.nextInt();
    }
//--------------------------------------
//print the contents of the square, neatly formatted
//--------------------------------------
    public void printSquare()
    {
         for (int row = 0; row < square.length; row++)
         {
            for (int col = 0; col < square.length; col++)
            {
              System.out.printf("%-3d", square[row][col]);
            }
            System.out.println();
         }
    }          
}