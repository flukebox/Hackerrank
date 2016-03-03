import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;


public class Equations {
  private static final int M = 1000007;
  private static final int[] ps = {29, 34483};
  private static final int[] qs = {1, 1};
  private static final int[] pqs = {29, 34483};
  private static Hashtable<Integer, int[][]> modularFactorials = new Hashtable<Integer, int[][]>();

  /**
   * 1/x + 1/y = 1/n!
   * y = (a+1) n!
   * x = (a+1)/a n!
   * such that x,y are integers greater than 1
   * so basically solution should be .. we should a such that a divide (a+1)n! completely.
   * that is only possible if a multiplication of subset of elements {1, 2, 3...n} 
   * so total solution would be 2^n
   */
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int sol = modPower(2, n)-1;
    if(sol<0) sol=sol+M;
    System.out.println(sol);
    sc.close();
  }
  
  
  public static int modPower(int base, int pow){
    pow %= (M-1);
    long t = 1;
    long p = base;
    while(pow >= 1) {
      if((pow & 1) != 0) t = (t * p) % M;
      p = (p * p) % M;
      pow >>>= 1;
    }
    return (int) t;
  }
  
  public static void computeModularFactorials(int ptoq, int p, int q){
    int[] factorials = new int[ptoq];
    int[] inverseFactorials = new int[ptoq];
    factorials[0] = 1;
    inverseFactorials[0] = 1;
    for(int i = 1; i < ptoq; i++){
      factorials[i] = ((i % p == 0)?1:i)*factorials[i-1]%ptoq;
      inverseFactorials[i] = ((i % p == 0)?inverseFactorials[i-1]:computeInverse(factorials[i], ptoq)%ptoq);
    }
    modularFactorials.put(ptoq, new int[][]{factorials, inverseFactorials});
  }
  
  
  public static int computeInverse(int a, int b) {
    int x0 = 1, y0 = 0;
    int x1 = 0, y1 = 1;
    while(b != 0) {
      int q = a/b;
      int x = x0 - q*x1;
      int y = y0 - q*y1;
      x0 = x1;
      y0 = y1;
      x1 = x;
      y1 = y;
      int tmp = b;
      b = a%b;
      a = tmp;
    }
    return new int[]{a,x0,y0}[1];
  }
  
  public static List<Integer> toBase(int n, int base) {
    List<Integer> digits = new ArrayList<Integer>();
    while(n != 0) {
      digits.add(n%base);
      n /= base;
    }
    return digits;
  }
  
  
  public static List<Integer> computeE(int p, List<Integer> ndigits, List<Integer> mdigits, List<Integer> rdigits) {
    List<Integer> e = new ArrayList<Integer>();
    int carry = 0;
    for(int i = 0; i < mdigits.size(); i++) {
      carry += mdigits.get(i)+rdigits.get(i);
      carry /= p;
      e.add(carry);
    }
    int sum = 0;
    for(int i=e.size()-1; i >= 0; i--) {
      sum += e.get(i);
      e.set(i, sum);
    }
    return e;
  }
  
  public static void packZeros(List<Integer> ndigits, List<Integer> mdigits, List<Integer> rdigits){
    for(int i = mdigits.size(); i < ndigits.size(); i++) mdigits.add(0);
    for(int i = rdigits.size(); i < ndigits.size(); i++) rdigits.add(0);
  }
  
  public static List<Integer> computeN_js(List<Integer> digits, int p, int q) {
    List<Integer> N_js = new ArrayList<Integer>();
    for(int j = 0; j <=digits.size(); j++) {
      int result = 0;
      int pp = 1;
      for(int i = j; i<j+q && i<digits.size(); i++) {
        result += digits.get(i)*pp;
        pp *= p;
      }
      N_js.add(result);
    }
    return N_js;
  }
  
  public static int generalizedLucas(int n, int m, int p, int q, int ptoq) {
    int r = n-m;
    List<Integer> ndigits = toBase(n, p);
    List<Integer> mdigits = toBase(m, p);
    List<Integer> rdigits = toBase(r, p);
    packZeros(ndigits, mdigits, rdigits);
    List<Integer> e = computeE(p, ndigits, mdigits, rdigits);
    List<Integer> nN_js = computeN_js(ndigits, p, q);
    List<Integer> mN_js = computeN_js(mdigits, p, q);
    List<Integer> rN_js = computeN_js(rdigits, p, q);
    int result = 1;
    for(int i = 0; i < e.get(0); i++) {
      result = (result*p)%ptoq;
    }
    for(int N_j : nN_js) {
      result = (result*modularFactorials.get(ptoq)[0][N_j])%ptoq;
    }
    for(int N_j : mN_js) {
      result = (result*modularFactorials.get(ptoq)[1][N_j])%ptoq;
    }
    for(int N_j : rN_js) {
      result = (result*modularFactorials.get(ptoq)[1][N_j])%ptoq;
    }
    if(p != 2 || q < 3) {
      if(q <= e.size() && e.get(q-1)%2 != 0) {
        result = (-1*result) % ptoq;
      }
    }
    return result >= 0 ? result : result + ptoq;
  }

}
