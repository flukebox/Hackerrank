package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JDay4 {
  public static void main(String[] args) throws IOException{
    Reader.init(System.in);
    int n = Reader.nextInt();
    long c = Reader.nextLong();
    long[] a = new long[n];
    long[] b = new long[n];
    long[] diff = new long[n];
    
    for(int i=0; i<n; i++){
      a[i] = Math.min(c, Reader.nextLong());
    }    

    for(int i=0; i<n; i++){
      b[i] = Reader.nextLong();
      diff[i] = a[i]-b[i];
    }    
    
    int total = 0;
    for(int i=0; i<n; i++){
      if(diff[i]<0) continue;
      long fuel = a[i];
      for(int j=0; j<n; j++){
        fuel -= b[(i+j)%n];
        if(fuel < 0) break;
        if(j==n-1){
          if(fuel>=0){
            total++;
          }
          break;
        }
        fuel = Math.min(c, fuel+a[(i+j+1)%n]);
      }
    }
    System.out.println(total);
  }

}
class Reader {
  static BufferedReader reader;
  static StringTokenizer tokenizer;
  static void init(InputStream input) {
    reader = new BufferedReader(new InputStreamReader(input));
    tokenizer = new StringTokenizer("");
  }

  static String next() throws IOException{
    while (!tokenizer.hasMoreTokens()){
      tokenizer = new StringTokenizer(reader.readLine());
    }
    return tokenizer.nextToken();
  }
  
  static long nextLong() throws IOException {
    return Long.parseLong( next() );
  }
  
  static int nextInt() throws IOException {
    return Integer.parseInt( next() );
  }

  static double nextDouble() throws IOException {
    return Double.parseDouble( next() );
  }
}