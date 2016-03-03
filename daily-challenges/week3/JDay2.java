package week3;

import java.util.Scanner;

class JDay2{
  private static int m = 1000000007;
	private static long[] pi = new long[200001];
	private static long temp= 1L;
	static {
	  pi[0] = 1;
  	for(int i=1; i<=200000; i++){
  		temp = (temp*10)%m;
  		pi[i] = (pi[i-1]+temp)%m;
  	}
	}
	
	public static void main(String[] args){
	  Scanner sc = new Scanner(System.in);
	  String line = sc.next();
	  long total = 0;
	  for(int i=0; i<line.length(); i++){
	    total = (total + ((((line.charAt(i)-'0')*(i+1))%m)*(pi[line.length()-i-1]))%m)%m;
	  }
	  System.out.println(total);
	  sc.close();
	}
}
