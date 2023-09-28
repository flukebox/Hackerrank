// you can also use imports, for example:
import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class PermCheck {
    public int solution(int[] A) {
        // Implement your solution here
        int n = A.length;
        HashSet<Integer> set = new HashSet<>();
        long sum = 1L*n*(n+1)/2;
        long csum = 0;

        for(int i = 0; i < n; i++){
            int e = A[i];
            if(set.contains(e)) return 0;
            else set.add(e);
            csum += e;
        }

        if(sum == csum) return 1;
        else return 0;

    }
}