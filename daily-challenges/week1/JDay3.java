package week1;


import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class JDay3 {
  private static final int M = 1000000007;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int p = sc.nextInt();
    TreeMap<Integer, Integer> brackets = new TreeMap<Integer, Integer>();

    for(int i = 1; i<= Math.sqrt(p); i++){
      brackets.put(i, 1);
      brackets.put(p/i, (p/i - (int)Math.floor((p/(i+1.0))+1) +1));
    }

    int size = brackets.size();
    int[] base = new int[size];
    long[] current = new long[size];
    long[] old = new long[size];
        
    int index = 0;
    for(Entry<Integer, Integer> entry:brackets.entrySet()){
      base[index] = entry.getValue();
      old[index++] = entry.getValue();
    }
    
    long sum = 0, total = 0;
    for(int i=2; i<=n; i++){
      for( int j=1; j<=size; j++){
        current[size-j] = old[j-1];
      }
      total = 0; sum = 0;
      for( int j=1; j<=size; j++){
        total = (total + current[size-j])%M;
        current[size-j] = ( total * base[size-j])%M;
        sum = (sum +current[size-j])%M;
      }
      long[] temp = old; old = current; current = temp;
    }
    
    System.out.println(sum);
    sc.close();
  }
  
}
