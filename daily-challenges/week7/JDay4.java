package week7;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class JDay4{
  public static void main(String[] args) throws IOException{
//    Reader.init(System.in);
//    int tc = Reader.nextInt();
//    while(tc-->0){
//      int n = Reader.nextInt();
//      int m = Reader.nextInt();
//      int k = Reader.nextInt();
//      Hashtable<Integer, Edge> edges = new Hashtable<Integer, Edge>();
//      for(int i=0; i<m; i++){
//        edges.put(i, new Edge(Reader.nextInt(), Reader.nextInt(), Reader.nextInt()));
//      }
//      int[] distsFromA = dijkistra(n, edges.get(k).x, edges);
//      int[] distsFromB = dijkistra(n, edges.get(k).y, edges);
//      solve(n, m, k, edges, distsFromA, distsFromB);
//    }
  int n = 4, m=4, k=1;
  Hashtable<Integer, Edge> edges = new Hashtable<Integer, Edge>();
  edges.put(1, new Edge(1, 2, 10));
  edges.put(2, new Edge(2, 3, 10));
  edges.put(3, new Edge(3, 4, 1));
  edges.put(4, new Edge(4, 1, 5));
  int[] distsFromA = new int[]{0, 10, 6, 5};
  int[] distsFromB = new int[]{10, 0, 10, 11};
  int[] direcA = new int[]{1, -1, 1, 1};
  int[] direcB = new int[]{-1, 1, 1, 1};
  /** dists contains pair of distance of each point from A and B **/
  Dist[] dists = new Dist[n];
  for(int i=0; i<n; i++){
    dists[i] = new Dist(distsFromA[i], distsFromB[i], direcA[i], direcB[i], edges.get(k).c);
    System.out.println(" i => "+dists[i]);
  }

  solve(n, m, k, edges, dists);
  }
  
  static int[] dijkistra(int n, int source, Hashtable<Integer, Edge> edges){
    int[] distfromSource = new int[n];
    return distfromSource;
  }
  
  public static void solve(int n, int m, int k, Hashtable<Integer, Edge> edges, Dist[] dists){
    double minDist = getMinDist(n, 0, dists);
    double start = 0;
    double end = edges.get(k).c; 
    System.out.println("At "+start+"="+getMinDist(n, start, dists)+", "+end+"="+getMinDist(n, start, dists));
    double x, cmin;
    while(true){
      x = (start+end)/2.0;
      cmin = getMinDist(n, x, dists);
      if(cmin > minDist){
        end = x;
      }else if ( cmin < minDist){
        minDist = cmin;
        start = x;
      }else break;
      if( start >= end) break;
      System.out.println("minDist="+minDist+", cmin="+cmin+", x="+x+", start="+start+", end="+end);
    }
    System.out.println(x+" "+minDist);
  }
  
  static double getMinDist(int n,  double x, Dist[] dists){
    double cmin = Double.MIN_VALUE;
    for(int i=0; i<n; i++){
      if (cmin < dists[i].getMin(x)){
        cmin = dists[i].getMin(x);
      }
    }
    System.out.println("cmin="+cmin+", x="+x);
    return cmin;
  }
  
  static class Dist{
    double d1, d2;
    int dr1, dr2;
    int c;
    
    Dist(double d1_, double d2_, int dr1_, int dr2_, int c_){
      d1 = d1_; d2=d2_;
      dr1 = dr1_; dr2=dr2_;
      c = c_;
    }
    double getMin(double x){
      return Math.min(d1+dr1*x, d2+dr2*(c-x));
    }

    @Override
    public String toString() {
      return "( "+d1+", "+d2+", "+dr1+", "+dr2+", "+c+")";
    }
  }
  
  static class Edge{
    int x, y, c;
    Edge(int x_, int y_, int c_){
      x = x_; y=y_; c=c_;
    }
    
    @Override
    public boolean equals(Object obj) {
      if(obj instanceof Edge){
        Edge edge = (Edge) obj;
        if(this.x==edge.x && this.y==edge.y && this.c==edge.c) 
          return true;
      }
      return false;
    }
    
    @Override
    public int hashCode(){
      return x*1567+23*y+c;
    }
    
    @Override
    public String toString() {
      return "( "+x+", "+y+", "+c+")";
    }
  }
  
  static class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;
    static void init(InputStream input) {
      reader = new BufferedReader(new InputStreamReader(input));
      tokenizer = new StringTokenizer("");
    }
    
    static String next() throws IOException{
      while (!tokenizer.hasMoreTokens()){
        tokenizer = new StringTokenizer(reader.readLine());
      }
      return tokenizer.nextToken();
    }
    
    static long nextLong() throws IOException {
      return Long.parseLong( next() );
    }
    
    static int nextInt() throws IOException {
      return Integer.parseInt( next() );
    }

    static double nextDouble() throws IOException {
      return Double.parseDouble( next() );
    }
  }
}




