package search;
import java.util.HashSet;
import java.util.Scanner;

public class Pairs {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int k = sc.nextInt();
    HashSet<Integer> numbers = new HashSet<Integer>();
    int count = 0;
    for(int i=0; i<n; i++){
      int num = sc.nextInt();
      if(numbers.contains(num+k)) count++;
      if(numbers.contains(num-k)) count++;
      numbers.add(num);
    }
    System.out.println(count);
    sc.close();
  }
}
