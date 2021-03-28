public class Species
{
   private static int speciesCount = 0;
   private static int endangeredSpecies = 0;
   private String name;
   private int population;
   private double growthRate;
   public Species()
   {
      name = "";
      population = 0;
      growthRate = 0;
      speciesCount++;
   }
   public Species(String name)
   {
      this.name = name;
      population = 0;
      growthRate = 0;
      speciesCount++;
   }
   public Species(String name, int population)
   {
      this.name = name;
      int p = population;
      setPopulation(p);
      this.population = population;
      growthRate = 0;
      speciesCount++;
   }
   public void setPopulation(int p)
   {
      if(p > 0)
      {
         population = p;
      }
   }
   public Species(String name, double growthRate)
   {
      this.name = name;
      population = 0;
      setGrowthRate(growthRate);
      this.growthRate = growthRate;
      speciesCount++;
      if(growthRate < 0)
      {
         endangeredSpecies++;
      }
   }
   public Species(String name, int population, double growthRate)
   {
      this.name = name;
      setPopulation(population);
      setGrowthRate(growthRate);
      this.population = population;
      this.growthRate = growthRate;
      if(growthRate < 0)
      {
         endangeredSpecies++;
      }
      speciesCount++;
   }
   public void setName(String newName)
   {
      this.name = newName;
   }
   public void setGrowthRate(double newGrowthRate)
   {
      if(newGrowthRate < 0 && this.growthRate > 0)
      {
         endangeredSpecies++;
      }
      else if (this.growthRate < 0 && newGrowthRate > 0)
      {
         endangeredSpecies--;
      }
   }
   public static int getSpeciesCount()
   {
      return speciesCount;
   }
   public String getName()
   {
      return name;
   }
   public double getGrowthRate()
   {
      return growthRate;
   }
   public int getPopulation()
   {
      return population;
   }
   public static int getEndangeredCount()
   {
      return endangeredSpecies;
   }
   public static int getPercentEndangered()
   {
      return endangeredSpecies / speciesCount;
   }
   public boolean equals(Species otherSpecies)
   {
      if(this.name == name)
      {
         if(this.population == population)
         {
            if(this.growthRate == growthRate)
            {
               return true;
            }
         }
      }
      return false;
   }
   public boolean endangered()
   {
      if(growthRate < 0)
      {
         return true;
      }
      return false;
   }
   public Species createIdenticalSpecies()
   {
      Species s1 = new Species();
      s1.name = this.name;
      s1.population = this.population;
      s1.growthRate = this.growthRate;
      speciesCount--;
      return s1;
   }
   public String toString()
   {
      String s = "";
      s += "Species name: " + name;
      s += ", Population: " + population;
      s += ", Endangered count: " + endangeredSpecies;
      return s;
   }
}