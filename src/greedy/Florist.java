package greedy;

import java.util.*;

public class Florist {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        long totalCost = 0;
        int[] cs = new int[n];
        int[] xs = new int[k];
        for(int i=0; i<n; i++){
            cs[i]=sc.nextInt();
        }
        Arrays.sort(cs);
        for(int i=cs.length-1; i>=0; i--){
            totalCost += (1+xs[i%k])*cs[i];
            xs[i%k]+=1;
        }
        System.out.println(totalCost);
    }

}