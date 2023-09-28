// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class MaxCounters {
    public int[] solution(int N, int[] A) {
        // Implement your solution here
        int[] counters = new int[N];
        int m = A.length;
        int max = 0;
        int base = 0;
        boolean dirty = false;
        for(int i = 0; i < m; i++){
            if(A[i] == N + 1){
                // reset to zero
                if(dirty){
                    counters = new int[N];
                    dirty = false;
                }
                base = base + max;
                max = 0;
            }else {
                dirty = true;
                counters[A[i]-1]++;
                if(counters[A[i]-1] > max) max =  counters[A[i]-1];
            }
        }
        for(int i = 0; i < N; i++){
            counters[i] += base;
        }
        return counters;
    }
}