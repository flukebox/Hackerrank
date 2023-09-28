package Oneweek.day6;

import java.util.Scanner;
import java.util.Stack;


/**
 * Implement a simple text editor. The editor initially contains an empty string, .
 * Perform  operations of the following  types:
 * 1 a append - Append string a to the end of S.
 * 2 k delete - Delete the last k characters of S.
 * 3 k print - Print the kth character of S.
 * 4 undo - Undo the last (not previously undone) operation of type  or , reverting  to the state it was in prior to that operation.
 */

class Op{
    int t;
    int k;
    String a;

    public Op(int t){
        this.t = t;
    }

    public void setA(String a) {
        this.a = a;
    }

    public void setK(int k) {
        this.k = k;
    }

    @Override
    public String toString() {
        return "Op{" +
                "t=" + t +
                ", k=" + k +
                ", a='" + a + '\'' +
                '}';
    }
}

public class SimpleTextEditor {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Stack<Op> ops = new Stack<>();
        for(int i = 0; i < n; i++ ){
            int t = sc.nextInt();
            StringBuilder sb = new StringBuilder();
            Op op = new Op(t);
            switch(t){
                case 1: {
                    //* 1 a append - Append string a to the end of S.
                    String app = sc.next();
                    // append app into current string
                    sb.append(app);
                    op.setA(app);
                    ops.push(op);
                }
                break;
                case 2:{
                    int k = sc.nextInt();
                    int l = sb.length();
                    op.setA(sb.substring(l-k, l));
                    sb.delete(l-k, l);
                    op.setK(k);
                    ops.push(op);
                }
                // * 2 k delete - Delete the last k characters of S.
                break;
                case 3: {
                    // * 3 k print - Print the kth character of S.
                    int k = sc.nextInt();
                    System.out.println(sb.charAt(k));
                }
                break;
                case 4:
                    // * 4 undo - Undo the last (not previously undone) operation of type
                    // 1 or 2, reverting S to the state it was in prior to that operation.
                    if(!ops.isEmpty()){
                        op = ops.pop();
                        if(op.t == 1){
                            int l = sb.length();
                            // delete appended string
                            sb.delete(l-op.a.length(), l);
                        }
                        if(op.t == 2){
                            int k = sb.length();
                            // append deleted string
                            sb.append(op.a);
                        }
                    }
                    break;
            }
        }
    }
}
