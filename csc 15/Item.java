public class Item
{
   private String name;
   private int quantity;
   private double pricePerUnit;
   public Item (String name, int quantity, double pricePerUnit)
   {
      this.name = name;
      this.quantity = quantity;
      this.pricePerUnit = pricePerUnit;
   }
   public String getName()
   {
      return name;
   }
   public int getQuantity()
   {
      return quantity;
   }
   public double getPricePerUnit()
   {
      return pricePerUnit;
   }
   public double getTotalCost()
   {
      double preTotal = quantity * pricePerUnit;
      double total = Math.round(preTotal * 100.0) / 100.0;
      return total;
   }
   public void setQuantity(int newQuantity)
   {
      quantity = newQuantity;
   }
   public void setPricePerUnit(double newPrice)
   {
      pricePerUnit = newPrice;
   }
   public void setName(String newName)
   {
      name = newName;
   }
   public boolean equals(Item other)
   {
      if(this.getName() == other.getName())
      {
         if(this.getQuantity() == other.getQuantity())
         {
            if(this.getPricePerUnit() == other.getPricePerUnit())
            {
               return true;
            }
         }
      }
      return false;
   }
   public String toString()
   {
      String s = "";
      s += String.format( "%-5s %-15s", "Name: ", getName());
      s += String.format("%-10s %-7d" ,"Quantity: ", getQuantity());
      s += String.format("%-16s %-6.2f" ,"Price Per Unit: ", getPricePerUnit());
      s += "  Price: " + getTotalCost() + "\n"; 
      return s;
   }
}