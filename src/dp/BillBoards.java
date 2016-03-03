package dp;
import java.util.LinkedList;
import java.util.Scanner;
public class BillBoards {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int k = sc.nextInt();
    int[] profit = new int[n+2];
    long netSum = 0;
    for(int i=1; i<=n; i++){
      profit[i]=sc.nextInt();
      netSum +=profit[i];
    }
    System.out.println(solve(profit, netSum, n, k));
   sc.close();
  }

  static long solve(int[] profit, long netSum, int n, int k){
    LinkedList<Integer> queue = new LinkedList<Integer>();
    long[] net = new long[n+2];
    for(int i=1; i<=k+1; i++){
      net[i]=profit[i];
      while(!queue.isEmpty() && net[i] <= net[queue.getLast()]){
       queue.removeLast(); 
      }
      queue.addLast(i);
    }
    
    for(int i=k+2; i<=n; i++){
      net[i]=net[queue.getFirst()]+profit[i];
      while(!queue.isEmpty() && net[i] <= net[queue.getLast()]){
        queue.removeLast(); 
      }
      while(!queue.isEmpty() && queue.getFirst()<=i-k-1){
        queue.removeFirst(); 
      }
      queue.addLast(i);
    }
    net[n+1]=net[queue.getFirst()];
    return netSum-net[n+1];
  }
}
