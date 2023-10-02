import java.util.HashSet;

class AbsDistinct {
    public int solution(int[] A) {
        int k = 0;
        int n = A.length;
        HashSet<Integer> nums = new HashSet<>();
        for (int a : A)  nums.add(Math.abs(a));
        return nums.size();
    }

    public int solution2(int[] A) {
        int n = A.length;
        if (n <= 1) return n;
        long[] B = new long[n];
        for (int i = 0;  i <n; i++)  B[i] = Math.abs(A[i] * 1L);
        long maxValue = Math.max(B[0], B[n-1]);
        int k = 1;
        for(int s = 0, e = n-1; s <= e;){
            if(maxValue == B[s]){
                s++; continue;
            }
            if(maxValue == B[e]){
                e--; continue;
            }
            if(B[s] <= B[e]) {
                maxValue = B[e];
                e--;
            }else{
                maxValue = B[s];
                s++;
            }
            k++;
        }
        return k;
    }

}