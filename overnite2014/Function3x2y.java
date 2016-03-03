import java.util.Scanner;

/**
f(0,0) = 0
f(3x,2y) = f(x,y)
f(3x+1,2y) = f(x,y)+1
f(3x+2,2y) = f(x,y)
f(3x,2y+1) = f(x,y)+1
f(3x+1,2y+1) = f(x,y)+2
f(3x+2,2y+1) = f(x,y)+1
 */
public class Function3x2y {
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int tc = sc.nextInt();
    while(tc-->0){
      int x = sc.nextInt();
      int y = sc.nextInt();
      System.out.println(solve(x,y));
    }
    sc.close();
  }
  
  
  static long solve(long x, long y){
    long sol = 0;
    if(x==0 && y==0){
      return 0;
    }else{
      long x_ = x/3; long x_mod = x%3;
      long y_ = y/2; long y_mod = y%2;
      if(x_mod==0 && y_mod==0) return solve(x_, y_);
      if(x_mod==1 && y_mod==0) return solve(x_, y_)+1;
      if(x_mod==2 && y_mod==0) return solve(x_, y_);
      if(x_mod==0 && y_mod==1) return solve(x_, y_)+1;
      if(x_mod==1 && y_mod==1) return solve(x_, y_)+2;
      if(x_mod==2 && y_mod==1) return solve(x_, y_)+1;
    }
    return sol;
  }
}
