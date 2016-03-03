import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

class COLARR{
  static class Node implements Comparable<Node>{
    int i, d;
    Node(int i_, int d_){
      i=i_;d=d_;
    }
    @Override
    public int compareTo(Node o){
      return this.d-o.d;
    }
    @Override
    public String toString(){
      return i+":"+d;
    }    
  }
 public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int tc = Integer.parseInt(sc.nextLine());
    while(tc-->0){
      int n = sc.nextInt();
      int m = sc.nextInt();
      int k = sc.nextInt();
      // skip to next line
      sc.nextLine();
      int[] cells = new int[n];
      String[] cell = sc.nextLine().split(" ");
      for(int i=0; i<n; i++){
        cells[i]=Integer.parseInt(cell[i]);
      }
      int[][] b = new int[n][m];
      for(int i=0; i<n; i++){
        String[] str = sc.nextLine().split(" ");
        for(int j=0; j<m; j++){
          b[i][j]=Integer.parseInt(str[j]);
        }
      }
      int[][] c = new int[n][m];
      ArrayList<Node> delta = new ArrayList<Node>(); 
      HashMap<Integer, Integer> table = new HashMap<Integer, Integer>();
      for(int i=0; i<n; i++){
        int diff = 0; int idx = -1;
        String[] str = sc.nextLine().split(" ");
        for(int j=0; j<m; j++){
          c[i][j]=Integer.parseInt(str[j]);
          if((b[i][j]-c[i][j]-b[i][cells[i]-1])>diff){
            diff = (b[i][j]-c[i][j]-b[i][cells[i]-1]);
            idx = i;
          }
        }
        if(idx!=-1){
          delta.add(new Node(idx,diff));
        }
      }
      Collections.sort(delta);
      for(int i=0; i<k && (delta.size()-1-i)>=0; i++){
        Node node = delta.get((delta.size()-1-i));
        table.put(node.i, node.d);
      }
      int score = 0;
      for(int i=0; i<n; i++){
        if(table.containsKey(i)){
          score+=table.get(i);
        }
        score+=b[i][cells[i]-1];
      }
      System.out.println(score);
    }  
    sc.close();
  }    
}
