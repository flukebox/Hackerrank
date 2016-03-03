import java.util.HashMap;
import java.util.Scanner;

class TWODOGS{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int k = sc.nextInt();
    sc.nextLine();
    HashMap<Integer, Integer> table = new HashMap<Integer, Integer>(); 
    String[] appels = sc.nextLine().split(" ");
    int time = -1;
    for(int i=1; i<=n; i++){
      int t1 = Math.min(i, n-i+1);
      int t2 = -1;
      int apple = Integer.parseInt(appels[i-1]);
      int tofind = k-apple;
      if(apple!=tofind){
        if(table.containsKey(tofind)){
          t2=table.get(tofind);
        }
        if(t2!=-1  && (time==-1 || time>Math.max(t1, t2))){
          time = Math.max(t1, t2);
        }
      }
      if(!table.containsKey(apple) || table.get(apple)>t1){
        table.put(apple, t1);
      }
    }
    System.out.println(time);
    sc.close();
  }  
}
