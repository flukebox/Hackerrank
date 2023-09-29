// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class DoubleSlice {
    public int solution(int[] A) {
        int n = A.length;
        int[] lsum  = new int[n];
        int[] rsum  = new int[n];
        int maxDoubleSlice = 0;
        int maxlSum = 0;
        int maxrSum = 0;
        for(int i = 1;  i <n-1; i++){
            maxlSum = Math.max(0, maxlSum+A[i]);
            lsum[i] = maxlSum;
            maxrSum = Math.max(0, maxrSum+A[n-1-i]);
            rsum[n-1-i] = maxrSum;
        }

        for(int i = 1;  i <=n-2; i++){
            maxDoubleSlice = Math.max(maxDoubleSlice, lsum[i-1] + rsum[i+1]);
        }

        return maxDoubleSlice;
    }
}