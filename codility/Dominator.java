// you can also use imports, for example:
import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Dominator {
    public int solution(int[] A) {
        // Implement your solution here
        int n = A.length;
        if( n == 0) return -1;
        if( n == 1) return  0;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i<n; i++){
            if(!stack.isEmpty() && stack.peek() != A[i]) stack.pop();
            else stack.push(A[i]);
        }
        if(stack.isEmpty()) return -1;
        int candidate = stack.peek();
        int count = 0;
        int index = -1;
        for(int i = 0; i<n; i++){
            if(candidate == A[i]) {
                count++;
                index = i;
            }
        }

        if(count > n/2) return index;
        else return -1;
    }
}