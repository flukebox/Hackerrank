package sorting;

import java.util.Scanner;  

public class Introduction {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int V = sc.nextInt();
    int n = sc.nextInt();
    for(int i=0; i<n; i++){
      int ar = sc.nextInt();
      if(V==ar){
        System.out.println(i);
        break;
      }        
    }
    sc.close();
  }
}