package sorting;

import java.util.Arrays;
import java.util.Scanner;  

public class MarkAndToys {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int k = sc.nextInt();
    int[] p = new int[n];
    for(int i=0; i<n; i++){
      p[i] = sc.nextInt();
    }
    int currSum = 0;
    int totalCount = 0;
    Arrays.sort(p); 
    for(int i=0; i<n; i++){
      if(p[i]+currSum<=k){
        currSum += p[i];
        totalCount +=1;
      }else{
        break;
      }
      
    }   
    System.out.println(totalCount);
    sc.close();
  }
}