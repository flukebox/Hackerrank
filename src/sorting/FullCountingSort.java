package sorting;

import java.util.Scanner;  

public class FullCountingSort {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n  = sc.nextInt();
    int[] count = new int[100];
    StringBuilder [] strings = new StringBuilder[100];
    
    for(int i=0; i<n; i++){
      int index = sc.nextInt();
      String str = sc.next();
      if(i<n/2) str = "-";
      if(strings[index]==null){
        strings[index] = new StringBuilder();
      }
      strings[index].append(str+" ");
      count[index]++;
    }
    
    for(int i=0; i<100; i++){
      if(strings[i]!=null){
        System.out.print(strings[i]);
      }
    }
    sc.close();
  }
}
