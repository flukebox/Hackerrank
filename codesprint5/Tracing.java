import java.math.BigInteger;
import java.util.Scanner;

public class Tracing {
  private static int M = 1000000007;
  private static int[] MI = new int[1000002];
  static{
    MI[1]=1;
    BigInteger bM = new BigInteger(""+M);
    for(int i=2; i<MI.length; i++){
      MI[i]=(new BigInteger(""+i)).modInverse(bM).intValue();
    }
  }
  
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int tc = sc.nextInt();
    while(tc-->0){
      System.out.println(solve(sc.nextInt(),sc.nextInt()));
    }
    sc.close();
  }

  static long[] ncr(int n){
    long[] vals = new long[n+1];
    int totalValues = (n/2)+1;
    vals[0]=1;
    for(int j=1; j<=n; j++){
      if(j<totalValues){
        vals[j]=(vals[j-1]*(n-j+1))%M*MI[j]%M;
      }else{
        vals[j]=vals[n-j];
      }
    }
    return vals;
  }

  static int solve(int n, int m){
    if(n<m){n^=m;m^=n;n^=m;}
    return (int) ncr(n+m-2)[n-1];
  }  
}
