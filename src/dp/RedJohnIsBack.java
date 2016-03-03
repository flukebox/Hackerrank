package dp;
import java.util.*;

public class RedJohnIsBack {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int numOfTestCase = sc.nextInt();
    for(int i=0; i<numOfTestCase; i++){
      int n = sc.nextInt();
      int n1 = n, n2 = 0;
      int totalCase = 1;
      for(n2=1,n1-=4; n2<=n/4; n2++, n1-=4){
        totalCase = totalCase + calculate(n1, n2);
      }
      int count = 0;
      for(int j=0; j<=totalCase; j++){
        if(prime(j)) count++;
      }
      System.out.println(count);
    }
    sc.close();
  }
  
  public static int calculate(int n1, int n2){
    int nume = 1, deno=1;
    for(int i=1; i<=n2; i++){
      nume *= (n1+i);
      deno *=i;
    }
    return nume/deno;
  }
  
  
  public static boolean prime(int n){
    if(n<2) return false;
    int sqrtn = (int) Math.sqrt(n);
    for(int i=2; i<=sqrtn; i++){
      if(n/i>=1 && n%i==0) return false;
    }
    return true;
  }
}