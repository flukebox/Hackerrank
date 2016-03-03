package search;
import java.util.Scanner;

public class FindTheMedian {
  public static int findKth(int[] ar, int start, int end, int k){
    if(end-start<1) return 0;
    int pivot = ar[end-1], hi = -1;
    for(int i=start; i<end-1; i++){
      if(ar[i]<pivot){
        if(hi!=-1){
          swap(ar, hi, i);
          hi++;
        }
      }else{
        if(hi==-1) hi=i;
      }
    }
    if(hi!=-1){
      swap(ar, hi, end-1);
    }else{
      hi = end-1;
    }
    if(hi==k) return pivot;
    if(k<hi){
      return findKth(ar, start, hi, k);
    }else{
      return findKth(ar, hi+1, end, k);
    }
  }   
  
  public static void swap(int[] ar, int from, int to){
    int temp = ar[from];
    ar[from]=ar[to];
    ar[to]=temp;
  }


  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int[] ar = new int[n];
    for(int i=0;i<n;i++){
      ar[i]=sc.nextInt(); 
    }
    if((n&1)==1){
      int median = n/2;
      System.out.println(findKth(ar, 0, n, median));
    }else{
      int median = n/2;
      System.out.println((findKth(ar, 0, n, median-1)+findKth(ar, 0, n, median))/2.0);
    }
    sc.close();
  }
}
