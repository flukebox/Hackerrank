// you can also use imports, for example:
import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class StoneWall {
    public int solution(int[] H) {
        // Implement your solution here
        // stack contains current bricks in use
        Stack<Integer> stack = new Stack<>();

        int n = H.length;
        int brick = 0;
        for(int i = 0; i < n; i++){
            // if stack is empty, add new brick
            if(stack.isEmpty()){
                stack.add(H[i]);
                brick ++;
            }else{
                // if stack brick is higher than current
                // we can not use that brick
                // pop that brick from stack
                while(!stack.isEmpty() && stack.peek() > H[i]){
                    stack.pop();
                }
                // is brick on stack is same height as needed
                if(!stack.isEmpty() && stack.peek() == H[i]){
                    continue;
                }else{
                    // current wall height is greater than brick height on stack
                    stack.push(H[i]);
                    brick ++;

                }
            }
        }
        return brick;

    }
}