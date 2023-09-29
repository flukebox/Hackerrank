// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class CountSemiprimes {

    public int[] solution(int N, int[] P, int[] Q) {
        // let's check if number is prime
        int[] Pr = new int[N+1];
        Pr[0] = 1; Pr[1] = 1;
        for(int i=2; i*i <= N; i++){
            if(Pr[i] == 0){
                int j = i*i;
                while( j <= N ){
                    if(Pr[j] == 0) Pr[j] = 1;
                    j += i;
                }
            }
        }

        // let's store min prime factors for each number
        int[] F = new int[N+1];
        for(int i=2; i*i <= N; i++){
            if(F[i] == 0){
                int j = i;
                while( j <= N ){
                    if(F[j] == 0) F[j] = i;
                    j += i;
                }
            }
        }

        // Now, let's check each number if it's subprime
        int[] prefix = new int[N+1];
        for(int i = 2; i <= N; i++){
            prefix[i] = prefix[i-1];
            // if num is not prime but num div by min factor is prime
            if(Pr[i] == 1 && Pr[i / F[i]] == 0) prefix[i]++;
        }
        int M = P.length;
        int[] out = new int[M];
        for(int i =0; i<M; i++){
            out[i] = prefix[Q[i]]-prefix[P[i]-1];
        }
        return out;
    }
}
