package mar14

import java.io.InputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

object KMP {
  def main(args:Array[String]){
    val reader = new Reader(System.in)
    for(tc <- 1 to reader.next().toInt){
      if(KMPMatch(reader.next(), reader.next()))
        println("YES");
      else 
        println("NO");
    }
  }
  
  def KMPMatch(str:String, pat:String):Boolean={
      val lps = longestPrefixSuffix(pat);
      var i = 0;
      var j = 0;
      while(i < str.length()){
        if(pat.charAt(j)==str.charAt(i)){
          j=j+1; i=i+1;
          if(j==pat.length()){
            return true;
          }
        }else{
          if(j!=0) 
            j=lps(j-1);
          else 
            i = i+1;
        }
      }
      return false;
  }

  def KMPMatchAllCount(str:String, pat:String):Int={
      var count = 0
      val lps = longestPrefixSuffix(pat);
      var i = 0;
      var j = 0;
      while(i < str.length()){
        if(pat.charAt(j)==str.charAt(i)){
          j=j+1; i=i+1;
          if(j==pat.length()){
            j=0
            count +=1
          }
        }else{
          if(j!=0) 
            j=lps(j-1);
          else 
            i = i+1;
        }
      } 
      count
  }

  
  def longestPrefixSuffix(pattern:String):Array[Int]={
      val lps = new Array[Int](pattern.length());
      lps(0)=0;
      var len = 0;
      var i = 1;
      while(i < pattern.length()){
        if(pattern.charAt(i)==pattern.charAt(len)){
          len=len+1;
          lps(i)=len;
          i=i+1;
        }else{
          if(len!=0){
            len=lps(len-1);
          }else{
            lps(i)=0;
            i=i+1;
          }
        }
      }
      return lps;
  }
}

class Reader(input:InputStream){
  private val reader = new BufferedReader(new InputStreamReader(input));
  private var tokenizer = new StringTokenizer("");  
  
  def next():String={
      while(!tokenizer.hasMoreTokens()){
        tokenizer = new StringTokenizer(reader.readLine());
      }
      return tokenizer.nextToken();
  }
}