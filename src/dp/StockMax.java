package dp;
import java.util.*;

public class StockMax {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int numOfTestCase = sc.nextInt();
    for(int i=0; i<numOfTestCase; i++){
      int n = sc.nextInt();
      long profit = 0;
      int[] stockPrice = new int [n];
      
      for(int j=0; j<n; j++){
        stockPrice[j]=sc.nextInt();
      }
      int currMax = Integer.MIN_VALUE;
      for(int j=n-1; j>=0; j--){
        if(currMax<stockPrice[j]){
          currMax = stockPrice[j];
        }
        profit+=(currMax-stockPrice[j]);
      }
      System.out.println(profit);
    }
    sc.close();
  }
}