import java.util.Scanner;

public class Range {
  public static void main(String[] args) {
    // scanner to read input from stdin
    Scanner sc = new Scanner(System.in);
    // total number of test cases
    int tc = sc.nextInt();
    int min = sc.nextInt();
    int max = sc.nextInt();
    for(int i=0; i<tc; i++){
        int curr = sc.nextInt();
        if( min <= curr && curr  <= max ){
            System.out.print(curr);
            System.out.print(" ");
        }
    }
    System.out.println();
    sc.close();
  }
}
