import java.util.Arrays;

public class MinAbsSumOfTwo {
    public int solution(int[] A){
        int n = A.length;
        // let's first sort A
        Arrays.sort(A);
        if( A[0] >= 0) return 2*A[0];
        if( A[n-1] <= 0) return -2*A[n-1];

        int minAbsSum = Math.abs(A[0] + A[n-1]);
        for (int j : A) {
            if (Math.abs(2*j) < minAbsSum) minAbsSum = Math.abs(2*j);
        }
        for(int s = 0, e = n-1 ; s <= e;){
            int curSum =  Math.abs(A[s] + A[e]);
            if( curSum < minAbsSum) minAbsSum =  curSum;
            if( s == e ) break;
            if(Math.abs(A[s+1]+A[e]) < curSum) s++;
            else if (Math.abs(A[s]+A[e-1]) < curSum) e--;
            else { s++; e--; }
        }
        return minAbsSum;
    }
}
