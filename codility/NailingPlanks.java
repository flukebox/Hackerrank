import java.util.*;

public class NailingPlanks {

    public int solution(int[] A, int[] B, int[] C){
        int n = A.length;
        int m = C.length;
        int N = 60000;
        // we basically, wants to do binary search for index i in C ,
        // where All nails till i are sufficient to nail all planks
        // store the min index of nail which Nailed ith Plank
        int start = 0;
        int end = m;

        int k = -1;
        // Ologm*(n+m)
        while (start <= end){
            int mid = (start+end)/2;
            int[] prefix = new int[N+1];

            // create prefix array for nail's count -- O(m)
            for(int i = 0; i < mid; i++) prefix[C[i]]++;
            for(int i = 1; i <= N; i++) prefix[i] += prefix[i-1];

            // O(n)
            if(check(A, B, n, prefix)){
                // chose left side
                end = mid - 1;
                k = mid;
            }else{
                start = mid + 1;
            }
        }
        return k;
    }

    public boolean check(int A[], int []B, int n, int[] prefix){
        for(int i = 0; i < n; i++){
            // if no nail to be nailed in this plank, no solution
            if(prefix[B[i]] - prefix[A[i]-1] == 0 ) return false;
        }
        return true;
    }

}
