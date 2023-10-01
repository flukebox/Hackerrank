import java.util.Arrays;

public class MinAbsSum {

    public int solution(int[] A){
        int n = A.length;
        if ( n == 0) return 0;
        int[] C = new int[101];
        int tsum = 0;

        /// get total sum and each abs number count
        for (int k : A) {
            C[Math.abs(k)]++;
            tsum += Math.abs(k);
        }

        // now basically , we want to minimize (tsum - 2*j) i.e (sum-j)-j
        // create sum j with coins C
        int[] dp = new int[tsum/2+1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for(int i = 0; i < C.length; i++){
            // we have count of type C[i] > 0
            if(C[i] > 0){
                // try to reach all numbers which could be made sum C[i] + Earlier number with given C[i] count
                for(int j = 0; j <= tsum/2; j++){
                    // we've already reached j by some combination, just set the count of C[i] at this counter
                    if(dp[j] >= 0) dp[j] = C[i];
                    else if( j >= i && dp [j - i] > 0){
                        dp[j] = dp[j-i] - 1;
                    }
                }
            }
        }
        int minSum = tsum/2+1;
        for(int i = 0; i <= tsum/2; i++){
            if(dp[i] >= 0) minSum = Math.min(minSum, tsum - 2*i);
        }
        return minSum;
    }
}
