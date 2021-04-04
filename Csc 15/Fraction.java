public class Fraction
{
   private int neum;
   private int deno;
   public Fraction(int neum, int deno)
   {
      this.neum = neum;
      this.deno = deno;
   }
   public Fraction()
   {
      deno = 1; 
      neum = 1;
   }
   public int getDeno()
   {
      return deno;
   }
   public int getNeum()
   {
      return neum;
   }
   public void setDeno(int newDeno)
   {
      if(newDeno != 0)
      {
         deno = newDeno;
      }
   }
   public void setNeum(int newNuem)
   {
      neum = newNuem;
   }
   public double getDouble()
   {
      return (double)neum / deno;
   }
   public boolean equals (Fraction f)
   {
      return this.getDouble() == f.getDouble();
   }
   public int getLCD()
   {
      int min = Math.min(neum, deno);
      while( neum % min != 0 || deno % min != 0)
      {
         min --;
      }
      return min;
   }
   public Fraction simplify()
   {
      int LCD = this.getLCD();
      int n = 0;
      int d = 0;
      if (neum % LCD == 0 && deno % LCD == 0)
      {
         n = neum / LCD;
         d = deno / LCD;
      }
      else
      {
         n = neum;
         d = deno;
      }
      Fraction f = new Fraction(n,d);
      return f;
   }
   public Fraction add(Fraction f)
   {
      int cd = this.deno * f.deno;
      int a = (cd / this.deno) * this.neum;
      int b = (cd / f.deno) * f.neum;
      int n = a + b;
      Fraction f1 = new Fraction(n,cd);
      f1 = f.simplify();
      return f1;
   }
   public Fraction subtract(Fraction f)
   {
      int cd = this.deno * f.deno;
      int a = (cd / this.deno) * this.neum;
      int b = (cd / f.deno) * f.neum;
      int n = a - b;
      Fraction f1 = new Fraction(n,cd);
      f1 = f.simplify();
      return f1;
   }
   public Fraction multiply(Fraction f)
   {
      int n = this.neum * f.neum;
      int d = this.deno * f.deno;
      Fraction f1 = new Fraction (n,d);
      f1 = f1.simplify();
      return f1;
   } 
   public String toString()
   {
      return this.neum + " / " + this.deno;
   }
   public Fraction divide(Fraction f)
   {
      int n = this.neum * f.deno;
      int d = this.deno * f.neum;
      Fraction f1 = new Fraction(n,d);
      f1 = f1.simplify();
      return f1;
   }
}