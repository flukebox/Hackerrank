package utilities;

public class Utilities {
  private static int M = 1000003;
  private static int[] facts = new int[M];

  /** mod power in log(n) **/
  static int modPower(int base, int pow){
    long t = 1;
    long p = base;
    while(pow >= 1) {
      if((pow & 1) == 1) t = (t * p) % M;
      p = (p * p) % M;
      pow >>>= 1;
    }
    return (int) t;
  }
  
  /** mod factorial all in O(m) **/
  static {
    facts[0]=1;
    long temp = 1;
    for(int i=1; i<M; i++){
      temp = (temp*i)%M;
      facts[i] = (int)temp;
    }
  }


  
  
  public Utilities() {
  }
  
  
  

  
}
