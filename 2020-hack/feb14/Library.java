package feb14;
import java.util.*;
public class Library {
  private static int[]     books    = new int[10001];
  private static int       MAX_BOOK = 1001;
  private static short[][] aux      = new short[40000][];
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int tc = sc.nextInt();
    while (tc-- > 0) {
      int n = sc.nextInt();
      for (int i = 0; i < n; i++) {
        books[i] = sc.nextInt();
      }
      buildRMQ(1, n, 1);
      int q = sc.nextInt();
      for (int i = 0; i < q; i++) {
        switch (sc.nextInt()) {
          case 0 : {
            int from = sc.nextInt();
            int to = sc.nextInt();
            short[] result = new short[MAX_BOOK];
            queryRMQ(1, n, 1, from, to, result);
            int k = sc.nextInt();
            int rank = 0;
            for (int j = 0; j < MAX_BOOK; j++) {
              rank += result[j];
              if (k <= rank) {
                System.out.println(j);
                break;
              }
            }
            break;
          }
          case 1 : {
            int index = sc.nextInt();
            int value = sc.nextInt();
            updateRMQ(1, n, 1, index, books[index - 1], value);
            books[index - 1] = value;
          }
        }
        
      }
    }
    sc.close();
  }
  
  static void printArray(short[] array) {
    for (int i = 0; i < array.length; i++) {
      System.out.print(i + ":" + array[i] + "\t");
    }
    System.out.println();
  }
  
  static void buildRMQ(int start, int end, int index) {
    aux[index] = new short[MAX_BOOK];
    if (start == end) {
      aux[index][books[start - 1]]++;
    } else {
      int mid = (start + end) >> 1;
      int left = index << 1;
      int right = left | 1;
      buildRMQ(start, mid, left);
      buildRMQ(mid + 1, end, right);
      for (int i = 0; i < MAX_BOOK; i++) {
        aux[index][i] = (short) (aux[left][i] + aux[right][i]);
      }
    }
  }
  
  static void updateRMQ(int start, int end, int index, int idx, int pvalue,
      int value) {
    if (start == end && start == idx) {
      aux[index][pvalue]--;
      aux[index][value]++;
    } else if (start <= idx && idx <= end) {
      int mid = (start + end) >> 1;
      int left = index << 1;
      int right = left | 1;
      updateRMQ(start, mid, left, idx, pvalue, value);
      updateRMQ(mid + 1, end, right, idx, pvalue, value);
      aux[index][pvalue]--;
      aux[index][value]++;
    }
  }
  
  static void queryRMQ(int start, int end, int index, int qstart, int qend,
      short[] result) {
    if (end < qstart || qend < start) {
    } else if (qstart <= start && end <= qend) {
      for (int i = 1; i < MAX_BOOK; i++) {
        result[i] += aux[index][i];
      }
    } else {
      int mid = (start + end) >> 1;
      int left = index << 1;
      int right = left | 1;
      queryRMQ(start, mid, left, qstart, qend, result);
      queryRMQ(mid + 1, end, right, qstart, qend, result);
    }
  }
}
