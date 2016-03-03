import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class LuckyNumber {
  private final static int NUM_OF_DIGITS = 20; 
  private static ArrayList<Integer> primes = new ArrayList<Integer>();
  
  public static void main(String[] args){
    buildPrimeNumbers();
    System.out.println(findDigits(18, 5, 10000, 20999));
  }
  
  
  // 1 2 0   1 2 [ 0 -9 ]
  // 13, 11, 7, 5, 3, 2
  // 1 2 9 -->> lucky NO
  // 1 2 8 -->> lucky NO
  
  /**
   *   Soln(n, d) = {
   *   Soln(n, d-1) + S(0,1)
   *   Soln(n-1, d-1)+S(1,1)
   *   Soln(n-2, d-1)+S(2,1)           
   *   Soln(n-3, d-1)+S(3,1)
   *   Soln(n-4, d-1)+S(4,1)
   *   Soln(n-5, d-1)+S(5,1)
   *   Soln(n-6, d-1)+S(6,1)
   *   Soln(n-7, d-1)+S(7,1)
   *   Soln(n-8, d-1)+S(8,1)
   *   Soln(n-9, d-1)+S(9,1)
   * 
   *  key = n:c 
   */
  
  
  public static int findDigits(int n, int numDigits, long r1, long r2){
    int[] baseCase = { 0,1,2,3,4,5,6,7,8,9};
    Set<ArrayList<Integer>>[] oldValues = new Set[n+1];
    for(int i=0; i<=n; i++){
      Set<ArrayList<Integer>> set = new HashSet<ArrayList<Integer>>();
      if( (i>=1) && (i<10)){
        ArrayList<Integer> temp = new ArrayList<Integer>();
        temp.add(baseCase[i]);
        set.add(temp);
      }
      oldValues[i]=set;
    }
    
    for(int j=2; j<=numDigits; j++){
      Set<ArrayList<Integer>>[] newValues = new Set[n+1];
      for(int i=0; i<=n; i++){
        Set<ArrayList<Integer>> set = new HashSet<ArrayList<Integer>>();
        for(int k=0; (k<10) && (i-k>=0); k++){
          System.out.println("i="+i+", j="+j+", k="+k+" n="+n+", numDigits="+numDigits);
          Set<ArrayList<Integer>> s1 = oldValues[i-k];
          if(!s1.isEmpty()){
            for(ArrayList<Integer> prefix:s1){
              ArrayList<Integer> temp = new ArrayList<Integer>();
              temp.addAll(prefix);
              temp.add(baseCase[k]);
              for(int digit:temp){
                System.out.print(digit);
              }
              System.out.print("\t");
              System.out.println();
            }
          }          
        }
        newValues[i]=set;
      }
      oldValues= newValues;
    }
    
    int count = 0;
    for(int i=0; i<=n; i++){
      System.out.println("Sum equals to "+i +" digits = "+numDigits);
      System.out.println();
      for(ArrayList<Integer> digits:oldValues[i]){
        for(int digit:digits){
          System.out.print(digit);
        }
        System.out.print("\t");
      }
    }
    System.out.println();
    return count;
  }
  
  public boolean checkLucky(int[] digits){
    int sum = 0;
    for(int i=0; i<digits.length; i++){
      sum += digits[i]*digits[i];
    }
    return prime(sum);
  }
  
  public static void buildPrimeNumbers(){
    for(int i=2; i<NUM_OF_DIGITS*81; i++){
      if(prime(i)){
        primes.add(i);
      }
    }
  }
  
  public static boolean prime(int n){
    if(n<2) return false;
    int sqrtn = (int) Math.sqrt(n);
    for(int i=2; i<=sqrtn; i++){
      if(n/i>=1 && n%i==0) return false;
    }
    return true;
  }
}



