package discrete;
/**
 * http://resources.infosecinstitute.com/random-number-generation-java/
 * @author jai.singh
 *seed updated to 
seed=(seed*0x5DEECE66DL+0xBL)&((1L<<48)???1)
rn=(int)(seed >>> (48 ??? bits)).
 */
import java.util.Random;
import java.util.Scanner;
public class PRNGGuessing{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int tc = sc.nextInt();
    while(tc-->0){
      long t1 = sc.nextLong();
      long t2 = sc.nextLong();
      int[] rns = new int[10];
      for(int i=0; i<10; i++){
        rns[i]=sc.nextInt();
      }
      solve(t1,t2, rns);
    }
    sc.close();
  }
  
  static boolean match(int[]rns, int[] nums, int offset){
    for(int i=0;i<10; i++){
      if(rns[i]!=nums[(offset+i)%10]) return false;
    }
    return true;
  }
  
  static void solve(long t1, long t2, int[] rns){
    for(long t=t1; t<=t2; t++){
      Random random = new Random(t);
      int[] nums = new int[10];
      for(int i=0;i<10;i++){
        nums[i]=random.nextInt(1000);
      }
      if(match(rns, nums, 0)){
        System.out.print(t+" ");
        for(int j=0; j<10; j++){
          System.out.print(random.nextInt(1000)+" ");
        }
        System.out.println();
        return;
      }
    }
  }
}