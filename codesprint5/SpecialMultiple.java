import java.util.Scanner;

public class SpecialMultiple {
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int tc = sc.nextInt();
    long[] ans = new long[501];
    for(int j=1; j<501; j++){
      for(int i=1; ;i++){
        long numi = num(i);
        if(numi%j==0){
          ans[j]=numi;
          break;
        }
      }
    }
    while(tc-->0){
      System.out.println(ans[sc.nextInt()]);
    }
    sc.close();
  }

  static long num(int n){
    long num=0;
    int MASK = 1<<30;
    while(MASK>0){
      boolean last = (n&MASK)>0;
      MASK=MASK>>1;
      if(last){
        num=num*10+9;
      }else{
        num=num*10;
      }
    }
    return num;
  }
}




