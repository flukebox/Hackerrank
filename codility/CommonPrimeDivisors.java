import java.util.ArrayList;
import java.util.HashSet;

//For example, for the input ([2, 1, 2], [1, 2, 2]) the solution returned a wrong answer (got 3 expected 1).
// [3, 9, 20, 11], [9, 81, 5, 13] -- 2
class CommonPrimeDivisors {
    int gcd(int a,int b){
        if (a % b == 0) return b;
        else return gcd(b, a%b);
    }

    int gcd2(int a, int b, int res){
        if (a == b) return res*a;
        if( a % 2 == 0 && b % 2 == 0) return gcd2(a/2, b/2, 2*res);
        if( a % 2 == 0) return gcd2(a/2, b, res);
        if( b % 2 == 0) return gcd2(a, b/2, res);
        if( a > b) return gcd2(a - b, b, res);
        else return gcd2(a, b - a, res);
    }

    public int solution(int[] A, int[] B) {
        int n = A.length;
        int k = 0;
        for(int i = 0; i < n; i++){
            if (A[i] == B[i]) {
                k++;
                continue;
            }
            // get the gcd of A and B
            int g = gcd2(A[i], B[i], 1);
            int a = A[i], b = B[i];
            int g1 = g;
            // Now, get reminder of A, B after dividing from factors
            while (a > 1){
               g1 = gcd2(a, g, 1);
               if(g1 == 1) { break; }
                a = a / g1;
            }
            while (b > 1){
                g1 = gcd2(b, g, 1);
                if(g1 == 1) { break;}
                b = b / g1;
            }
            if (a == 1 && b ==1) k++;
        }
        return k;
    }
}