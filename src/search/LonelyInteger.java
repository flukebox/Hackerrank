package search;

import java.util.Scanner;

public class LonelyInteger {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int xor = 0;
    for(int i=0; i<n; i++){
      int num = sc.nextInt();
      xor = num^xor;  
    }
    System.out.println(xor);
    sc.close();
  }
}
