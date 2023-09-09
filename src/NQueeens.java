import java.util.Scanner;

public class NQueeens {
  int SIZE = 8;
  int[] b = new int[SIZE];
  int s = 0;

  public NQueeens(int size){
    SIZE = size;
    b = new int[SIZE];
    s = 0;
  }

  boolean unsafe(int y) {
    int x = b[y];
    for (int i = 1; i <= y; i++) {
      int t = b[y - i];
      if (t == x ||
          t == x - i ||
          t == x + i) {
        return true;
      }
    }
    return false;
  }
  
  void putboard() {
    ++s;
    /**
    System.out.println("\n\nSolution " + (++s));
    for (int y = 0; y < SIZE; y++) {
      for (int x = 0; x < SIZE; x++) {
        System.out.print((b[y] == x) ? "|Q" : "|_");
      }
      System.out.println("|");
    }**/
  }

  void solve(){
    int y = 0;
    b[0] = -1;
    while (y >= 0) {
      do {
        b[y]++;
      } while ((b[y] < SIZE) && unsafe(y));
      if (b[y] < SIZE) {
        if (y < SIZE-1) {
          b[++y] = -1;
        } else {
          putboard();
        }
      } else {
        y--;
      }
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    for(int i=1; i<=n ; i++){
      long t1 = System.currentTimeMillis();
      NQueeens nq = new NQueeens(i);
      nq.solve();
      long t2 = System.currentTimeMillis();
      System.out.println("("+i+") Number of solutions are  "+nq.s+", timetaken="+(t2-t1)+" milliseconds");
    }
  }
}