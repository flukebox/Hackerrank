public class TieRopes {


    public int solution(int K, int[] A) {
        int n = A.length;
        int s = 0;
        int ropes = 0;
        int sum = 0;
        while (s < n){
            // tie ropes till we reach length >= K
            while ( s < n && sum < K){
                sum += A[s];
                s++;
            }
            // we have tied ropes length >= K
            if (sum >= K) {
                ropes++;
                sum = 0;
            }
        }
        return ropes;
    }

}
