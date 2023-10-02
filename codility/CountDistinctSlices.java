import java.util.HashSet;

public class CountDistinctSlices {
    public int solution(int M, int[] A){
        int n = A.length;
        long k = 0;
        /**
         *  [3,4,5,5,2] -- (5 + 10) - 1(3,4,5,5,2) -1 (3,4,5,5) - 1 (4,5,5,2) - 1(4,5,5) - 1(5,5) - 1(5,5,2)
         *  [5,3,4,2,5] --
         *  [5,3,4,5,2] --
         */
        HashSet<Integer> slice = new HashSet<>();
        for(int h = 0, t = 0; h < n && t < n;){
            // we move head till we don't find any duplicates
            if(!slice.contains(A[h])){
                slice.add(A[h]);
                k += (h-t+1);
                h++;
            }else{
            // we have a duplicate, we remove all elements till duplicate
            // we move tail, by making sure h < n, t can never be >= n
            // because elements going side slice can not be more than n
                // At this moment, A[h]=A[t]
                slice.remove(A[t]);
                t++;
            }
            if( k >= 1000000000) break;
        }
        return ( k >= 1000000000) ? 1000000000 : (int) k;
    }
}


