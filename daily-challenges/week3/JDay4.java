package week3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class JDay4{
  static class Writer {
    static BufferedWriter writer;

    static void init(OutputStream output){
      writer = new BufferedWriter(new OutputStreamWriter(output));
    }
    
    static void writeInt(int n) throws IOException{
      writer.write(""+n+"\n");
    }

    static void writeLong(long n) throws IOException{
      writer.write(""+n+"\n");
    }

    static void writeDouble(double n) throws IOException{
      writer.write(""+n+"\n");
    }

    static void flush() throws IOException{
      writer.flush();
    }
  }

  static class Reader {
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
  
  public static void main(String[] args) throws IOException{
	  Reader.init(System.in);
	  Writer.init(System.out);
	  int tc = Reader.nextInt();
	  while(tc-->0){
	    Writer.writeInt(solve(Reader.nextInt()));
	  }
	  Writer.flush();
	}
	
  private static int solve(int n){
    if((n&1)==1) return 1;
    else{
      int nim = Integer.SIZE - Integer.numberOfLeadingZeros(n);
      int xor = 1^nim;
      int ans = Integer.MAX_VALUE;
      for(int i=1; i<=nim; i++){
        int oldV = 1<<(i-1);
        if((xor^i) < i){
          int nsp = xor^i;
          int newV = 0; 
          if(nsp!=0){
            if((i-nsp)==1){
              newV = 1<<(nsp-1);
            }else{
              newV = (1<<nsp)-1;
            }
          }
          return oldV-newV;
        }
      }
      return ans;
    }
  }    
}
