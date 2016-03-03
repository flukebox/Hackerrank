package dp;
import java.util.Hashtable;
import java.util.Scanner;

public class LegoBlocks {
  private static long[] C = new long[1001];
  private static long M = 1000000007;
  static{
    C[0]=1; C[1]=1; C[2]=2; C[3]=4; C[4]=8;
    for(int i=5; i<1001; i++ ){
      for(int j=1; j<=4; j++)
        C[i]=(C[i-j]+C[i])%M;
    }
  }
  
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int numerbOfTestCase = sc.nextInt();
    for(int i=0; i<numerbOfTestCase; i++){
      int n = sc.nextInt();
      int m = sc.nextInt();
      System.out.println(numOfWays(n, m));
    }
    sc.close();
  }

  public static long modPower(long a, int b){
    long temp = 1;
    if(b==0) return 1;
    temp = modPower(a, b/2);
    temp = (temp*temp)%M;
    if(b%2==0){
      return temp;
    }else{
      return (a*temp)%M;
    }
  }
  
  public static long numOfWays(int n, int m){
    long[] s = new long[m+1];
    Hashtable<Integer, Long> powC = new Hashtable<Integer, Long>();
    for(int i=1; i<=m; i++){
      long plus = modPower(C[i], n);
      long minus = 0;
      for(int j=1; j<i; j++){
        if(!powC.containsKey(i-j)){
          long temp = modPower(C[i-j], n);
          minus = (minus+s[j]*temp)%M;
          powC.put(i-j, temp);
        }else{
          minus = (minus+s[j]*powC.get(i-j))%M;
        }
      }
      s[i] = plus - minus;
      if(s[i]<0) s[i]+=M;
    }
    return s[m];
  }
}