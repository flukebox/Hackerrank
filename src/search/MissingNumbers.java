package search;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;

public class MissingNumbers {  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    Hashtable<Integer, Integer> A = new Hashtable<Integer, Integer>();
    for(int i=0; i<n; i++){
      int a = sc.nextInt();
      if(A.containsKey(a)){
        A.put(a, A.get(a)+1);
      }else{
        A.put(a, 1);
      }
    }
    int m = sc.nextInt();
    for(int i=0; i<m; i++){
      int a = sc.nextInt();
      if(A.containsKey(a)){
        A.put(a, A.get(a)-1);
      }else{
        A.put(a, -1);
      }
    }
    
    Integer[] keys = A.keySet().toArray(new Integer[0]);
    Arrays.sort(keys);
    for(int i=0; i<keys.length; i++){
      if(A.get(keys[i])<0){
        System.out.print(keys[i]+" ");
      }
    }
    sc.close();
  }
}
