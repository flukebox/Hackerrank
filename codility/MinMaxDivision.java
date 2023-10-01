public class MinMaxDivision {

    public int solution(int K, int M, int[] A){
        int minSum = 0;
        int maxSum = 0;
        int n = A.length;
        for(int i = 0; i < n; i++) {
            maxSum += A[i];
            minSum = Math.max(A[i], minSum);
        }
        // binary search for sum to be a solution of the problem O(long(N*M)*N --> O(NlogN)
        int result = 0;
        while (minSum <= maxSum){
            int midSum = (minSum + maxSum)/2;
            // if solution exists for midsum
            if(solCheck(K, midSum, n, A)){
                // move to left
                result = midSum;
                maxSum = midSum-1;
            }else{
                // move to right
                minSum = midSum+1;
            }
        }
        return result;
    }

    public boolean solCheck(int k, int sum, int n, int[]A){
        // check whether we can divide A into k block of sum <= s
        int csum = 0;
        // first block starting from 0th
        for(int i = 0; i < n; i++){
            csum += A[i];
            if(csum > sum) {
                csum = A[i];
                k--;
            }
        }
        return k > 0;
    }
}
