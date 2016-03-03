package discrete;
import java.util.Scanner;

public class Handshake {
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int tc = sc.nextInt();
    for(int i=0; i<tc; i++){
      long n = sc.nextInt();
      System.out.println((n*(n-1))>>1);
    }
    sc.close();
  }
}
