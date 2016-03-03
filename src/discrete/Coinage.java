package discrete;
import java.util.Scanner;


public class Coinage {
  
  /**
   * @param args
   */
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int K = 4;
    int[] coins  = {1, 2, 5, 10};
    int tc = sc.nextInt();
    while(tc-->0){
      int n = sc.nextInt();
      int[] cc = new int[4];
      for(int j=0; j<4; j++)
        cc[j]=sc.nextInt();
      System.out.println(dynCoinage(n, K, coins, cc));
    }
    sc.close();
  }
  
  /** Recursive approach with NO constraints  **/
  public static int recCoinage(int n, int k, int[] coins){
    if(n==0) return 1;
    if(k<1||n<0) return 0;
    return (recCoinage(n-coins[k-1], k, coins)+recCoinage(n, k-1, coins));
  }
  
  /** Recursive approach with constraints for limited coins **/
  public static int recCoinage(int n, int k, int[] coins, int[] coinCounts){
    if(n==0) return 1;
    if(k<1||n<0) return 0;
    int[] cc = new int[coinCounts.length];
    System.arraycopy(coinCounts, 0, cc, 0, cc.length);
    int temp2 = 0;
    if(cc[k-1]>0){ 
      cc[k-1]=cc[k-1]-1;
      temp2 = recCoinage(n-coins[k-1], k, coins, cc);
    }
    int temp1 = recCoinage(n, k-1, coins, cc);
    return (temp1+temp2);
  }
  
  /** Dynamic approach with NO constraints , OPTIMIZED :D **/
  public static int dynCoinage(int n, int k, int[] coins){
    if(n==0) return 1;
    if(k<1||n<0) return 0;
    
    int[] soln = new int[n+1];
    int[] old = new int[n+1];
    old[0]=1;
    
    for(int i=1; i<=k; i++){
      soln[0]=1;
      for(int j=1; j<=n; j++){
        soln[j]= old[j]+((j-coins[i-1]>=0)?soln[j-coins[i-1]]:0);
      }
      old = soln;
      soln = new int[n+1];      
    }
    return old[n];
  }
  
  /** Dynamic approach with constraints for limited coins **/
  public static int dynCoinage(int n, int k, int[] coins, int[] coinCounts){
    if(n==0) return 1;
    if(k<1||n<0) return 0;
    
    int[] soln = new int[n+1];
    int[] old = new int[n+1];
    old[0]=1;
    
    for(int i=1; i<=k; i++){
      for(int j=1; j<=n; j++){
        soln[0]=1;
        int ck = Math.min(n/coins[i-1], coinCounts[i-1]);
        for(int c=0; c<=ck; c++){
          soln[j]+= ((j-c*coins[i-1]>=0)?old[j-c*coins[i-1]]:0);
        }
      }
      old = soln;
      soln = new int[n+1];
    }
    return old[n];
  }
}
