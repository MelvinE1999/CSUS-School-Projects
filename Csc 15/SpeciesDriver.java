public class SpeciesDriver
{
   public static void main(String[] args)
   {
      Species s1 = new Species();
      System.out.println(s1.toString());
      Species s2 = new Species("Rabbit", 6750, 1.05);
      System.out.println(s2.toString());
      Species s3 = new Species ("Polar Bear", 550);
      System.out.println(s3.toString());
      Species s4 = new Species ("Bee", -1.05);
      System.out.println(s4.toString());
      Species s5 = new Species("Ant");
      System.out.println(s5.toString());
      s1.setPopulation(10);
      System.out.println(s1.toString());
      s1.setName("Ticks");
      System.out.println(s1.toString());
      s1.setGrowthRate(1.00);
      System.out.println(s1.toString());
      System.out.println("S2 name: " + s2.getName());
      System.out.println("S2 population: " + s2.getPopulation());
      System.out.println("S2 growth rate: " + s2.getGrowthRate());
      if(s4.endangered() == true)
      {
         System.out.println("Species is endangered");
      }
      else
      {
         System.out.println("Species is not endangered");
      }
      System.out.println("Species Count: " + Species.getSpeciesCount());
      System.out.println("Endangered Count: " + Species.getEndangeredCount());
      System.out.println("Endangered Percent: " + Species.getPercentEndangered() + "%");
      Species s6 = s4.createIdenticalSpecies();
      if(s6.equals(s4) == true)
      {
         System.out.println("Species 6 and Species 4 are the same.");
      }       
      else
      {
         System.out.println("Species 6 and Species 4 are not the same.");
      }
   }
}