// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class PassingCars {
    public int solution(int[] A) {
        // Implement your solution here
        int n = A.length;
        int [] pc = new int[n];
        pc[n-1] = A[n-1];
        for(int i = n-2; i >= 0; i--){
            pc[i] += pc[i+1] + A[i];
        }
        long tc = 0;
        for(int i = 0; i < n ; i++){
            if(A[i] == 0) tc = tc + pc[i];
        }

        return (int)((tc <= 1000000000) ? tc : -1);
    }
}