package sorting;

import java.util.Scanner;

public class RunningTime {
  public static int insertionSort(int[] ar){
    int count = 0;
    for(int i=1; i<ar.length; i++){
      for(int j=i-1; j>=0; j--){
        if(ar[j]>ar[i]){
          count++;
        }
      }
    }   
    return count;
  }
  
  
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int[] ar = new int[n];
    for(int i=0;i<n;i++){
      ar[i]=in.nextInt(); 
    }
    System.out.println(insertionSort(ar));
    in.close();
  }    
}