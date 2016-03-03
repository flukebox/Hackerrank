package sorting;

import java.util.Scanner;  

public class CountingSort1 {
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int [] count = new int[100];
    int n = sc.nextInt();
    for(int i=0; i<n; i++){
        count[sc.nextInt()]++;
    }
    for(int i=0; i<100; i++){
        System.out.print(count[i]+" ");
    }
    
    sc.close();
  }
}