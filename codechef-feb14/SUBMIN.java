import java.util.Hashtable;
import java.util.Scanner;
class SUBMIN{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int[] nums = new int[n];
    for(int i=0; i<n; i++){
      nums[i]=sc.nextInt();
    }
    Hashtable<Integer, Integer> table = new Hashtable<Integer, Integer>();
    for(int i=0; i<n; i++){
      int min = nums[i];
      for(int j=i; j<n; j++){
        if(min>nums[j]){
          min=nums[j];
        }
        if(table.containsKey(min)){
          table.put(min, table.get(min)+1);
        }else{
          table.put(min, 1);
        }
      }
    }
    int tc = sc.nextInt();
    while(tc-->0){
      int k = sc.nextInt();
      if(table.containsKey(k)){
        System.out.println(table.get(k));
      }else{
        System.out.println(0);
      }
    }
    sc.close();
  }
}
