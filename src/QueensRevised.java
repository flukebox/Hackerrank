import java.util.Scanner;
public class QueensRevised {
  static int totalSol = 0;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int tc = sc.nextInt();
    while(tc--!=0){
      int n = sc.nextInt();
      int [][] board = new int[n][n];
      int[] col = new int[n];
      int offset = (n+n-1)+n-1;
      int[] dia = new int[(n+n-1)<<1];
      
      singleSolutionRecursive(board, 0, 0, n, n, col, dia, offset);
      printArray(board);
    }
    sc.close();    
  }
  
  static void printArray(int[][] board){
    System.out.println("-----------------SOLUTION:"+totalSol+"----------------");
    for(int[] row:board){
      for(int r:row){
        System.out.print((r==1)?"|Q" : "|_");
      }
      System.out.println();
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
}





