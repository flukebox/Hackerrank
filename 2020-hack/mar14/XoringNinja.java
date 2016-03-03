package mar14;
import java.util.Scanner;

public class XoringNinja {
  private static final long M = 1000000007;
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int tc = sc.nextInt();
    while(tc-->0){
      int n = sc.nextInt();
      int or = 0;
      int[] A = new int[n];
      for(int i=0; i<n; i++){
        A[i]=sc.nextInt();
        or |= A[i];
      }
      long sum = 0;
      long base = modPower(2, n-1); // 2^(n-1)
      // number of elements (1,2^0), (2,2^1), (3,2^2), (4,2^3)
      // index position (1, 2^0), (2, 2^1), (3, 2^2), (4, 2^3)
      for(int i=0; i<31; i++){
        if((or&(1<<i))!=0)
        sum = (sum + (1<<i))%M;
      }
      sum = (sum*base)%M;
      System.out.println(sum);
    }
    sc.close();
  }
  
  static int modPower(int base, int pow){
    long t = 1;
    long p = base;
    while(pow >= 1) {
      if((pow & 1) == 1) t = (t * p) % M;
      p = (p * p) % M;
      pow >>>= 1;
    }
    return (int) t;
  }

}
