package warmup;

import java.util.Arrays;
import java.util.Scanner;

public class AngryChildren {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int k = sc.nextInt();
    int[] packets = new int[n];
    for(int i=0; i<n; i++){
      packets[i]=sc.nextInt();        
    }
    Arrays.sort(packets);
    int min = Integer.MAX_VALUE;
    for(int i=k-1; i<n; i++){
      if((packets[i]-packets[i-k+1])<min){
        min = (packets[i]-packets[i-k+1]);
      }
    }
    System.out.println(min);
    sc.close();
  }
}