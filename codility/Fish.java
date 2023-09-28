// you can also use imports, for example:
import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Fish {
    public int solution(int[] A, int[] B) {
        // Implement your solution here
        Stack<Integer> size = new Stack<>();
        Stack<Integer> dir = new Stack<>();

        int n = A.length;
        for(int i = 0; i < n; i++){
            // Size A[i], direction B[i]
            // if stack is empty, just Add
            if(size.isEmpty() || B[i] == 1 || dir.peek() == B[i]) {
                size.push(A[i]);
                dir.push(B[i]);
            }else {
                // if last fish opp dir and bigger
                // current fish getting gulped
                if(size.peek() > A[i]) continue;
                else {
                    while((!size.isEmpty()) && (size.peek() < A[i]) && (dir.peek() != B[i])) {
                        size.pop();
                        dir.pop();
                    }
                    if(size.isEmpty() || (dir.peek() == B[i])){
                        size.push(A[i]);
                        dir.push(B[i]);
                    }
                }
            }
        }
        return size.size();
    }
}