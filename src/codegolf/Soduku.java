package codegolf;
import java.util.*;
import static java.lang.System.*;
class Sudoku {
  boolean   t  = true, f = !t;
  int[][][]   b  = new int[4][9][9];
  public static void main(String[] args) {
    Sudoku s = new Sudoku();
    Scanner sc = new Scanner(in);
    int tc = sc.nextInt();
    while (tc-- != 0) {
      int i = 0, j = 0, v;
      while (i < 9) {
        while (j < 9) {
          v = sc.nextInt();
          if (v != 0) s.st(i, j, v - 1);
          j++;
        }
        j = 0;
        i++;
      }
      s.ss(0, 0);
      i = j = 0;
      while (i < 9) {
        while (j < 9)
          out.print(s.b[0][i][j++] + " ");
        out.println();
        j = 0;
        i++;
      }
    }
    sc.close();
  }
  void st(int i, int j, int v) {
    b[0][i][j] = v + 1;
    b[1][i][v] = b[2][j][v] = b[3][(i/3)*3+j/3][v] = 1;
  }
  boolean ss(int i, int j) {
    if (i == 9) {
      i = 0;
      if (++j == 9) return t;
    }
    if (b[0][i][j] != 0) return ss(i + 1, j);
    for (int v = 0; v < 9; v++) {
      if ((b[1][i][v]|b[2][j][v]|b[3][(i/3)*3+j/3][v]) == 0) {
        st(i, j, v);
        if (ss(i + 1, j)) return t;
        b[0][i][j]=b[1][i][v] = b[2][j][v] = b[3][(i/3)*3+j/3][v] = 0;
      }
    }
    return f;
  }
}