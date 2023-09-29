// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Peaks {
    public int solution(int[] A) {
        // Implement your solution here
        int n = A.length;
        int m = (int) Math.ceil(Math.sqrt(n));
        // Total peaks sum till current index and i is peak
        int sumTillNow = 0;
        // tell me total peaks till index i
        int[] peaksSum = new int[n];
        for (int i = 1; i < n - 1; i++){
            if ( A[i-1] < A[i] && A[i] > A[i+1]){
                sumTillNow += 1;
            }
            peaksSum[i] = sumTillNow;
        }
        peaksSum[n-1] = sumTillNow;

        // if less than 3 elements, no peak, no block
        if (n < 3) return 0;
        // if n =  OR 4 we can have at max 2|1|0 block
        if (n <= 4) return peaksSum[n-1];
        // if only 1 only peak throughout, only 1 block possible
        if(peaksSum[n-1] == 1) return 1;

        int maxBlocks = 0;
        for (int i = 2; i <= n ; i++){
            // if it's possible to divide A into n/i blocks of size i
            if(n % i == 0){
                if(isGoodSize(i, n, peaksSum) && (maxBlocks < n/i)) maxBlocks = n/i;
            }
        }
        // if prime number
        if(peaksSum[n-1] >  1 && maxBlocks == 0) return 1;
        return  maxBlocks;
    }

    boolean isGoodSize(int i, int n, int[] peaksSum){
        int j = i;
        // check if each block contains a peak
        while (j <= n){
            // first block
            if ( (i == j && peaksSum[j-1] < 1)) break;
            if ( j > i && (peaksSum[j-1] - peaksSum[j-i-1]) < 1) break;
            // every block succeeded in condition, we are a good block
            // go to next block
            if( j == n) break;
            j += i;
        }
        return j == n;
    }
}

