import java.util.Arrays;

public class CountTriangles {
    public int solution(int[] A){
        int n = A.length;
        int k = 0;
        Arrays.sort(A);
        /// Caterpiller Tail
        for(int  p = 0; p < n; p++){
            // Caterpiller head
            int r = p + 2;
            /// Caterpiller Body
            for(int  q = p + 1; q < n; q++) {
                while (r < n && (A[p] + A[q] > A[r])) {
                    r++;
                }
                k += (r-q-1);
            }
        }
        return k;
    }
}
