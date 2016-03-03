package discrete;
import java.util.Arrays;
import java.util.Scanner;

public class BuildingAList {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int tc = sc.nextInt();
    while(tc--!=0){
      sc.nextInt();
      String str = sc.next();
      char[] chars = str.toCharArray();
      Arrays.sort(chars);
      printString(chars, 0, "");
    }
    sc.close();
  }

  public static void printString(char[] chars,  int idx, String prefix){
    if(idx==chars.length) return;
    System.out.println(prefix+chars[idx]);
    printString(chars, idx+1, prefix+chars[idx]);
    printString(chars, idx+1, prefix);
  }
}
