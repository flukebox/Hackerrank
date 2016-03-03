import java.util.Scanner;

public class HyperrectangleGcd {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int tc = sc.nextInt();
    while (tc-- > 0) {
      int n = sc.nextInt();
      int[] ns = new int[n];
      for(int i=0; i<n; i++){
        ns[i]=sc.nextInt();
      }
      for(int i1=0; i1<ns[0]; i1++){
        for(int i2=0; i2<ns[1]; i2++){
          System.out.print(gcd(i1+1, i2+1)+" ");
        }
        System.out.println();
      }
    }
    sc.close();
  }
  
  static int gcd(int a,int b){
    while(b > 0){
      int c = a % b;
      a = b;
      b = c;
    }
    return a;
  }
}
