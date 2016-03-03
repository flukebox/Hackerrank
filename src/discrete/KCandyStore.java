package discrete;
import java.math.BigInteger;
import java.util.Scanner;

public class KCandyStore {
  private static int prime = 1000000000;
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int tc = sc.nextInt();
    for(int i=0; i<tc; i++){
      int n = sc.nextInt();
      int k = sc.nextInt();
      System.out.println(getNCK(n+k-1, n-1));
    }
    sc.close();
  }
  
  public static int getNCK(int n, int k){
    int totalValues = (n/2)+1;
    int[] values = new int[n+1];
    values[0]=1;
    BigInteger mod = new BigInteger(""+prime);
    BigInteger bi = new BigInteger("1");
    for(int j=1; j<=n; j++){
      if(j<totalValues){
        bi = bi.multiply(new BigInteger(""+(n-j+1))).divide(new BigInteger(""+j));
        values[j]=bi.mod(mod).intValue();
      }else{
        values[j]=values[n-j];
      }
    }
    return values[k];
  }
  
}
