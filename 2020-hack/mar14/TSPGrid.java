package mar14;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 
0 0
1 0 
0 1
2 0
2 1
2 2
0 2
1 2
 
Need more heuristics

3 4
1 2 3
5 4 3
7 6 5 

1 3 5 7 
1 2 2 3

6 6
1 2 3 4 2
5 4 3 2 1
7 6 5 4 3 
4 2 2 7 6
7 5 8 6 7
2 3 4 3 2

2 2 3 4 2 1
1 2 3 5 6 7
7 3 5 4 3 2
1 3 5 7 5 2
1 2 2 3 5 2


6 7
1 2 3 4 2 1
5 4 3 2 1 2
7 6 5 4 3 3
4 2 2 7 6 4
7 5 8 6 7 5
2 3 4 3 2 6

2 2 3 4 2 1 1
1 2 3 5 6 7 2
7 3 5 4 3 2 3
1 3 5 7 5 2 4 
1 2 2 3 5 2 5


4 10
1 2 3 4 2 1 2 3 4 
5 4 3 2 1 4 3 2 1 
7 6 5 4 3 7 6 5 4
4 2 2 7 6 2 2 7 6

2 2 3 4 2 1 3 4 3 2
1 2 3 5 6 7 3 5 6 7
7 3 5 4 3 2 3 5 6 7


5 8
1 2 3 4 2 1 2
5 4 3 2 1 4 3 
7 6 5 4 3 7 6
4 2 2 7 6 2 2
7 5 8 6 7 2 2

2 2 3 4 2 1 3 3
1 2 3 5 6 7 3 6
7 3 5 4 3 2 3 5
1 3 5 7 5 2 5 7

5 10
1 2 3 4 2 1 2 3 4 
5 4 3 2 1 4 3 2 1 
7 6 5 4 3 7 6 5 4
4 2 2 7 6 2 2 7 6
7 5 8 6 7 2 2 7 6

2 2 3 4 2 1 3 4 3 2
1 2 3 5 6 7 3 5 6 7
7 3 5 4 3 2 3 5 6 7
1 3 5 7 5 2 5 7 5 2



6 8
1 2 3 4 2 1 2  
5 4 3 2 1 4 3 
7 6 5 4 3 7 6
4 2 2 7 6 2 2
7 5 8 6 7 2 2
2 3 4 3 2 3 4

2 2 3 4 2 1 3 4 
1 2 3 5 6 7 3 5
7 3 5 4 3 2 3 5
1 3 5 7 5 2 5 7
1 2 2 3 5 2 5 7


6 9
1 2 3 4 2 1 2 3
5 4 3 2 1 4 3 2 
7 6 5 4 3 7 6 5
4 2 2 7 6 2 2 7
7 5 8 6 7 2 2 7
2 3 4 3 2 3 4 3

2 2 3 4 2 1 3 4 3
1 2 3 5 6 7 3 5 6
7 3 5 4 3 2 3 5 6
1 3 5 7 5 2 5 7 5
1 2 2 3 5 2 5 7 5


6 10
1 2 3 4 2 1 2 3 4 
5 4 3 2 1 4 3 2 1 
7 6 5 4 3 7 6 5 4
4 2 2 7 6 2 2 7 6
7 5 8 6 7 2 2 7 6
2 3 4 3 2 3 4 3 2

2 2 3 4 2 1 3 4 3 2
1 2 3 5 6 7 3 5 6 7
7 3 5 4 3 2 3 5 6 7
1 3 5 7 5 2 5 7 5 2
1 2 2 3 5 2 5 7 5 2


 */
public class TSPGrid {
  private static long iterations = 0;
  private static int totalSol = 0;
  private static boolean[] validrc;
  private static boolean[] validcc;
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int m = sc.nextInt(); 
    int n = sc.nextInt();
    int[][] horz = new int[m][n-1];
    int[][] vert = new int[m-1][n];
    for(int i=0; i<m; i++){
      for(int j=0; j<n-1; j++){
        horz[i][j]=sc.nextInt();
      }
    }
    for(int i=0; i<m-1; i++){
      for(int j=0; j<n; j++){
        vert[i][j]=sc.nextInt();
      }
    }
    
    validrc = new boolean[(1<<m)-1];
    for(int i=0; i<m-1; i++){
      validrc[(1<<i)-1]=true;
      validrc[(1<<(m-1))+((1<<i)-1)]=true;
    }
    validcc = new boolean[(1<<n)-1];
    for(int i=0; i<n-1; i++){
      validcc[(1<<i)-1]=true;
      validcc[(1<<(n-1))+((1<<i)-1)]=true;
    }

    
    int nodes = m*n;
    int[] dist = new int[nodes];
    int[][] adj = new int[nodes][];
    int[][] adjcost = new int[nodes][];
    for(int i=0; i<nodes; i++){
      dist[i]=(Math.abs((i/n)-1)+Math.abs(i%n));
      ArrayList<Integer> cost = new ArrayList<Integer>();
      ArrayList<Integer> temp = new ArrayList<Integer>();
      int edges = 0;
      if((i-1)/n==i/n && i-1>=0){
        int co = horz[i/n][(i-1)%n]; 
        int idx = Collections.binarySearch(cost, co);
        if(idx<0) idx = -idx -1;
        cost.add(idx, co);
        temp.add(idx, i-1);
        edges++;
      }

      if((i+1)/n==i/n && i+1<nodes){
        int co = horz[i/n][i%n]; 
        int idx = Collections.binarySearch(cost, co);
        if(idx<0) idx = -idx -1;
        cost.add(idx, co);
        temp.add(idx, i+1);
        edges++;
      }
      
      if((i-n)%n==i%n && i-n>=0){
        int co = vert[(i-n)/n][i%n]; 
        int idx = Collections.binarySearch(cost, co);
        if(idx<0) idx = -idx -1;
        cost.add(idx, co);
        temp.add(idx, i-n);
        edges++;
      }
      if((i+n)%n==i%n && i+n<nodes){
        int co = vert[i/n][i%n]; 
        int idx = Collections.binarySearch(cost, co);
        if(idx<0) idx = -idx -1;
        cost.add(idx, co);
        temp.add(idx, i+n);
        edges++;
      }
      adj[i]=new int[edges];
      adjcost[i]=new int[edges];
      for(int j=0; j<edges; j++){
        adj[i][j]=temp.get(j);
        adjcost[i][j]=cost.get(j);
      }
    }
    hamCycle(m, n, nodes, adj, adjcost, dist);
    System.out.println("Total Number of iterations ="+iterations+", totalSols="+totalSol);
    sc.close();
  }
  
  
  private static int hamCycleUtil(int m, int n, int V, int[][] adj, int[][] adjcost, 
      int[] dist, int path[], int[] seen,  int pos, int currCost, int minCost, int[] rc, int[] cc, int r, int c){
    iterations++;
    if (pos==V){
      for(int i=0; i<adj[path[pos-1]].length; i++){
        if(adj[path[pos-1]][i]==path[0]){
          totalSol++;
//          printSolution(V, path);
          currCost=currCost+adjcost[path[pos-1]][i];
          if(currCost<minCost){
            return currCost;
          }
        }
      }
      return minCost;
    }
    
    for (int i = 0; i < adj[path[pos-1]].length; i++){
      int v = adj[path[pos-1]][i];
      /* Check if this vertex can be added to Hamiltonian Cycle */
      if (seen[v]==0 && ((V-pos+1)>=dist[v]) && currCost+adjcost[path[pos-1]][i]<minCost){
        path[pos] = v;
        seen[v]=1;
        rc[v/n]--;
        cc[v%n]--;
        if(rc[v/n]==0) r|=1<<(m-1-v/n);
        if(cc[v%n]==0) c|=1<<(n-1-v%n);
        if(validcc[c] && validrc[r]){
          minCost=hamCycleUtil(m, n, V, adj, adjcost, dist, path, seen, pos+1, currCost+adjcost[path[pos-1]][i], minCost, rc, cc, r, c);
        }
        path[pos] = -1;
        seen[v]=0;
        rc[v/n]++;
        cc[v%n]++;
        if(rc[v/n]!=0 && (r&1<<(m-1-v/n))!=0) r^=1<<(m-1-v/n);
        if(cc[v%n]!=0 && (c&1<<(n-1-v%n))!=0) c^=1<<(n-1-v%n);
      }
    }
    return minCost;
  }
  
  private static int hamCycleUtilSingle(int m, int n, int V, int[][] adj, int[][] adjcost, 
      int[] dist, int path[], int[] seen,  int pos, int currCost, int minCost, int[] rc, int[] cc, int r, int c){
    iterations++;
    if (pos==V){
      for(int i=0; i<adj[path[pos-1]].length; i++){
        if(adj[path[pos-1]][i]==path[0]){
          totalSol++;
          printSolution(V, path);
          currCost=currCost+adjcost[path[pos-1]][i];
          if(currCost<minCost){
            return currCost;
          }
        }
      }
      return minCost;
    }
    
    for (int i = 0; i < adj[path[pos-1]].length; i++){
      int v = adj[path[pos-1]][i];
      /* Check if this vertex can be added to Hamiltonian Cycle */
      if (seen[v]==0 && ((V-pos+1)>=dist[v]) && currCost+adjcost[path[pos-1]][i]<minCost){
        path[pos] = v;
        seen[v]=1;
        rc[v/n]--;
        cc[v%n]--;
        if(rc[v/n]==0) r|=1<<(m-1-v/n);
        if(cc[v%n]==0) c|=1<<(n-1-v%n);
        if(validcc[c] && validrc[r]){
          int temp = hamCycleUtilSingle(m, n, V, adj, adjcost, dist, path, seen, pos+1, currCost+adjcost[path[pos-1]][i], minCost, rc, cc, r, c);
          if(temp!=minCost){
            return temp;
          }
        }
        path[pos] = -1;
        seen[v]=0;
        rc[v/n]++;
        cc[v%n]++;
        if(rc[v/n]!=0 && (r&1<<(m-1-v/n))!=0) r^=1<<(m-1-v/n);
        if(cc[v%n]!=0 && (c&1<<(n-1-v%n))!=0) c^=1<<(n-1-v%n);
      }
    }
    return minCost;
  }

  
  private static void hamCycle(int m, int n, int V,int[][] adj, int[][] adjcost, int[] dist){
    if((V&1)==1){
      System.out.println(0);
    }else{
      int[] path = new int[V];
      int[] seen = new int[V];
      int[] rc = new int[m];
      int[] cc = new int[n];
      Arrays.fill(rc, n);
      Arrays.fill(cc, m);
      Arrays.fill(path, -1);
      
      int minCost = Integer.MAX_VALUE;
      int currCost = 0;
      path[0] = adj[0][1]; seen[path[0]]=1; 
      path[1] = 0;seen[path[1]]=1;currCost+=adjcost[0][1];
      path[2] = adj[0][0];seen[path[2]]=1;currCost+=adjcost[0][0];
      cc[0]-=2;
      rc[0]-=2;
      int r=0, c=0;
      if(rc[0]==0) r|=1<<(m-1);
      if(cc[0]==0) c|=1<<(n-1);
      minCost=hamCycleUtilSingle(m, n, V, adj, adjcost, dist, path, seen, 3, currCost, minCost, rc, cc, r, c);
      if(minCost==Integer.MAX_VALUE){
        System.out.println(0);
      }else{
        System.out.println(minCost);
      }
    }
  }
  
  /* A utility function to print solution */
  public static void printSolution(int V, int path[]){
    System.out.println("Solution Exists: for V ="+V+"\n");
    for(int i = 0; i < V; i++)
      System.out.print(path[i]+" ");
    System.out.println();
  }
}
/*
 * 
25

 
100
0 0
0 1
0 2
0 3
0 4
0 5
0 6
0 7
0 8
0 9
1 0
1 1
1 2
1 3
1 4
1 5
1 6
1 7
1 8
1 9
2 0
2 1
2 2
2 3
2 4
2 5
2 6
2 7
2 8
2 9
3 0
3 1
3 2
3 3
3 4
3 5
3 6
3 7
3 8
3 9
4 0
4 1
4 2
4 3
4 4
4 5
4 6
4 7
4 8
4 9
5 0
5 1
5 2
5 3
5 4
5 5
5 6
5 7
5 8
5 9
6 0
6 1
6 2
6 3
6 4
6 5
6 6
6 7
6 8
6 9
7 0
7 1
7 2
7 3
7 4
7 5
7 6
7 7
7 8
7 9
8 0
8 1
8 2
8 3
8 4
8 5
8 6
8 7
8 8
8 9
9 0
9 1
9 2
9 3
9 4
9 5
9 6
9 7
9 8
9 9
 * 
 */
