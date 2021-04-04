public class Circle
{
private int x;
private int y;
private int radius;
public Circle(int x, int y, int radius)
{
   this.x = x;
   this.y = y;
   this.radius = radius;
}
public Circle()
{
   x = 0;
   y = 0;
   radius = 1;
}
public void setX(int newX)
{
   x = newX;
}
public void setY(int newY)
{
   y = newY;
}
public void setradius(int newRadius)
{
   radius = newRadius;
}
public int getx()
{
   return x;
}
public int gety()
{
   return y;
}
public int getradius()
{
   return radius;
}
public String toString()
{
   String s = "";
   s += "x: " + x;
   s += " y: " + y;
   s += " radius: " + radius;
   return s;
}
public double getArea()
{
   return Math.PI * (radius * radius);
}
public int getDiameter()
{
   return radius + radius;
}
public double getCircumference()
{
   return 2 * (Math.PI * radius);
}
}