package math;

import java.util.Scanner;

public class GameOfThrones1 {
  public static void main(String[] args) {
    Scanner myScan = new Scanner(System.in);
    String inputString = myScan.nextLine();
    int[] chars = new int[26];
    for(byte c:inputString.getBytes()){
      if(chars[c-'a']==0){
        chars[c-'a']++;
      }else{
        chars[c-'a']--;        
      }
    }
    int nonzero = 0;
    for(int i=0; i<26; i++){
      if(chars[i]==1){
        nonzero++;
      }
    }
    System.out.println((nonzero>1)?"NO":"YES");
    myScan.close();
  }
}
