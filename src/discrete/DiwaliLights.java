package discrete;
import java.util.Scanner;


public class DiwaliLights{
  
  public static void main(String[] args) {
    int prime = 100000;
    Scanner sc = new Scanner(System.in);
    int tc = sc.nextInt();
    for(int i=0; i<tc; i++){
      int n = sc.nextInt();
      System.out.println(answer(n, prime));
    }
    sc.close();
  }

  public static int answer(int n, int prime){
    int temp = modPower(2, n, prime)-1;
    if(temp<0) temp = temp+prime;
    return temp;
  }
  
  
  public static int modPower(int a, int b, int m){
    long temp = 1;
    if(b==0) return 1;
    temp = modPower(a, b/2, m);
    temp = (temp*temp)%m;
    if(b%2==0){
      return (int) temp;
    }else{
      return (int) ((a*temp)%m);
    }
  }
}
