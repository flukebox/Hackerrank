package sorting;

import java.util.Scanner;  

public class QuickSort1 {
  static void partition(int[] ar) {
    int pivot = ar[0], lc = 0, hc = 0;
    int[] low = new int[ar.length-1];
    int[] high = new int[ar.length-1];
    for(int i=1; i<ar.length; i++){
      if(ar[i]<=pivot){
        low[lc++]=ar[i];
      }else{
        high[hc++]=ar[i];
      }
    }
    System.arraycopy(low, 0, ar, 0, lc);
    ar[lc]=pivot;
    System.arraycopy(high, 0, ar, lc+1, hc);
    printArray(ar);
  }   
  
  /* Tail starts here */
  static void printArray(int[] ar) {
    for(int n: ar){
      System.out.print(n+" ");
    }
    System.out.println("");
  }
  
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int[] ar = new int[n];
    for(int i=0;i<n;i++){
      ar[i]=in.nextInt(); 
    }
    partition(ar);
    in.close();
  }    
}