package mar14;
import java.util.Scanner;

public class IndianJob {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int tc = sc.nextInt();
    while(tc-->0){
      int n = sc.nextInt();
      int g = sc.nextInt();
      int[] A = new int[n];
      int sum = 0;
      for(int i=0; i<n; i++){
        A[i]=sc.nextInt();
        sum+=A[i];
      }
      if(sum/2>g){
        System.out.println("NO");
      }else{
        solve(A, sum, g);
      }
    }
    sc.close();
  }
  
  private static void solve(int[] array, int sum, int g){
    boolean[] sol = new boolean[sum/2+1];
    sol[0] = true;
    for(int i:array){
      for(int j=sum/2; j>=i; j--){
        if(sol[j-i]) sol[j]=true;
      }
    }
    int halfsumcloser = sum/2;
    for(; !sol[halfsumcloser]; halfsumcloser--);
    if(halfsumcloser<=g && sum-halfsumcloser<=g){
      System.out.println("YES");
    }else{
      System.out.println("NO");
    }
  }
  
}
