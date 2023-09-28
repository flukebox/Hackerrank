public class GenomicRangeQuery2 {

    public int[] solution(String S, int[] P, int[] Q) {
        int N = S.length();
        int[] A = new int[N+1];
        int[] C = new int[N+1];
        int[] G = new int[N+1];

        for(int i = 0; i < N; i++){
            switch (S.charAt(i)){
                case 'A':
                    A[i+1] = 1;
                    break;
                case 'C':
                    C[i+1] = 1;
                    break;
                case 'G':
                    G[i+1] = 1;
                    break;
                default:
                    break;
            }
        }
        for(int i = 1; i <= N; i++) {
            A[i] += A[i-1]; C[i] += C[i-1];  G[i] += G[i-1];
        }

        int M = P.length;
        int[] result = new int[M];
        for(int i = 0; i < M; i++){
            if(A[Q[i]+1]-A[P[i]] > 0){
                result[i] = 1;
            } else if(C[Q[i]+1]-C[P[i]] > 0){
                result[i] = 2;
            } else if(G[Q[i]+1]-G[P[i]] > 0){
                result[i] = 3;
            } else {
                result[i] = 4;
            }
        }
        return result;
    }
}
