public class MaxNonoverlappingSegments {

    public int solution(int[] A, int[] B){
        int n = A.length;
        int k = 0;
        int i  = 0;
        int prev = 0;
        while ( i < n ){
            if(i == 0) k++;
            if(B[prev] < A[i]) { k++; prev=i;}
            i++;
        }
        return k;
    }
}
