package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class JDay3 {
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
  
  static int X, Y;
  static int[][] board;
  static int[][] boardcost;
  static int[][] bfs;
  static Point[][] points;
  static Point npoint = new Point();
  static Point[] startQ, emptyQ;

  public static void main(String[] args) throws IOException{
    Reader.init(System.in);
    int n = Reader.nextInt();
    int m = Reader.nextInt();
    int k = Reader.nextInt();
    int q = Reader.nextInt();

    X = n; Y = m;
    board = new int[X][Y];
    boardcost = new int[X][Y];
    bfs = new int[X][Y];
    points = new Point[X][Y];
    startQ = new Point[4*X*Y];
    emptyQ = new Point[4*X*Y];
    
    for(int i=0; i<n; i++){
      for(int j=0; j<m; j++){
        board[i][j] = Reader.nextInt();
        points[i][j] = new Point(i, j);
      }
    }    
    for(int i=0; i<q; i++){
      Point empty = points[Reader.nextInt()-1][Reader.nextInt()-1];
      Point start = points[Reader.nextInt()-1][Reader.nextInt()-1];
      Point target = points[Reader.nextInt()-1][Reader.nextInt()-1];
      System.out.println(solve(board, empty, start, target, k));
    }
  }

  public static int solve(int[][] board, Point em, Point st, Point ta, int k){
    for(int i=0; i<X; i++){
      for(int j=0; j<Y; j++){
        boardcost[i][j]=-1;
      }
    }
    
    int startIdx = 0, endIdx = 0;
    startQ[endIdx]=st;
    emptyQ[endIdx++]=em;
    boardcost[st.x][st.y]=0;
    
    while(startIdx!=endIdx){
      Point s = startQ[startIdx];
      Point e = emptyQ[startIdx++];
      
      int ccost = boardcost[s.x][s.y];
      board[s.x][s.y]=0;
      
      for(Point p:s.neig()){
        if(p.valid && !e.equals(p)){
          int cost = bfs(e, p, k)+1+ccost;
          if(boardcost[p.x][p.y]==-1 || boardcost[p.x][p.y] > cost){
            boardcost[p.x][p.y]=cost;
            startQ[endIdx]=p;
            emptyQ[endIdx++]=s;
          }
        }
      }
      board[s.x][s.y]=1;
    }
    return boardcost[ta.x][ta.y];
  }
  
  public static int bfs(Point start, Point target, int k){
    if((Math.abs(target.x-start.x)+Math.abs(target.y-start.y))>k) return k;
    
    int x1 = Math.max(0, start.x-k-1), x2 = Math.min(X, start.x+k+1);
    int y1 = Math.max(0, start.y-k-1), y2 = Math.min(Y, start.y+k+1);
    
    for(int i=x1 ; i<x2; i++){
      for(int j=y1; j<y2; j++){
        bfs[i][j]=k;
      }
    }
    Queue<Point> queue = new LinkedList<Point>();
    queue.add(start);
    bfs[start.x][start.y]=0;
    while(!queue.isEmpty()){
      Point temp = queue.remove();
      int d = bfs[temp.x][temp.y];
      if(d>=k) continue;
      for(Point p:temp.neig()){
        if(p.valid && bfs[p.x][p.y]>d+1){
          queue.add(p);
          bfs[p.x][p.y]=d+1;
        }
      }
    }
    return Math.min(bfs[target.x][target.y], k);
  }
  
  static class Point{
    int x, y;
    boolean valid = false;

    Point(){}
    Point(int x_, int y_){
      x=x_; y=y_;
      valid = true;
    }
    
    Point(int[]p){
      x=p[0]; y=p[1];
      valid = true;
    }
    
    @Override
    public String toString(){
      return "("+x+","+y+","+valid+")";
    }
    
    @Override
    public boolean equals(Object obj){
      Point p = (Point)obj;
      if((p.x==x) && (p.y==y)){
        return true;
      }else{
        return false;
      }
    }

    Point[] neig(){
      return new Point[]{up(), down(), left(), right()};
    }
    
    Point up(){
      if((x+1)<X && board[x+1][y]==1){
        return points[x+1][y];
      }else{
        return npoint;
      }
    }
    Point down(){
      if((x-1)>=0 && board[x-1][y]==1){
        return points[x-1][y];
      }else{
        return npoint;
      }
    }
    
    Point left(){
      if((y-1)>=0 && board[x][y-1]==1){
        return points[x][y-1];
      }else{
        return npoint;
      }
    }
    
    Point right(){
      if((y+1)<Y && board[x][y+1]==1){
        return points[x][y+1];
      }else{
        return npoint;
      }
    }
  }
}
