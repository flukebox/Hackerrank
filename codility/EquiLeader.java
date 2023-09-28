// you can also use imports, for example:
import java.util.*;

class EquiLeader {
    public int solution(int[] A) {
        int n = A.length;
        // to get leader candiates at right side
        Stack<Integer> stack = new Stack<>();
        for (int j : A) {
            if (!stack.isEmpty() && stack.peek() != j) {
                stack.pop();
            } else stack.push(j);
        }
        if(stack.isEmpty()) return 0;
        int candidate = stack.peek();
        // leader candidates count till index i
        int[] counter = new int[n];
        for(int i = 0; i < n; i++){
            counter[i] = (i > 0 ? counter[i-1] : 0);
            if(candidate == A[i]){
                counter[i]++;
            }
        }
        int count = 0;
        if(counter[n-1] > n/2){
            for(int i = 0; i < n-1; i++){
                if(counter[i] > (i+1)/2 && (counter[n-1]-counter[i]) > (n-i-1)/2) count++;
            }
        }
        return count;
    }
}
