package math;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;

public class NCR {
  private static int M  = 3 * 3 * 3 * 11 * 13 * 37;
  private static int[]  ps = {3, 11, 13, 37};
  private static int[]  qs = {3, 1, 1, 1};
  private static int[]  pqs= {27, 11, 13, 37 };
  private static Hashtable<Integer, int[][]> modularFactorials = new Hashtable<Integer, int[][]>();
  
  public static void main(String[] args) throws IOException {
    Reader.init(System.in);
    Writer.init(System.out);
    for (int i = 0; i < 4; i++) {
      computeModularFactorials(pqs[i], ps[i], qs[i]);
    }
    int tc = Reader.nextInt();
    while (tc-- > 0) {
      int n = Reader.nextInt();
      int r = Reader.nextInt();
      int ncr = 0;
      int[] rs = new int[4];
      for (int i = 0; i < 4; i++) {
        rs[i] = generalizedLucas(n, r, ps[i], qs[i], pqs[i]);
      }
      for (int i = 0; i < 4; i++) {
        ncr = (ncr + rs[i] * (M / pqs[i] * computeInverse(M / pqs[i], pqs[i])))
            % M;
      }
      ncr = (ncr >= 0 ? ncr : ncr + M);
      Writer.writeInt(ncr);
    }
    Writer.flush();
  }
  
  public static void computeModularFactorials(int ptoq, int p, int q) {
    int[] factorials = new int[ptoq];
    int[] inverseFactorials = new int[ptoq];
    factorials[0] = 1;
    inverseFactorials[0] = 1;
    for (int i = 1; i < ptoq; i++) {
      factorials[i] = ((i % p == 0) ? 1 : i) * factorials[i - 1] % ptoq;
      inverseFactorials[i] = ((i % p == 0)
          ? inverseFactorials[i - 1]
          : computeInverse(factorials[i], ptoq) % ptoq);
    }
    modularFactorials.put(ptoq, new int[][]{factorials, inverseFactorials});
  }
  
  public static int computeInverse(int a, int b) {
    int x0 = 1, y0 = 0;
    int x1 = 0, y1 = 1;
    while (b != 0) {
      int q = a / b;
      int x = x0 - q * x1;
      int y = y0 - q * y1;
      x0 = x1;
      y0 = y1;
      x1 = x;
      y1 = y;
      int tmp = b;
      b = a % b;
      a = tmp;
    }
    return new int[]{a, x0, y0}[1];
  }
  
  public static List<Integer> toBase(int n, int base) {
    List<Integer> digits = new ArrayList<Integer>();
    while (n != 0) {
      digits.add(n % base);
      n /= base;
    }
    return digits;
  }
  
  public static int fromBase(List<Integer> digits, int base) {
    int n = 0;
    int b = 1;
    for (int i = 0; i < digits.size(); i++) {
      n += digits.get(i) * b;
      b *= base;
    }
    return n;
  }
  
  public static List<Integer> computeE(int p, List<Integer> ndigits,
      List<Integer> mdigits, List<Integer> rdigits) {
    List<Integer> e = new ArrayList<Integer>();
    int carry = 0;
    for (int i = 0; i < mdigits.size(); i++) {
      carry += mdigits.get(i) + rdigits.get(i);
      carry /= p;
      e.add(carry);
    }
    int sum = 0;
    for (int i = e.size() - 1; i >= 0; i--) {
      sum += e.get(i);
      e.set(i, sum);
    }
    return e;
  }
  
  public static void packZeros(List<Integer> ndigits, List<Integer> mdigits,
      List<Integer> rdigits) {
    for (int i = mdigits.size(); i < ndigits.size(); i++)
      mdigits.add(0);
    for (int i = rdigits.size(); i < ndigits.size(); i++)
      rdigits.add(0);
  }
  
  public static List<Integer> computeN_js(List<Integer> digits, int p, int q) {
    List<Integer> N_js = new ArrayList<Integer>();
    for (int j = 0; j <= digits.size(); j++) {
      int result = 0;
      int pp = 1;
      for (int i = j; i < j + q && i < digits.size(); i++) {
        result += digits.get(i) * pp;
        pp *= p;
      }
      N_js.add(result);
    }
    return N_js;
  }
  
  public static int generalizedLucas(int n, int m, int p, int q, int ptoq) {
    int r = n - m;
    List<Integer> ndigits = toBase(n, p);
    List<Integer> mdigits = toBase(m, p);
    List<Integer> rdigits = toBase(r, p);
    packZeros(ndigits, mdigits, rdigits);
    List<Integer> e = computeE(p, ndigits, mdigits, rdigits);
    List<Integer> nN_js = computeN_js(ndigits, p, q);
    List<Integer> mN_js = computeN_js(mdigits, p, q);
    List<Integer> rN_js = computeN_js(rdigits, p, q);
    int result = 1;
    for (int i = 0; i < e.get(0); i++) {
      result = (result * p) % ptoq;
    }
    for (int N_j : nN_js) {
      result = (result * modularFactorials.get(ptoq)[0][N_j]) % ptoq;
    }
    for (int N_j : mN_js) {
      result = (result * modularFactorials.get(ptoq)[1][N_j]) % ptoq;
    }
    for (int N_j : rN_js) {
      result = (result * modularFactorials.get(ptoq)[1][N_j]) % ptoq;
    }
    if (p != 2 || q < 3) {
      if (q <= e.size() && e.get(q - 1) % 2 != 0) {
        result = (-1 * result) % ptoq;
      }
    }
    return result >= 0 ? result : result + ptoq;
  }
  static class Reader {
    static BufferedReader  reader;
    static StringTokenizer tokenizer;
    static void init(InputStream input) {
      reader = new BufferedReader(new InputStreamReader(input));
      tokenizer = new StringTokenizer("");
    }
    
    static String next() throws IOException {
      while (!tokenizer.hasMoreTokens()) {
        tokenizer = new StringTokenizer(reader.readLine());
      }
      return tokenizer.nextToken();
    }
    
    static long nextLong() throws IOException {
      return Long.parseLong(next());
    }
    
    static int nextInt() throws IOException {
      return Integer.parseInt(next());
    }
    
    static double nextDouble() throws IOException {
      return Double.parseDouble(next());
    }
  }
  
  static class Writer {
    static BufferedWriter writer;
    
    static void init(OutputStream output) {
      writer = new BufferedWriter(new OutputStreamWriter(output));
    }
    
    static void writeInt(int n) throws IOException {
      writer.write("" + n + "\n");
    }
    
    static void writeLong(long n) throws IOException {
      writer.write("" + n + "\n");
    }
    
    static void writeDouble(double n) throws IOException {
      writer.write("" + n + "\n");
    }
    
    static void flush() throws IOException {
      writer.flush();
    }
  }
  

}
