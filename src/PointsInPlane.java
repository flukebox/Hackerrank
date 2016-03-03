import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

class Point{
  float x;
  float y;
  public Point(int x, int y){
    this.x = x;
    this.y = y;
  }
  
  public boolean equals(Object o){
    if(o instanceof Point){
      Point obj = (Point) o; 
      if(obj.x == this.x && obj.y == this.y) return true;
    }
    return false;
  }
  
  public int hashCode(){
    return (int) (10000*x+y);
  }
}

class Line{
  float m;
  float b;
  
  public Line(Point p1, Point p2){
    if((p2.x - p1.x)==0) m = 1;
    else  m = (p2.y - p1.y)/(p2.x-p1.x);
    b=(p1.y-m*p1.x);
  }
  
  public Line(int m, int b){
    this.m = m;
    this.b = b;
  }
  
  public boolean lies(Point p){
    if(m*p.x+b-p.y==0){
      return true;
    }
    return false;
  }
  
  public int countLying(Set<Point> points ){
    int count = 0;
    for(Point p:points){
      if(lies(p)) count++;
    }
    return count;
  }
  
  public boolean equals(Object o){
    if(o instanceof Line){
      Line obj = (Line) o; 
      if(obj.m == this.m && obj.b == this.b) return true;
    }
    return false;
  }
  
  public int hashCode(){
    return (int) (10000*b+m);
  }
  
}



public class PointsInPlane {
  
  public static void main(String[] args) {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    Scanner sc = new Scanner(System.in);
    int numOfTestCase = sc.nextInt();
    for(int i=0; i<numOfTestCase; i++){
      int n = sc.nextInt();
      Point[] points = new Point[n];
      HashSet<Point> pointset = new HashSet<Point>();
      for(int j=0; j<n; j++){
        points[j] = new Point(sc.nextInt(), sc.nextInt());
        pointset.add(points[j]);
      }
      
      Hashtable<Line, HashSet<Point>> lines = new Hashtable<Line, HashSet<Point>>();
      // get all the lines and put them in hashtable with corresponding lying points
      for(int j=0; j<n; j++){        
        Point p1 = points[j];
        for(int k=j+1; k<n; k++){
          Point p2 = points[k];
          Line line = new Line(p1, p2);
          if(!lines.containsKey(line)){
            HashSet<Point> ps = new HashSet<Point>();
            ps.add(p1); ps.add(p2);
            lines.put(line, ps);
          }else{
            HashSet<Point> ps = lines.get(line);
            ps.add(p1); ps.add(p2);
          }
        }
      }

      HashSet<Point> copyset = new HashSet<Point>();
      copyset.addAll(pointset);
      
      // choose min number of lines such that lying points constitute all the points
      for(Entry<Line, HashSet<Point>> entry:lines.entrySet()){
        entry.getKey(); 
      }
      
    }
    sc.close();
  }
}
