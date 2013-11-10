import android.location;

public class locationPoint()
{
  
  private double x,y;
  private static boolean first;
  private static double firstLon,firstLat;
  
  public locationPoint(Location L)
  {
      if (!first)
      {
          x = 0;
          y = 0;
          firstLon = L.getLongitude();
          firstLat = L.getLatitude();
          first = true;
      }
      else
      {
          x = distance(L.getLongitude(),firstLat);
          y = distance(L.getLatitude(),firstLon);
      }
      
      private double distance(double lon,double fLon)
      {
          double phi;
          double r = 6500000; // ############################# real value needed
          phi = (fLon - lon)/Math.PI / 180;
          
          return phi * r; 
      }
  }  

  getX()
  {
      return x;
  }
  
  getY()
  {
      return y;
  }
  
}

public int calculatePlane(List<locationPoint> points){
        
        double part1, part2;
        locationPoint a, b;
        part1=0;
        part2=0;
           
        //part1
        for (int i=0; i<points.size();i++){
                a = points.get(i);
                b = points.get((i+1)%points.size());
                part1 += a.getX()*b.getY();
        }
           
        //part2
        for (int i=0; i<points.size();i++){
                a = points.get((i+1)%points.size());
                b = points.get(i);
                part2 += a.getX()*b.getY();
        }      
                   
        return (int) Math.round((part1 - part2) * 0.5);
}
