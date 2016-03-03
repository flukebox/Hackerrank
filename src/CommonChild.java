import java.util.Scanner;

public class CommonChild {
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String first = sc.nextLine();
    String second = sc.nextLine();
    int n = first.length();
    int[][] cc = new int[n+1][n+1];
  
    for(int i=0; i<=n; i++){
      cc[i][0]=0;
      cc[0][i]=0;
    }
    
    for(int i=1; i<=n; i++){
      for(int j=1; j<=n; j++){
        if(first.charAt(i-1)==second.charAt(j-1)){
          cc[i][j] = max(cc[i-1][j-1]+1, cc[i-1][j], cc[i][j-1]);
        }else{
          cc[i][j] = max(cc[i-1][j-1], cc[i-1][j], cc[i][j-1]);
        }
      }
    }
    System.out.println(cc[n][n]);
    sc.close();
  }
  
  private static int  max(int a, int b, int c){
    return Math.max(Math.max(a, b), Math.max(b, c));
  }
  
}
