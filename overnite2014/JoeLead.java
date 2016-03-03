import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
public class JoeLead {
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    HashSet<Integer> leads = new HashSet<Integer>();
    HashSet<Integer> currleads = new HashSet<Integer>();
    int n = sc.nextInt();
    int[] scores = new int[n];
    for(int i=0; i<n; i++){
      scores[i]=sc.nextInt();
    }
    Arrays.sort(scores);
    leads.add(scores[1]-scores[0]);
    currleads.add(scores[1]-scores[0]);
    for(int i=2; i<n; i++){
      HashSet<Integer> temp = new HashSet<Integer>();
      int delta=scores[i]-scores[i-1];
      leads.add(delta);
      temp.add(delta);
      for(int diff:currleads){
        leads.add(diff+delta);
        temp.add(diff+delta);
      }
      currleads=temp;
    }
    System.out.println(leads.size());
    sc.close();
  }
}
