package discrete;
import java.util.Scanner;

public class MinimumDraw {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int testcases = sc.nextInt();
    for(int i=0; i<testcases; i++){
      int n = sc.nextInt();
      System.out.println(n+1);
    }
    sc.close();
  }
}
