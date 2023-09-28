public class NumberOfDiscIntersections {
    public int solution(int[] A){
        int n = A.length;
        long[] starts = new long[n];
        long[] ends = new long[n];

        for(int i = 0; i < n; i++){
            // mark circle start if (i-A[i]) overflow, just mark at 0
            if(i < A[i]) starts[0]++;
            else starts[i-A[i]]++;
            // mark circle end if (i-A[i]) overflow, just mark at n-1
            if(((long) i + A[i]) >= n) ends[n-1]++;
            else ends[i+A[i]]++;
        }

        long pairs = 0;
        long active = 0;
        for(int i = 0; i<n; i++){
            // count new pairs from the group
            pairs += starts[i]*(starts[i]-1)/2;
            // count pairs of new group with active
            pairs += active*starts[i];
            active += starts[i]-ends[i];
            if(pairs > 10000000) return -1;
        }
        return (int) pairs;
    }
}
