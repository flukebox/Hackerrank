package math;

import java.util.Scanner;

public class FindDigits {
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int tc = sc.nextInt();
    while(tc-->0){
      int n = sc.nextInt();
      String s = ""+n;
      int count = 0;
      for(char c:s.toCharArray()){
        int digit = Integer.parseInt(""+c);
        if((digit!=0)&&(n%digit==0)) count++;
      }
      System.out.println(count);
    }
    sc.close();
  }
}
