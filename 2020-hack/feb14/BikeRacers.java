package feb14;
import java.util.Arrays;
import java.util.Scanner;

public class BikeRacers {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();
    int k = sc.nextInt();
    long[][] bikers = new long[n][2];
    long[][] bikes = new long[m][2];
    long[][] cost = new long[n][m];
    long[] socost = new long[n * m];
    for (int i = 0; i < n; i++) {
      bikers[i] = new long[]{sc.nextLong(), sc.nextLong()};
    }
    for (int j = 0; j < m; j++) {
      bikes[j] = new long[]{sc.nextLong(), sc.nextLong()};
      for (int i = 0; i < n; i++) {
        cost[i][j] = distance(bikes[j], bikers[i]);
        socost[i * m + j] = cost[i][j];
      }
    }
    Arrays.sort(socost);
    System.out.println(solve(cost, socost, n, m, k));
    sc.close();
  }
  
  static long distance(long[] p1, long[] p2) {
    return ((p1[0] - p2[0]) * (p1[0] - p2[0]))
        + ((p1[1] - p2[1]) * (p1[1] - p2[1]));
  }
  
  static boolean bpm(boolean edges[][], int u, boolean[] seen, int[] matchR,
      int n, int m) {
    for (int v = 0; v < m; v++) {
      if (edges[u][v] && !seen[v]) {
        seen[v] = true;
        if (matchR[v] < 0 || bpm(edges, matchR[v], seen, matchR, n, m)) {
          matchR[v] = u;
          return true;
        }
      }
    }
    return false;
  }
  
  static boolean maxBPM(boolean[][] edges, int n, int m, int k) {
    int[] matchR = new int[m];
    Arrays.fill(matchR, -1);
    int result = 0;
    for (int u = 0; u < n; u++) {
      boolean[] seen = new boolean[m];
      if (bpm(edges, u, seen, matchR, n, m)) result++;
    }
    return result >= k;
  }
  
  static long solve(long[][] cost, long[] socost, int n, int m, int k) {
    int left = 0, right = n * m;
    while (!(left == right)) {
      boolean[][] edges = new boolean[n][m];
      int mid = (right + left) / 2;
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          edges[i][j] = (cost[i][j] <= socost[mid]);
        }
      }
      if (maxBPM(edges, n, m, k)) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    return socost[left];
  }
}
