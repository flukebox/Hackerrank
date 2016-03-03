import java.util.Scanner;
import java.util.Set;


public class StringTransmission {
  public static final int M = 1000000007;
  
  public static void main(String[] args) {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    Scanner sc = new Scanner(System.in);
    int numOfTestCase = sc.nextInt();
    for(int i=0; i<numOfTestCase; i++){
      int n = sc.nextInt();
      int k = sc.nextInt();
      int totalCase = totalCases(n,k);
      String bitString = sc.next();
      totalCase-=periodiCases(bitString, k);
      System.out.println(totalCase);
    }
    sc.close();
  }
  
  
  static int totalCases(int n, int k){
    long cases = 1;
    long temp = 1;
    for(int i=1; i<=k; i++){
      temp = ((temp*(n-i+1))/i)%M;
      cases = (cases+temp)%M;      
    }
    return (int) cases;
  }
  
  static boolean divisibleBy(Set<Integer> divider, int k){
    for(int i:divider){
      if((k%i)==0) return true;
    }
    return false;
  }
  
  static int periodiCases(String bitString, int k){
    int count = 0;
    int n = bitString.length();
    for(int i=2; i<=bitString.length()/2; i++){
      if(n%i!=0) continue;
      String b1 = bitString.substring(0, i);
      String b2 = bitString.substring(i, 2*n/i);
      if(periodicInKFlips(b1, b2, k)){
        
      }
    }
    return count;
  }
  
  static boolean periodicInKFlips(String b1, String b2, int k){
    for(int i=0; i<b1.length(); i++){
      if(!(b1.charAt(i)==b2.charAt(i)) && k>0) k--;
    }
    return (k>=0)?true:false;
  }
}
