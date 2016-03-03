package sorting;

import java.util.Arrays;
import java.util.Scanner;  

public class TwoArrays {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int tc = sc.nextInt();
    while(tc-->0){
      int n = sc.nextInt();
      int k = sc.nextInt();
      int [] as = new int[n];
      int [] bs = new int[n];
      for(int i=0; i<n; i++){
        as[i]=sc.nextInt();
      }
      for(int i=0; i<n; i++){
        bs[i]=sc.nextInt();
      }
      Arrays.sort(as);
      Arrays.sort(bs);
      
      boolean possible = true;
      for(int i=0; i<n; i++){
        if(as[i]+bs[n-1-i]>=k) continue;
        else {
          possible = false;
        }
      }
      System.out.println(possible?"YES":"NO");
    }
    sc.close();
  }  
}