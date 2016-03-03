package sorting;

import java.util.Scanner;  

public class CountingSort2 {
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n  = sc.nextInt();
    int[] count = new int[100];
    for(int i=0; i<n; i++){
      count[sc.nextInt()]++;
    }
    for(int i=0; i<100; i++){
      while(count[i]-->0){
        System.out.print(i+" ");
      }
    }
    sc.close();
  }
}