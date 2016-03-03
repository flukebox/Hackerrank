package feb14;
import java.util.Scanner;
public class RMQ {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();
    sc.nextLine();
    int k = (int)Math.ceil(Math.log(n) / Math.log(2));
    String[] data = sc.nextLine().split(" ");
    int[][] table = new int[n][k+1];
    for(int j=0; j<=k; j++){
      for(int i=0; i<n; i++){
        if(j==0){
          table[i][j]=Integer.parseInt(data[i]);
        }else{
          int ki = 1<<(j-1); 
          if((i+ki<n)) {
            table[i][j]=Math.min(table[i][j-1], table[i+ki][j-1]); 
          }else{
            table[i][j]=table[i][j-1]; 
          }
        }
      }
    }
    for(int i=0; i<m; i++){
      int a = sc.nextInt();
      int b = sc.nextInt();
      if(b==a){
        System.out.println(data[a]);
      }else{
        int ki = (int) Math.floor(Math.log(b-a) / Math.log(2));
        int bi = (b-(1<<ki));
        System.out.println(Math.min(table[a][ki], table[bi+1][ki]));
      }
    }
    sc.close();
  }      
}


