package sorting;

import java.util.Scanner;  

public class InsertionSortAdvancedAnalysis {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int tc = sc.nextInt();
    int N = 1000001;
    while(tc-->0){
      int n = sc.nextInt();
      int[] ar = new int[n];
      int[] aux = new int[N];
      int total = 0;
      long count = 0;
      for(int i=0;i<n;i++){
        ar[i]=sc.nextInt(); 
        count+=(total-getBIT(aux, ar[i]));
        updateBIT(aux, ar[i],1);
        total++;
      }
      System.out.println(count);
    }
    sc.close();
  }    
  
  public static void updateBIT(int[] aux, int index, int value){
    int k = index;
    while(k<=aux.length-1){
      aux[k]+=value;
      k +=(k&-k);
    }
  }
  
  public static long getBIT(int[] aux, int index){
    int k = index;
    long ret=0;
    while(k>=1){
      ret+=aux[k];
      k -=(k&-k);
    }
    return ret;
  }
}
