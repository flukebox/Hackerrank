// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class MaxSlice {
    public int solution(int[] A) {
        // Implement your solution here
        int n = A.length;
        int maxSum = A[0];
        int maxSlice = A[0];

        for(int i = 1; i < n; i++){
            maxSum = Math.max(A[i], maxSum + A[i]);
            maxSlice = Math.max(maxSlice, maxSum);
        }
        return maxSlice;
    }
}