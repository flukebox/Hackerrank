// you can also use imports, for example:
import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Brackets {
    public int solution(String S) {
        // Implement your solution here
        Stack<Character> stack = new Stack<>();
        int n = S.length();
        for(int i = 0; i < n; i++){
            char c = S.charAt(i);
            switch(c){
                case ')':{
                    if(!stack.isEmpty() && stack.peek() == '(') stack.pop();
                    else return 0;
                    }
                    break;
                case '}':{
                    if( !stack.isEmpty() && stack.peek()  == '{') stack.pop();
                    else return 0;
                    }
                    break;
                case ']':{
                    if( !stack.isEmpty() && stack.peek()  == '[') stack.pop();
                    else return 0;
                    }
                    break;
                default:
                    stack.push(c);
            }
        }
        return stack.isEmpty() ? 1:0;
    }
}
