package week3;
import java.util.Scanner;

class JDay5{
  private static int M = 1000000007;
  private static int N = 15000000;
  private static boolean[] bprimes = new boolean[N+1];

  public static void main(String[] args){
	  generatePrimes();
	  Scanner sc = new Scanner(System.in);
	  System.out.println(solve2(sc.nextInt(),sc.nextInt()));
	  sc.close();
	}
	
	private static long solve2(long n, long m){
    long mn = Math.min(n,m);
    long ans = 1;
    for(int p=2; p<=mn; p++){
      if(bprimes[p]){
        long pow = 0;
        long cp = p;
        while((n/cp)*(m/cp)>0){
          pow += (n/cp)*(m/cp);
          cp *=p;
        }
        ans = (ans*modPower(p, pow))%M;
      }
    }
    return ans;
  }    

  private static long modPower(long base, long pow){
    long t = 1;
    long p = base%(M-1);
    while(pow >= 1) {
      if((pow & 1) == 1) t = (t * p) % M;
      p = (p * p) % M;
      pow >>>= 1;
    }
    return t;
  }

  public static void generatePrimes() {
    java.util.Arrays.fill(bprimes, true);
    bprimes[0] = bprimes[1] = false;
    for (int i = 2; i < bprimes.length; ++i){
      if (bprimes[i])
        for (int j = i + i; j < bprimes.length; j += i){
          bprimes[j] = false;
        }
    }
  }
}
