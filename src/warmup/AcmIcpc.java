package warmup;

import java.util.Scanner;

public class AcmIcpc {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        String[] topics = new String[n];
        for(int i=0; i<n; i++){
          topics[i] = sc.next();
        }
        int[] sums = new int[(n*(n-1))/2];
        int idx = 0, max = 0, net = 0; 
        for(int i=0; i<n; i++){
          for(int j=i+1; j<n; j++){
            sums[idx++] = getNetTopic(topics[i], topics[j], m);
            if ( max < sums[idx-1]) max = sums[idx-1];
          }
        }
        for(int i=0; i<sums.length; i++) net += ( (sums[i]==max)?1:0);
        System.out.println(max);
        System.out.println(net);
        sc.close();
    }
    
    static int getNetTopic(String t1, String t2, int m){
      int sum = 0;
      for(int i=0; i<m; i++) sum += ((t1.charAt(i)=='0' && t2.charAt(i)=='0')?0:1);
      return sum;
    }
    
}