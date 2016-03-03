package sorting;

import java.util.Scanner;  

public class InsertionSort1 {
  
  static void insertionSort(int[] ar) {
    int temp = ar[ar.length-1];
    for(int i=ar.length-2; i>=0; i--){
      if(ar[i]>temp){
        ar[i+1]=ar[i];
        printArray(ar);
        if(i==0){
          ar[i]=temp;
          printArray(ar);
        }
      }else{
        ar[i+1]=temp;
        printArray(ar);
        break;
      }
    }
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
    insertionSort(ar);
    in.close();
  }    
}
