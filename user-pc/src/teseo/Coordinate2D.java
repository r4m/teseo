package teseo;

public class Coordinate2D
{
   private double x;
   private double y;
      
   public Coordinate2D(double i, double j)
   {
      x = i;
      y = j;
   }
   
   public Coordinate2D(double[] c)
   {
      if (c.length == 2)
      {
         x = c[0];
         y = c[1];
      }
   }
   
   public double[] toArray()
   {
      double[] array = new double[2];
      array[0] = x;
      array[1] = y;
     
      return array;
   }
   
   public boolean compareTo(Coordinate2D comp)
   {
      if(x!=comp.x || y!=comp.y)
         return false;
      else
         return true;
   }
   
   public double getX()
   {
      return x;
   }
   
   public void setX(double _x)
   {
      x = _x;
   }
   
   public double getY()
   {
      return y;
   }
   
   public void setY(double _y)
   {
      y = _y;
   }
}
