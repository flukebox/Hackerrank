package week3;

import java.math.BigInteger;
import java.util.Scanner;

class JDay3{
	public static void main(String[] args){
	  Scanner sc = new Scanner(System.in);
	  int tc = sc.nextInt();
	  while(tc-->0){
	    long n = sc.nextLong();
	    long k = sc.nextLong();
	    System.out.println(solve(n,k));
	  }
	  sc.close();
	}
	
  private static String solve(long n, long k){
    if (n==1 || k > n/2 || (k==1 && !prime(n)) || (n%2==1 && k==2 && !prime(n-2))) return "No";
    else if (n%2==1 && k>=3) return solve(n-3, k-1);
    else return "Yes";
  }
    
  private static boolean prime(long n){
    return new BigInteger(""+n).isProbablePrime(2000);
  }
}
