import java.io.*;
import java.util.*;

class Result {

    /*
     * Complete the 'decryptPassword' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */
    public static String decryptPassword(String s) {
        // Write your code here
        int olen = 0;
        Stack<Character> stack = new Stack<Character>();
        Queue<Character> fifo = new LinkedList<Character>();

        for(int i=0; i < s.length(); i++){
            if ( !( s.charAt(i) == '*' || s.charAt(i) == '0' )) {
                olen ++;
            }
            if (s.charAt(i) >= '1' && s.charAt(i) >= '9' ) {
                stack.push(s.charAt(i));
            }else {
                fifo.add(s.charAt(i));
            }
        }

        char[] ostr = new char[olen];
        while(!stack.isEmpty()){
            Character c = stack.pop();
            if ( c == '0' ){
                ostr[olen-1] = fifo.remove();
            } else if ( c == '*' ){
                ostr[olen-2] = c;
                ostr[olen-1] = stack.pop();
                olen --;
            }
            olen --;
        }
        System.out.println(ostr);
        return new String(ostr);
    }

    public static void main(String[] args) throws IOException {
        System.out.println(Result.decryptPassword("51Pa*0Lp*0e"));
    }
}

