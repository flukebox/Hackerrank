package search;

import java.util.Scanner;

public class Encryption {
  public static void main(String[] args){
    char[][] chars = new char[9][9];
    Scanner sc = new Scanner(System.in);
    String temp = sc.next();
    int n = temp.length();
    int row = (int) Math.floor(Math.sqrt(n));
    int col = (int) Math.ceil(Math.sqrt(n));
    if(row*col<n) row++;
    int idx = 0;
    for(int i=0; i<row; i++){
      for(int j=0; j<col; j++){
        if(idx<n)chars[i][j]=temp.charAt(idx++);
        else break;
      }
    }
    
    for(int j=0; j<col; j++){
      String str = "";
      for(int i=0; i<row; i++){
        if(chars[i][j]==0) break;
        str+=chars[i][j];
      }
      System.out.print(str+" ");
    }
    sc.close();
  }  
}
