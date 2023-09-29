
class Flags {
    public int solution(int[] A) {
        int n = A.length;
        if(n<3) return 0;
        int m = (int) Math.ceil(Math.sqrt(n));
        int[] next = new int[n];
        boolean peaks = false;
        next[n-1] = -1;
        // next tells you index of next peak from this pointer or it'self
        for(int i = n-2; i > 0; i--){
            if(A[i-1] < A[i] && A[i] > A[i+1]){
                next[i] = i;
                peaks = true;
            } else {
                next[i] = next[i+1];
            }
        }

        if( !peaks) return 0;
        next[0] = next[1];
        int max_flags = 0;
        for(int i = 1 ; i <= m ; i++){
            int flags = 0;
            int p = 0;
            while (p < n && flags < i){
                p = next[p];
                if(p == -1) break;
                p += i;
                flags++;
            }
            max_flags= Math.max(max_flags, flags);
        }
        return  max_flags;
    }
}
