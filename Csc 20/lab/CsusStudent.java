// This class defines CsusStudent's profile including: name, id, address, phone, and email
// Csc20 lab 5 assignment
// Csc 20 's Student to provide more inputs here
public class CsusStudent {
    // class attributes
    private String studentName;
    private int studentId;
    private String studentAddress;
    private String studentPhone;
    private String studentEmail;

    // constructor
    public CsusStudent(String newName, int newId, String newAddress, String newPhone_number, String newEmail){
      studentName = newName;
      studentId = newId;
      studentAddress = newAddress;
      studentPhone = newPhone_number;
      studentEmail = newEmail;
    }

    // setName
    public void setName(String newName){
      studentName = newName;
    }

    // getName 
    public String getName(){
      return studentName;
    }
    
    // setID
    public void setID(String newID){
      studentId = Integer.parseInt(newID);
    }

    // getID 
    public String getID(){
      String ID = Integer.toString(studentId);
      return ID;
    }  

    // setAddress
    public void setAddress(String newAddress){
      studentAddress = newAddress;
    }

    // getAddress
    public String getAddress(){
      return studentAddress;
    }

    // setPhone
    public void setPhone(String newPhone_number){
      studentPhone = newPhone_number;
    }
 
    // getPhone
    public String getPhone(){
      return studentPhone;
    }

    // setEmail
    public void setEmail(String newEmail){
      studentEmail = newEmail;
    }

    // getEmail
    public String getEmail(){
      return studentEmail;
    }

    // toString
    public String toString(){
        // return a string value by combining all values from class's attributes
       String format = "";
       format += "Student Name: " + studentName;
       format += "(" + studentId + ")\n";
       format += "Student Address: " + studentAddress;
       format += "\nStudent Phone #: " + studentPhone;
       format += "\nStudent Email: " + studentEmail;
       format += "\n";
       return format;
    }

}