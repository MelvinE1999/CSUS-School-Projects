public class GroceryList
{
   public Item[] items;
   public static int count;
   public static final int max = 50;
   public GroceryList()
   {
      items = new Item[max];
      count = 0;
   }
   public Item[] getList()
   {
      return items;
   }
   public void add(Item newItem)
   {
      if(items[count] == null)
      {
         items[count] = newItem;
         count++;
      }
   }
   public boolean remove(Item oldItem)
   {
      for(int i = 0; i < count; i++)
      {
         if(items[i] == null)
         {
         items[i] = items[i];
         }
         else if(items[i].equals(oldItem))
         {
            items[i] = null;
            return true;
         }
      }
      return false;
   }
   public double getTotalCost()
   {
      double cost = 0;
      for(int i = 0; i < count; i++)
      {
         if(items[i] != null)
         {
            cost += items[i].getTotalCost();
         }
      }
      return cost;
   }
   public int find(String name)
   {
      int index = -1;
      for(int i = 0; i < count; i++)
      {
         if(items[i] == null)
         {
            index = index;
         }
         else if(items[i].getName().equals(name))
         {
            index = i;
         }
      }
      return index;
   }
   public String toString()
   {
      String a = "";
      for(int i = 0; i < count; i++)
      {
         if(items[i] != null)
         {
            a += items[i].toString();
         }
      }
      return a;
   }
   public boolean isEmpty()
   {
      for(int i = 0; i < count; i++)
      {
         if(items[i] != null)
         {
            return false;
         }
      }
      return true;
   }
   public static void setCount(int otherCount)
   {
      count = otherCount;
   }
}