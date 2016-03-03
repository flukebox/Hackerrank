import java.util.Scanner;

public class CirclesMath {
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    String str1 = sc.next();
    String str2 = sc.next();
    char[] match1 = new char[256]; 
    char[] match2 = new char[256]; 
    for(int i=0; i<str1.length(); i++){
      match1[str1.charAt(i)]++;
    }
    for(int i=0; i<str2.length(); i++){
      if(match1[str2.charAt(i)]==0){
        match2[str2.charAt(i)]++;
      }else{
        match1[str2.charAt(i)]--;
      }
    }
    int total = 0;
    for(int i=0; i<256; i++){
      total += match1[i]+match2[i];
    }
    System.out.println(total);
    sc.close();
  }
}
