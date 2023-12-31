import java.util.Scanner;


public class Clique {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int tc = sc.nextInt();
    while(tc--!=0){
      int n = sc.nextInt();
      int m = sc.nextInt();
      System.out.println(clique(n,m));
    }
    sc.close();
  }
  
  
  public static int clique(int n, int m){
    int k,low = 1,high = n + 1 ;
    while(low + 1 < high){
      int mid = low + (high - low)/2 ;
      k = solve1(n,mid) ;
      if(k < m) low = mid ;
      else high = mid ;
    }
    return high ;
  }
  
  public static int solve1(int n,int k){
    int g1 = n%k ; int g2 = k - g1 ;
    int sz1 = n/k + 1; int sz2 = n/k ;
    return  (g1*sz1*g2*sz2 + g1*(g1-1)*sz1*sz1/2 + g2*(g2-1)*sz2*sz2/2) ;
  }
  
}
