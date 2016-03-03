package mar14;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManasaMaths {
  private static List<int[]> divisible = new ArrayList<int[]>();
  static{
    for(int idx=0; idx<1000; idx++){
      if(idx%8==0){
        String number = idx+"";
        number = "000".substring(0, 3-number.length())+number;
        divisible.add(getHistogram(number));
      }
    }
  }
  
  private static int[] getHistogram(String number){
    int[] buckets = new int[10];
    for(int i=0; i<number.length(); i++){ 
      buckets[number.charAt(i)-48]++;
    }
    return buckets;
  }
  
  private static void permutation(String prefix, String str, List<String> list) {
    int n = str.length();
    if (n == 0){ list.add(prefix);
    }else {
      for (int i = 0; i < n; i++)
        permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n), list);
    }
  }
  
  @SuppressWarnings("unused")
  private static void print(int[]div){
    for(int i=0; i<10; i++){ System.out.print(i+":"+div[i]+"\t");}
    System.out.println();
  }
  
  private static boolean possiblity(int[] div, int[] given){
    for(int i=0; i<10; i++){
      if(div[i]>given[i]) return false;
    }
    return true;
  }
  
  private static void solve(String number){
    boolean yes = false;
    if(number.length()<4){
      List<String> perms = new ArrayList<String>();
      permutation("", number, perms);
      for(String perm:perms){
        int n = Integer.parseInt(perm);
        if(n%8==0){
          yes = true;break;
        }
      }
    }else{
      int[] buckets = getHistogram(number);
      for(int[] div:divisible){
        if(possiblity(div, buckets)){
          yes = true; break;
        }
      }
    }
    System.out.println(((yes)?"YES":"NO"));
  }
  
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int tc = sc.nextInt();
//    for(int i=0; i<tc; i++){
//      System.out.print(i+"->");
//      solve(i+"");
//    }
//    System.exit(0);
    while(tc-->0){
      solve(sc.next());
    }
    sc.close();
  }
}
