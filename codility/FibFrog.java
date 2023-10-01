import java.util.Arrays;
import java.util.HashSet;

public class FibFrog {
    public int solution(int[] A){
        int N = 26;
        int[] F = new int[N+1];
        HashSet<Integer> Fibs = new HashSet<>();
        F[0] = 0; F[1] = 1;
        Fibs.add(0);
        Fibs.add(1);
        for(int i = 2; i <= N; i++){
            F[i] = F[i-1] + F[i-2];
            Fibs.add(F[i]);
        }
        int n = A.length;
        // We need to break n into F[a1] + F[a2]... F[an] ... etc
        // such each A[F[a1]-1] = 1, A[F[a1]+F[a2]] = 1;
        // Let's see it as a coin change problem
        // we need to break N into coins of Fibs
        // With condition that whenever we are breaking the N,
        // that point must contain a leaf
        int[] sol = new int[n+1];
        Arrays.fill(sol,Integer.MAX_VALUE);
        for(int i = 0; i <= n; i++){
            // if i is Fib AND either i is leaf or i is bank
            if(Fibs.contains(i+1) && (i == n || A[i] == 1)) {
                sol[i] = 1;
                continue;
            }
            // No leaf AND no bank
            if(i < n && A[i] == 0 ) continue;
            // else we check, if we can reach i with Fib + previous solved i'
            for(int j = 2; j <= N && F[j] <= i; j++){
                if(sol[i-F[j]] != Integer.MAX_VALUE){
                    sol[i] = Math.min(sol[i], sol[i-F[j]]+1);
                }
            }
        }
        return sol[n] == Integer.MAX_VALUE ? -1 : sol[n];
    }
}
