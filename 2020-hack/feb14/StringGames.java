package feb14;
import java.util.Scanner;

public class StringGames {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();
    int k = sc.nextInt();
    String A = sc.next();
    String B = sc.next();
    solve(A, B, n, m, k);
    sc.close();
  }
  
  static void allSubsInLexicalOrder(String A) {
    char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    System.out.println("chars.lenght=" + chars.length);
  }
  static void solve(String A, String B, int n, int m, int k) {
  }
  
}