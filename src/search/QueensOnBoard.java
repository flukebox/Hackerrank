package search;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Scanner;

public class QueensOnBoard {
  static long M = 1000000007;
  static long totalSol = 0;
  static ArrayList<ArrayList<Long>> rowSols;
  static long[][][][] masks;
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    long tc = sc.nextInt();
    while(tc--!=0){
      int n = sc.nextInt();
      int m = sc.nextInt();
      preProcessing(m);
      long []board = new long[n];
      for(int i=0; i<n; i++){
        String line = sc.next();
        for(int j=0; j<m; j++){
          board[i] |= (line.charAt(j)=='#'?1:0)<<(m-j-1);
        }
      }
      System.out.println(allSoutionRecursiveQR(board, m, n));
    }
    sc.close();    
  }
  
  static void buildRowSolution(long row, int m, boolean lastQ, long currSol, int currIdx, ArrayList<Long> solution){
    if(currIdx==m){
      solution.add(currSol);
      return;
    }
    if(lastQ){
      if((row&(1L<<(m-currIdx-1)))==0){
        buildRowSolution(row, m, true, currSol, currIdx+1, solution);      
      }else{
        buildRowSolution(row, m, false, currSol, currIdx+1, solution);      
      }
    }else{
      if((row&(1L<<(m-currIdx-1)))==0){
        buildRowSolution(row, m, true, currSol|(1L<<(m-currIdx-1)), currIdx+1, solution);
      }
      buildRowSolution(row, m, false, currSol, currIdx+1, solution);      
    }
  }
  
  static void preProcessing(int m){
    rowSols = new ArrayList<ArrayList<Long>>();
    int totalCases = (1<<m);
    for(long i=0; i<totalCases; i++){
      ArrayList<Long> rowSol = new ArrayList<Long>();
      buildRowSolution(i, m, false, 0L, 0, rowSol);
      rowSols.add(rowSol);
    }

    long BITMASK = (1L<<m)-1;
    masks = new long[totalCases][totalCases][totalCases][3];
    long boardRow, currMask;
    for(long br=0; br<totalCases; br++){
      for(long cs=0; cs<totalCases; cs++){
        for(long cm=0; cm<totalCases; cm++){
          boardRow=br^BITMASK;
          currMask=cm|cs;
          masks[(int) br][(int) cs][(int) cm]=new long[]{(currMask&boardRow)&BITMASK, 
              ((currMask<<1)&boardRow)&BITMASK,((currMask>>1)&boardRow)&BITMASK};
        }
      }
    }
  }
  
  static long allSoutionRecursiveQR(long[] board, int m, int n){
    Hashtable<String, Long> masksSol = new Hashtable<String, Long>();
    long ts = -1;
    for(int i=0; i<n; i++){
      ArrayList<Long> possibleRowSol=rowSols.get((int) board[i]);
      long [] cmasks= new long[]{0,0,0};
      if(i==0){
        for(long colSol:possibleRowSol){
          if(((board[i]|cmasks[0]|cmasks[1]|cmasks[2])&colSol)==0){
            if(i+1<n){
              String ms = masks[(int) board[i+1]][(int) colSol][(int) cmasks[0]][0] +":" 
                        + masks[(int) board[i+1]][(int) colSol][(int) cmasks[1]][1]+":"
                        + masks[(int) board[i+1]][(int) colSol][(int) cmasks[2]][2];
              if(masksSol.containsKey(ms)){
                masksSol.put(ms, masksSol.get(ms)+1L);
              }else{
                masksSol.put(ms, 1L);
              }
            }else{
              ts = (ts+1L)%M;
            }
          }
        }
      }else{
        Hashtable<String, Long> temp = new Hashtable<String, Long>();
        for(Entry<String, Long> entry:masksSol.entrySet()){
          String[] key = entry.getKey().split(":");
          cmasks=new long[]{ Long.parseLong(key[0]), Long.parseLong(key[1]), Long.parseLong(key[2])};
          for(long colSol:possibleRowSol){
            if(((board[i]|cmasks[0]|cmasks[1]|cmasks[2])&colSol)==0){
              if(i+1<n){
                String ms = masks[(int) board[i+1]][(int) colSol][(int) cmasks[0]][0] +":" 
                          + masks[(int) board[i+1]][(int) colSol][(int) cmasks[1]][1]+":"
                          + masks[(int) board[i+1]][(int) colSol][(int) cmasks[2]][2];
                if(temp.containsKey(ms)){
                  temp.put(ms, (temp.get(ms)+entry.getValue())%M);
                }else{
                  temp.put(ms, entry.getValue());
                }
              }else{
                ts = (ts+entry.getValue())%M;
              }
            }
          }
        }
        masksSol = temp;
      }
    }
    return ts;
  }
  
  static void printArray(int[][] board){
    totalSol++;
    System.out.println("-----------------SOLUTION:"+totalSol+"----------------");
    for(int[] row:board){
      for(int r:row){
        System.out.print((r==1)?"|Q" : "|_");
      }
      System.out.println();
    }
  }
  
  static void printArray2(int[] placement, int n, int m){
    totalSol++;
    System.out.println("\n\nSolution " + (totalSol));
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        System.out.print((placement[i] == j) ? "|Q" : "|_");
      }
      System.out.println("|");
    }
  }
  
  static boolean singleSolutionRecursive(int[][] board, int i, int j, int n, int m, 
      int[] col, int[] dia, int offset){
    while(j<m){
      if((col[j]|dia[i+j]|dia[offset+i-j])==0){
        board[i][j]=col[j]=dia[i+j]=dia[offset+i-j]=1;
        if(i+1==n||singleSolutionRecursive(board, i+1, 0, n, m, col, dia, offset)){
          return true;
        }
        board[i][j]=col[j]=dia[i+j]=dia[offset+i-j]=0;
      }
      j++;
    }
    return false;
  }
  
  
  static void allSoutionRecursive(int[][] board, int i, int j, int n, int m, 
      int[] col, int[] dia, int offset){
    while(j<m){
      if((col[j]|dia[i+j]|dia[offset+i-j])==0){
        board[i][j]=col[j]=dia[i+j]=dia[offset+i-j]=1;
        if(i+1==n) {
          printArray(board);
        }else{
          allSoutionRecursive(board, i+1, 0, n, m, col, dia, offset);
        }
        board[i][j]=col[j]=dia[i+j]=dia[offset+i-j]=0;
      }
      j++;
    }
  }
  
  
  static boolean unsafe(int[] placement, int y) {
    int x = placement[y];
    for (int i = 1; i <= y; i++) {
      int t = placement[y - i];
      if (t == x || t == x - i || t == x + i) {
        return true;
      }
    }
    return false;
  }
  
  static void singleSolutionNonRecursive(int n, int m){
    allSolutionNonRecursive(n, m, 1);
  }
  
  static int allSolutionNonRecursive(int n, int m, int k){
    int numSol = 0;
    int[] placement = new int[n];
    int currIdx = 0;
    placement[currIdx]=-1;
    while(currIdx>=0){
      do{
        placement[currIdx]++;
      }while(placement[currIdx]<m && unsafe(placement, currIdx));
      if(placement[currIdx]<m){
        if(currIdx<n-1){
          currIdx++;
          placement[currIdx]=-1;
        }else{
          numSol++;
          printArray2(placement, n, m);
          if(numSol==k) return numSol;
        }
      }else{
        currIdx--;
      }
    }
    return numSol;
  }
}





