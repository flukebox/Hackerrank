import java.util.Arrays;
import java.util.Scanner;

public class Hogwarts {
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int tc = sc.nextInt();
    while(tc-->0){
      int n = sc.nextInt();
      int k = sc.nextInt();
      int[] boys = new int[n];
      int[] girls = new int[n];
      Arrays.sort(boys);
      Arrays.sort(girls);
      int sum1 = 0;
      for(int i=0; i<n; i++){
        sum1+=boys[i]*girls[n-i-1];
      } 
      if(sum1<=k){
        System.out.println("YES");
      }else{
        System.out.println("NO");
      }
      
    }
    sc.close();
  }
}

