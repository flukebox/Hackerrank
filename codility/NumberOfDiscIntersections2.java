import java.util.Arrays;
import java.util.Comparator;

public class NumberOfDiscIntersections2 {
    public int solution(int[] A){
        int n = A.length;
        Pair[] pairs = new Pair[2*n];
        for(int i = 0; i < 2*n; i+=2){
            // mark circle start if (i-A[i]) overflow, just mark at 0
            pairs[i] = new Pair((long)i-A[i], 1);
            pairs[i+1] = new Pair((long)i-A[i], -1);
        }
        Arrays.sort(pairs, (Comparator<Pair>) (p1, p2) -> {
            if(p1.x > p2.x) return 1;
            if(p1.x < p2.x) return -1;
            if(p1.start == 1 && p2.start == -1) return -1;
            if(p1.start == -1 && p2.start == 1) return 1;
            return 0;
        });

        long total_pairs = 0;
        long active = 0;
        for (Pair pair : pairs) {
            if (pair.start == 1) {
                // Add Active as total intersected pair with current circle
                total_pairs += active;
                // Increase active pair count
                active++;
            } else {
                // Decrease active pair count
                active--;
            }
            if (total_pairs > 10000000) return -1;
        }
        return (int) total_pairs;
    }

}

class Pair{
    long x;
    int start;

    // Constructor
    public Pair(long x, int start) {
        this.x = x;
        this.start = start;
    }
}