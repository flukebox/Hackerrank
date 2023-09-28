public class GenomicRangeQuery {

    public int[] solution(String S, int[] P, int[] Q) {
        int N = S.length();
        int[] data = new int[N];
        for(int i = 0; i < N; i++){
            switch (S.charAt(i)){
                case 'A':
                    data[i] = 1;
                    break;
                case 'C':
                    data[i] = 2;
                    break;
                case 'G':
                    data[i] = 3;
                    break;
                case 'T':
                    data[i] = 4;
                    break;
            }
        }

        int[] aux = new int[4*(N+1)];
        build(data, 1, N, 1, aux);
        int M = P.length;
        int[] result = new int[M];
        for(int i = 0; i < M; i++){
            result[i] = query(aux, P[i]+1, Q[i]+1, 1, N, 1);
        }
        return result;
    }


    static void build(int[]data, int l, int r, int index, int[] aux){
        if(l==r){
            aux[index]=data[l-1];
        }else{
            int mid = (r+l)>>1, leftIndex=index<<1, rightIndex=leftIndex|1;
            build(data, l, mid, leftIndex, aux);
            build(data,mid+1, r, rightIndex, aux);
            aux[index] = Math.min(aux[leftIndex], aux[rightIndex]);
        }
    }

    static int query(int[] aux, int start, int end, int l, int r, int index) {
        if (r < start || end < l) {
            return Integer.MAX_VALUE;
        } else if (start <= l && end >= r) {
            /** return values of current node **/
            return aux[index];
        } else {
            int mid = (r + l) >> 1, leftIndex = index << 1, rightIndex = leftIndex | 1;
            return Math.min(query(aux, start, end, l, mid, leftIndex), query(aux, start, end, mid + 1, r, rightIndex));
        }
    }

}
