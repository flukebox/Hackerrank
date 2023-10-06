// you can also use imports, for example:
import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class CountNonDivisible {
    public int[] solution(int[] A) {
        // Implement your solution here
        int n = A.length;
        int[] C = new int[100001];
        // maintain count of each element's occurrence
        for(int i = 0; i < n; i++)  C[A[i]]++;
        int[] out = new int[n];
        for(int i = 0; i < n; i++) {
            int count = 0;
            // max Sqrt (n)
            for(int j = 1; j*j <= A[i]; j++){
                // if j is divisor of A[i]
                if(A[i] % j == 0){
                    count += C[j];
                    // if j is divisor of A[i] and j*j != N
                    if( A[i]/j != j ) {
                        count+= C[A[i]/j];
                    }
                }
            }
            out[i] = n-count ;
        }
        return out;
    }
}