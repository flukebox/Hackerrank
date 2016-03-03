package search;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ClosestNumbers {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int [] nums = new int[n];
    int[] diff = new int[n-1];
    for(int i=0; i<n; i++){
      nums[i]=sc.nextInt();;
    }
    Arrays.sort(nums);
    int minDiff = Integer.MAX_VALUE;
    ArrayList<Integer> list = new ArrayList<Integer>();
    for(int i=0; i<n-1; i++){
      diff[i]=Math.abs(nums[i]-nums[i+1]);
      if(diff[i]<minDiff){
        minDiff = diff[i];
        list = new ArrayList<Integer>();
        list.add(i);
      }else if(diff[i]==minDiff){
        list.add(i);
      }
    }
    
    for(int i:list){
      System.out.print(nums[i]+" "+nums[i+1]+" ");
    }
    sc.close();
  }
}
