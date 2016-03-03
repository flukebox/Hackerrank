package discrete;
import java.util.Scanner;

public class Game {
  
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int numerbOfTestCase = sc.nextInt();
    while(numerbOfTestCase-->0){
      int num = sc.nextInt();
      int MASK = 1;
      int oneCount  = 0;
      int totalMove = 0;
      while(MASK<num && (MASK<<1) <= num){
        MASK = MASK << 1;
      }
      // MASK set to MSB
      while(MASK!=0){
        if((MASK&num)==0)  totalMove += oneCount;
        else oneCount++;
        MASK = MASK >> 1;
      }
      if(totalMove%2==1){
        System.out.println("First Player");
      }else{
        System.out.println("Second Player");
      }
    }
    sc.close();
  }
}
