package math;

import java.util.Scanner;

public class FindPoint {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int tc = sc.nextInt();
    while(tc-->0){
      int[] p = {sc.nextInt(), sc.nextInt()};
      int[] q = {sc.nextInt(), sc.nextInt()};
      int[] s = {2*q[0]-p[0], 2*q[1]-p[1]};
      System.out.println(s[0]+" "+s[1]);
    }
    sc.close();
  }
}
