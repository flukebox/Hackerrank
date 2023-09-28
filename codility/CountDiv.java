// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class CountDiv {
    public int solution(int A, int B, int K) {
        if(A == B){
            if( A%K == 0) return 1;
            else return 0;
        }else {
            // Implement your solution here
            int n1 =  A%K == 0 ? A/K : A/K + 1;
            int n2 =  B/K;
            return (n2>=n1) ? (n2-n1+1) : 0;
        }
    }
}