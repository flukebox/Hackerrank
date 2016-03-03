package sorting;

import java.util.Arrays;
import java.util.Scanner;  

public class RunningTimeQuickSort {
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int[] ar = new int[n];
    int[] br = new int[n];
    for(int i=0;i<n;i++){
      ar[i]=sc.nextInt(); 
    }
    int[] ar_=Arrays.copyOf(ar, n);
    long insertionShift = bottomUpSort(n, ar, br);
    long quickSwap = quickSort(ar_, 0, n);
    System.out.println((insertionShift-quickSwap));
    sc.close();
  }    
  public static int quickSort(int[] ar, int start, int end){
    if(end-start<=1) return 0;
    int pivot = ar[end-1], count=0, hi = -1;
    for(int i=start; i<end-1; i++){
      if(ar[i]<pivot){
        if(hi!=-1){
          swap(ar, hi, i);
          hi++;
        }
        count++;
      }else{
        if(hi==-1) hi=i;
      }
    }
    if(hi!=-1){
      swap(ar, hi, end-1);
    }
    count++;
    if(hi!=-1){
      if(start<hi-1) count+=quickSort(ar, start, hi);
      if(hi<end-1) count+=quickSort(ar, hi+1, end);
    }else{
      count+=quickSort(ar, start, end-1);
    }
    return count;
  }   
  
  public static void swap(int[] ar, int from, int to){
    int temp = ar[from];
    ar[from]=ar[to];
    ar[to]=temp;
  }
  
  public static void printArray(int[] ar){
    System.out.println();
    for(int i=0; i<ar.length; i++){
      System.out.print(ar[i]+" ");
    }
    System.out.println();
  }
  
  public static void quickSortNIP(int[] ar){
    int pivot = ar[ar.length-1], lc = 0, hc = 0;
    int[] low = new int[ar.length-1];
    int[] high = new int[ar.length-1];
    for(int i=0; i<ar.length-1; i++){
      if(ar[i]<=pivot){
        low[lc++]=ar[i];
      }else{
        high[hc++]=ar[i];
      }
    }
    if(lc>1){
      low = Arrays.copyOf(low, lc);
      quickSortNIP(low);
    } 
    if(hc>1) {
      high = Arrays.copyOf(high, hc);
      quickSortNIP(high);
    }
    System.arraycopy(low, 0, ar, 0, lc);
    ar[lc]=pivot;
    System.arraycopy(high, 0, ar, lc+1, hc);
  }   
  
  /********************************* Merge Sort ****************************************************/
  /* array A[] has the items to sort; array B[] is a work array */
  public static long bottomUpSort(int n, int A[], int B[]){
    long count = 0;
    for (int w=1; w < n; w*=2){
      for (int i = 0; i < n; i+=2*w){
        count += bottomUpMerge(A, i, Math.min(i+w, n), Math.min(i+2*w, n), B);
      }
      System.arraycopy(B, 0, A, 0, n);
    }
    return count;
  }
  
  public static long bottomUpMerge(int A[], int l, int r, int e, int B[]){
    long count = 0;
    for (int j=l, i0=l, i1=r; j<e; j++){
      if (i0 < r && (i1 >= e || A[i0] <= A[i1])){ B[j] = A[i0++];}
      else{ count+=(r-i0); B[j] = A[i1++];}
    }
    return count;
  }
  /********************************* Merge Sort ****************************************************/
}