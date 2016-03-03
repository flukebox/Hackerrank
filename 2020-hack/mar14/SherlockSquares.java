package mar14;
import java.util.Scanner;

public class SherlockSquares {
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int tc = sc.nextInt();
    while(tc-->0){
      int a = sc.nextInt();
      int b = sc.nextInt();
      int sa  = (int) Math.ceil(Math.sqrt(a));
      int sb  = (int) Math.floor(Math.sqrt(b));
      System.out.println(sb-sa+1);
    }
    sc.close();
  }
}
