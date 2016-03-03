package discrete;
import java.math.BigInteger;
import java.util.Scanner;


public class NcRTable {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int prime = 1000000000;
    int tc = sc.nextInt();
    for(int i=0; i<tc; i++){
      int n = sc.nextInt();
      int totalValues = (n/2)+1;
      int[] values = new int[totalValues];
      values[0]=1;
      System.out.print(1);
      BigInteger mod = new BigInteger(""+prime);
      BigInteger bi = new BigInteger("1");
      for(int j=1; j<=n; j++){
        if(j<totalValues){
          bi = bi.multiply(new BigInteger(""+(n-j+1))).divide(new BigInteger(""+j));
          values[j]=bi.mod(mod).intValue();
          System.out.print(" "+values[j]);
        }else{
          System.out.print(" "+values[n-j]);
        }
      }
      System.out.println();
    }
    sc.close();
  }
}
