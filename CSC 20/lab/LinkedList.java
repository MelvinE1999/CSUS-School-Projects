// A class's description to be provided by student
/*
Name: Melvin Evans
description: this program will make a linked list and then fill it withe given information
date: 3/14/18
*/
// Fill in code in ALL commented areas

public class LinkedList {
   // Defined Node class
   private class Node {
      private Object Data = null;
      private Node Next = null;
      public Node() { 
          Data = null; 
          Next = null; 
      }
      public Node(Object element) { 
          Data = element; 
      }
      public Node(Object o, Node n) { 
          Data = o; 
          Next = n; 
      }      
      public void setNext(Node n) { 
         Next = n; 
      }
      public Node getNext() {  
         return Next; 
      }
      public Object getElement() {  
         return Data; 
      }
      public void setElement(Object element) { 
         Data = element; 
      }
   }

   // Internal data for LinkedList
   private Node head = null;
   private Node current = null;
   private int size = 0;
   
   // LinkedList constructor
   public LinkedList() {
      head = null;
   }
   
   // Move the current position forward one position
   /*
   Pre-condition: must have atleast one node and the next nodecan not be null
   post-condition: It will move current 1 space forward
   */
   public void forward() {
      if(size > 1)
      {
         Node tmp = current.getNext();
         if(tmp != null)
         {
            current = tmp;
         }
      }
   }
   
   // Move the current position backward one position
   /*
   Pre-condition: must have atleast one node and current must not be the same as head
   Post-codition: it will move the current pointer back one node
   */
   public void backward() {
      if(head != current)
      {
         Node tmp = head;
         while((tmp.getNext()!=current)&&(tmp.getNext()!=null))
         {
            tmp = tmp.getNext();
         }
         current = tmp;
      }
   }   
 
   // Get current object's data element
   /*
   Pre-condition: must have one node
   Post-condition: it will return the information of the node
   */
   public Object currentData() {
      if(current != null)
      {
         return current.getElement();
      }
      else
      {
         return null;
      }
   }
  
  
   // Add object to the first of the list
   /*
   Pre-condition: must pass a type of obect
   Post-condition:it will add a node to the beginning of the list
   */
   public void addFirst(Object o) {
      Node tmp = new Node(o,head);
      head = tmp;
      if(current == null)
      {
         current = head;
      }
      size++;
   }
	
   
   // resetCurrent at the first position
   /*
   Pre-condition: must have atleast one node
   Post-condition: it will set current to the beginning of the list
   */
   public void resetCurrent() {
      current = head;
   }
   
   // Add object to the last of the list
   /*
   Pre-condition: none
   Post-condition: it will add the node to the end of the list
   */
   public void addLast(Object o) {
      if(size != 0)
      {
         Node tmp = head;
         while(tmp.Next != null)
         {
            tmp = tmp.getNext();
         }
         Node temp = new Node(o, null);
         temp.setNext(tmp.getNext());
         tmp.setNext(temp);
         size++;
         return;
      }
      addFirst(o);
      
   }
	
   // Add an object o before the current position
   /*
   Pre-condition: must have atleast 1 node
   Post-condition: it iwll print the node before the current position
   */
   public void insertBefore(Object o) {
      if(size != 0)
      {
         Node myNode = new Node(o,null);
         if(head == current)
         {
            addFirst(o);
         }
         else
         {
            backward();
            myNode.setNext(current.getNext());
            current.setNext(myNode);
            forward();
            forward();
            size++;
         }
      }
   }
   
   // Add an object o after the current position
   /*
   Pre-condition: size > 0
   Post-condition: will insert a node after the current position
   */
   public void insertAfter(Object o) {
      if(size != 0)
      {
         Node myNode = new Node(o,null);
         myNode.setNext(current.getNext());
         current.setNext(myNode);
      }
      else
      {
         return;
      }
      size++;
   }
   
   // Get first object
   /*
   Pre-condition: none
   Post-condition: returns the first node if there is one and if not returns null
   */
   public Object getFirst() {
     if(size == 0)
     {
         return null;
     }
     return head.getElement();
   }
	
   // Get last object
   /*
   Pre-condition: none
   Post-condition: it will reurn the info for the last node unless there is no nodes then it will return null
   */
   public Object getLast() {
      if(size == 0)
      {
         return null;
      }
      Node tmp = head;
      while(tmp.Next != null)
      {
         tmp = tmp.getNext();
      }
      return tmp.getElement();
   }
	
   // Remove the first object
   /*
   Pre-condition: none
   Post-condition: it removes the first object
   */
   public Object removeFirst(){
      if(size == 0)
      {
         return null;
      }
      if(current == head)
      {
         forward();
      }
      head = head.getNext();
      size--;
      return head;
   }
	
   // Remove the last object
   /*
   Pre-condition: none
   Post-condition: it will remove the last item
   */
   public Object removeLast() {
      if(size == 0)
      {
         return null;
      }
      Node tmp = head;
      Node prev = head;
      while(tmp.Next != null)
      {
         prev = tmp;
         tmp = tmp.getNext();
      }
      prev.setNext(null);
      size--;
      return head.getElement();
   }
	
   // Remove object o from the list
   /*
   Pre-condition: size > 0 and nonde must be on the list
   Post-condition: removes the given object off the list
   */
   public void remove(Object o) {
      if(size != 0)
      {
         Node prev = null; 
         Node tmp = head; 
         while(tmp.getNext() != null && tmp.getElement() != o)
         {
            prev = tmp;
            tmp = tmp.getNext();
         }
         if(tmp.getElement() == o)
         {
            current = current == tmp? prev : current;
            if(prev != null)
            {
               prev.setNext(tmp.getNext());
            }
            else
            {
               head = head.getNext();
            }
            size--;
         }   
      }
   }
   
   // Returns the number of elements on the list
   public int size() {
      return size;
   }
	
   // Is the list emptied?
   public boolean isEmpty() {
      if(size == 0)
      {
         return true;
      }
      else
      {
         return false;
      }
   }

   // Display a content of a list
   public String toString() {
      String r = "( HEAD -> ";
      // Node l = head.getNext();
      Node l = head;
      while (l != null) {
         r = r + l.getElement() + " -> " ;
         l = l.getNext();
      }
      return  r + " )";
   }
	
   public static void main(String args[]) {
      LinkedList lst = new LinkedList();
      // creat instances for testing
      /*CsusStudent instance1 = new CsusStudent("John Doe 1", 1, "1 Somewhere", "916-555-1211", "johndoe1@somewhere.com");
      CsusStudent instance2 = new CsusStudent("John Doe 2", 2, "2 Somewhere", "916-555-1212", "johndoe2@somewhere.com");
      CsusStudent instance3 = new CsusStudent("John Doe 3", 3, "3 Somewhere", "916-555-1213", "johndoe3@somewhere.com");
      CsusStudent instance4 = new CsusStudent("John Doe 4", 4, "4 Somewhere", "916-555-1214", "johndoe4@somewhere.com"); 
      CsusStudent instance5 = new CsusStudent("John Doe 5", 5, "5 Somewhere", "916-555-1215", "johndoe5@somewhere.com");
      CsusStudent instance6 = new CsusStudent("John Doe 6", 6, "6 Somewhere", "916-555-1216", "johndoe6@somewhere.com");
      CsusStudent instance7 = new CsusStudent("John Doe 7", 7, "7 Somewhere", "916-555-1217", "johndoe7@somewhere.com");
      CsusStudent instance8 = new CsusStudent("John Doe 8", 8, "8 Somewhere", "916-555-1218", "johndoe8@somewhere.com"); 
      CsusStudent instance9 = new CsusStudent("John Doe 9", 9, "9 Somewhere", "916-555-1219", "johndoe9@somewhere.com");         
     
      // begin adding instance1 to the list 
      lst.addFirst(instance1);
      
      // test forward and backward for single entry
      lst.forward();
      lst.backward();
      
      // now add instance2 and instance3 via addFirst and instance4, instance5, instance6 via addLast
      lst.addFirst(instance2);
      lst.addFirst(instance3);
      lst.addLast(instance4);
      lst.addLast(instance5);
      lst.addLast(instance6);
      
      // move current forward a few times            
      lst.forward();
      lst.forward();
      lst.forward();

      // insert instance 9 after
      lst.insertAfter(instance9);
      // remove instance9
      lst.remove(instance9);
      
      // print out the first and last entries
      System.out.println("Show the first entry and last entry:");
      System.out.println(lst.getFirst());
      System.out.println(lst.getLast());
    
      
      // print out the whole list
      System.out.println("Show the whole list:");
      System.out.println(lst.toString());

   	
      // remove entries starting from the last entry 
      System.out.println("Check for the content of the emptied list");
      lst.removeFirst();
      lst.removeFirst();
      lst.removeFirst();
      lst.removeLast();
      lst.removeLast();
      lst.removeLast();
      if(lst.isEmpty() == true)
      {
         System.out.println("List is empty");
      }
      else
      {
         System.out.println("List is not empty");
      }*/
      Integer instance1 = new Integer(1);
      Integer instance2 = new Integer(2);
      Integer instance3 = new Integer(3);
      Integer instance4 = new Integer(4);
      lst.addFirst(instance1);
      lst.addFirst(instance2);
      lst.backward();
      lst.insertAfter(instance3);
      lst.addLast(instance4);
      int MySize = lst.size();
   }
}