import java.util.Scanner;
class LCPESY {
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int tc = sc.nextInt();
    while(tc-->0){
      String str1=sc.next();
      String str2=sc.next();
      char[] chars = new char[128];
      for(int i=0; i<str1.length(); i++){
        chars[str1.charAt(i)]++;
      }
      int len = 0;
      for(int i=0; i<str2.length(); i++){
        if(chars[str2.charAt(i)]>0){
          len++;
          chars[str2.charAt(i)]--;
        }
      }
      System.out.println(len);
    }
    sc.close();
  }
}
