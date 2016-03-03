package feb14;
import java.util.Arrays;
import java.util.Scanner;
public class CuttingBoards {
  private static long M = 1000000007;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int tc = sc.nextInt();
    while (tc-- > 0) {
      int m = sc.nextInt();
      int n = sc.nextInt();
      long[] y = new long[m - 1];
      long[] x = new long[n - 1];
      sc.nextLine();
      String[] temp = sc.nextLine().split(" ");
      for (int i = 0; i < y.length; i++) {
        y[i] = Long.parseLong(temp[i]);
      }
      temp = sc.nextLine().split(" ");
      for (int i = 0; i < x.length; i++) {
        x[i] = Long.parseLong(temp[i]);
      }
      Arrays.sort(x);
      Arrays.sort(y);
      System.out.println(cost(m, n, y, x));
    }
    sc.close();
  }
  
  static long cost(int m, int n, long[] y, long[] x) {
    long cost = 0;
    long i = 1, j = 1;
    while (true) {
      if (y[(int) (m - j - 1)] == x[(int) (n - i - 1)]) {
        if (i >= j) {
          cost = (cost + y[(int) (m - j - 1)] * i) % M;
          j++;
        } else {
          cost = (cost + x[(int) (n - i - 1)] * j) % M;
          i++;
        }
      } else if (y[(int) (m - j - 1)] > x[(int) (n - i - 1)]) {
        cost = (cost + y[(int) (m - j - 1)] * i) % M;
        j++;
      } else {
        cost = (cost + x[(int) (n - i - 1)] * j) % M;
        i++;
      }
      if (i == n || j == m) break;
    }
    if (j == m) {
      for (; i < n; i++) {
        cost = (cost + x[(int) (n - i - 1)] * j) % M;
      }
    }
    if (i == n) {
      for (; j < m; j++) {
        cost = (cost + y[(int) (m - j - 1)] * i) % M;
      }
    }
    return cost;
  }
}