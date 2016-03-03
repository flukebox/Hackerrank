import java.util.Scanner;
public class StonePiles {  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int tc = sc.nextInt();
    
    while(tc-->0){
      int n = sc.nextInt();
      int totalCases = 0;
      for(int i=0; i<n; i++){
        int x = sc.nextInt();
        if(x==2||x==1) continue;
        int s = ((x-1)/2+(x-1)%2);
        totalCases+=s;
      }
      if((totalCases&1)==1){
        System.out.println("ALICE");
      }else{
        System.out.println("BOB");
      }
    }
    sc.close();
  }  
}
