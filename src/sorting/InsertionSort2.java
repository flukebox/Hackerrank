package sorting;

import java.util.Scanner;  

public class InsertionSort2 {
  public static void insertionSort(int[] ar){
    for(int i=1; i<ar.length; i++){
      int temp = ar[i];
      for(int j=i-1; j>=0; j--){
        if(ar[j]>temp){
          ar[j+1]=ar[j];
          if(j==0){
            ar[j]=temp;
          }
        }else{
          ar[j+1]=temp;
          break;
        }
      }
      printArray(ar);
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