import java.util.Scanner;


public class MineJumping {
  
  /**
   * @param args
   */
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int tc = sc.nextInt();
    while(tc-->0){
      int b = sc.nextInt();
      int c = sc.nextInt();
      int [] mine = new int[c];
      int [] ways = new int[c+2];
      ways[0] = 1;
      for(int i=0; i<c; i++){
        mine[i] = sc.nextInt();
        if(mine[i]==1){
          ways[i+1] = 0;
        }else{
          for(int k=1; k<=b && (i+1-k)>=0; k++){
            ways[i+1] += ways[i+1-k];
          }
        }
      }
      for(int k=1; k<=b && (c+1-k)>=0; k++){
        ways[c+1] += ways[c+1-k];
      }
      System.out.println(ways[c+1]);
    }
    sc.close();
  }
}
