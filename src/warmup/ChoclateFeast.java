package warmup;

import java.util.Scanner;

public class ChoclateFeast {
    public static void main(String[] args){
      Scanner sc = new Scanner(System.in);
      int tc = sc.nextInt();
      while(tc-->0){
        int n = sc.nextInt();
        int c = sc.nextInt();
        int m = sc.nextInt();
        int count = n/c, w=count;
        while(w>=m){
          count += (w/m); w = (w/m)+(w%m);
        }
          System.out.println(count);
      }
      sc.close();
    }  
  }