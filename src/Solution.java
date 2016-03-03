import java.util.Scanner;

public class Solution {
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int tc = sc.nextInt();
    while(tc-->0){
      int m = sc.nextInt();
      int n = sc.nextInt();
      int[] costs = new int[10001];
      for(int i=1; i<=n; i++){
        int c = sc.nextInt();
        if((m-c)>0 && costs[m-c]!=0){
          System.out.println(costs[m-c]+" "+i);
        }else{
          costs[c]=i;          
        }
      }
    }
    sc.close();
  }
}