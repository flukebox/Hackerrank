package search;

import java.util.*;

public class CoinOnTableAC {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();
    int k = sc.nextInt();
    char[][] board = new char[n][m];
    int destI=0, destJ=0;
    for(int i=0; i<n; i++){
      String line = sc.next();
      for(int j=0; j<m; j++){
        board[i][j]=line.charAt(j);
        if(board[i][j]=='*'){destI=i;destJ=j;}
      }
    }
    if(k<(destI+destJ)){
      System.out.println("-1");
    }else{
      System.out.println(solve(board, k, n, m, destI, destJ));
    }
    sc.close();
  }
  
  static int solve(char [][] board, int k, int n, int m, int destI, int destJ){    
    boolean done = false;
    int i=0,j=0;
    String path = "";
    while(!done&&path.length()<=k){
      switch(board[i][j]){
        case 'U': path+="U";i--;break;
        case 'L': path+="L";j--;break;
        case 'D': path+="D";i++;break;
        case 'R': path+="R";j++;break;        
        case '*': done=true;break;
      }  
      if(i<0||j<0||i>=n||j>=m)break;
    }
    if(done)return 0;
    else return dijkstra(board, k, n, m, destI, destJ);
  }

  static class Node{
    int i;
    int j;
    Node(int i, int j){
      this.i=i;
      this.j=j;
    }
    
    @Override
    public String toString() {
      return i+":"+j;
    }
  }
  
  static void printArray(int [][] array){
    for(int[] row:array){
      for(int r:row){
        System.out.print(r+" ");
      }
      System.out.println();
    }    
  }
  
  static boolean isVisitable(int i, int j, int n, int m, int count){
    if((i==0&&j==0)||(i==0&&j==m-1)||(i==n-1&&j==0)||(i==n-1&&j==m-1))
      return count<2;
    if(((i==0||i==n-1)&&(j>0 && j<m-1))||((j==0||j==m-1)&&(i>0 && i<n-1)))
      return count<3;
    return count<4;
  }
  
  static int dijkstra(char [][] board, int k, int n, int m, int destI, int destJ){
    int[][] table = new int[n][m];
    int[][] visited = new int[n][m];
    int[][] pathlen = new int[n][m];

    for(int i=0; i<n; i++){
      Arrays.fill(table[i], k+1);
    }
    table[0][0]=0;
    LinkedList<Node> queue = new LinkedList<Node>();
    queue.add(new Node(0, 0));
    visited[0][0]=1;
    while(!queue.isEmpty()){
      Node node = queue.poll();
      // U
      int temp = 0;
      if(node.i>0){
        temp=Math.min(table[node.i-1][node.j], (board[node.i][node.j]=='U'?0:1)+table[node.i][node.j]);
        if((temp!=table[node.i-1][node.j]||visited[node.i-1][node.j]==0)){
          if(board[node.i-1][node.j]!='*' && pathlen[node.i][node.j]+1<=k){
            queue.add(new Node(node.i-1, node.j));
          }
          visited[node.i-1][node.j]++;
          pathlen[node.i-1][node.j]=pathlen[node.i][node.j]+1;
        }
        table[node.i-1][node.j]=temp;
      }
      // D
      if(node.i+1<n){
        temp=Math.min(table[node.i+1][node.j], (board[node.i][node.j]=='D'?0:1)+table[node.i][node.j]);
        if((temp!=table[node.i+1][node.j]||visited[node.i+1][node.j]==0)){
          if(board[node.i+1][node.j]!='*' && pathlen[node.i][node.j]+1<=k){
            queue.add(new Node(node.i+1, node.j));
          }
          visited[node.i+1][node.j]++;
          pathlen[node.i+1][node.j]=pathlen[node.i][node.j]+1;
        }
        table[node.i+1][node.j] =temp;
      }
      
      // L
      if(node.j>0){
        temp=Math.min(table[node.i][node.j-1], (board[node.i][node.j]=='L'?0:1)+table[node.i][node.j]);
        if((temp!=table[node.i][node.j-1]||visited[node.i][node.j-1]==0)){
          if(board[node.i][node.j-1]!='*' && pathlen[node.i][node.j]+1<=k){
            queue.add(new Node(node.i, node.j-1));
          }
          visited[node.i][node.j-1]++;
          pathlen[node.i][node.j-1]=pathlen[node.i][node.j]+1;
        }
        table[node.i][node.j-1]=temp;
      }
      
      // R
      if(node.j+1<m){
        temp=Math.min(table[node.i][node.j+1], (board[node.i][node.j]=='R'?0:1)+table[node.i][node.j]);
        if((temp!=table[node.i][node.j+1]||visited[node.i][node.j+1]==0)){
          if(board[node.i][node.j+1]!='*' && pathlen[node.i][node.j]+1<=k){
            queue.add(new Node(node.i, node.j+1));
          }
          visited[node.i][node.j+1]++;
          pathlen[node.i][node.j+1]=pathlen[node.i][node.j]+1;
        }
        table[node.i][node.j+1]=temp;
      }
    }
    return table[destI][destJ];
  }
}