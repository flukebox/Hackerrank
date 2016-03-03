package dp;
import java.util.Arrays;
import java.util.Scanner;

public class Candies {
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int[] rating = new int[n];
    for(int i=0; i<n; i++){
      rating[i]=sc.nextInt();
    }
   System.out.println(calculateCandies(rating, n));
   sc.close();
  }
  
  
  public static long calculateCandies(int[] rating, int n){
    int totalSum = 0;
    int[][] candies = new int[3][n];
    Arrays.fill(candies[0], 1);
    Arrays.fill(candies[1], 1);
    for(int i=1; i<n; i++){
      if(rating[i]>rating[i-1]){
        candies[0][i]=candies[0][i-1]+1;
      }
      if(rating[n-i]<rating[n-i-1]){
        candies[1][n-i-1]=candies[1][n-i]+1;
      }
    }
    for(int i=0; i<n; i++){
      candies[2][i]=Math.max(candies[0][i], candies[1][i]);
      totalSum += candies[2][i];
    }
    return totalSum;
  }
}