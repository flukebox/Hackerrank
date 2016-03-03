package discrete;
import java.util.Scanner;

public class ConnectingTowns {
  public static void main(String[]   args){
    int prime = 1234567;
    Scanner sc = new Scanner(System.in);
    int testcases = sc.nextInt();
    for(int i=0; i<testcases; i++){
      int n = sc.nextInt();
      int total = 1;
      for(int j=1; j<n;j++){
        total = (total * sc.nextInt())%prime;
      }
      System.out.println(total);
    }
    sc.close();
  }
}
