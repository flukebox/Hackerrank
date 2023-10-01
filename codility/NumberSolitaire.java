public class NumberSolitaire {

    public int solution(int[] A) {
        int n = A.length;
        if (n == 2 ) return A[0]+A[1];
        int[] dp = new int[n];

        dp[0] = A[0];
        for(int i = 1; i < n; i++){
            dp[i] = Integer.MIN_VALUE;
            for(int j = 1; j <=6 && j <= i; j++){
                dp[i] = Math.max(dp[i], A[i] + dp[i-j]);
            }
        }
        return dp[n-1];
    }

}
