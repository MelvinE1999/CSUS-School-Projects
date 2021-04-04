// This class defines Csc20Student's profile with extension of CsusStudent
// Csc20 lab 5 assignment
// Csc 20 's Student to provide more inputs here
public class Csc20Student extends CsusStudent {

    // attributes
    private boolean preComputerMajor;
    private int numberOfComputerClassUnits;
       
    // constructor   
    public Csc20Student(String name, int id, String address, String phone, String email, boolean preCsMajor, int csUnits) {
        super(name,id, address, phone, email);
        preComputerMajor = preCsMajor;
        numberOfComputerClassUnits = csUnits;
    }
   
    // getPreComputerMajor 
    public boolean getPreComputerMajor () {
      return preComputerMajor;
    }
    
    // setPreComputerMajor
    public void setPreComputerMajor (boolean value) {
      preComputerMajor = value;
    }

    // getNumberofCsUnits
    public int  getNumberofCsUnits () {
      return numberOfComputerClassUnits;
    }
    
    // setNumberofCsUnits
    public void setNumberofCsUnits (int units) {
      numberOfComputerClassUnits = units;
    }

    // toSTring
    public String toString(){
        // return a string value by combining all values from class's attributes
        String format = "";
        format += "\nPre-Computer Science: " + preComputerMajor;
        format += "\nCS Units: " + numberOfComputerClassUnits;
        return format;  
    }

}