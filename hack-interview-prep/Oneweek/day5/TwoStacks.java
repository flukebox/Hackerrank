package Oneweek.day5;

import java.io.*;
import java.util.*;

public class TwoStacks {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        Queue qe = new Queue();
        for(int i = 0; i < q; i++){
            int t = sc.nextInt();
            switch(t){
                case 1:
                    int e = sc.nextInt();
                    //enqueue e
                    qe.enqueue(e);
                    break;

                case 2:
                    // dequeue
                    qe.dequeue();
                    break;
                case 3:
                    // peek front
                    qe.peek();
                    break;

            }
        }

    }

    private static class Queue{
        private Stack<Integer> s1;
        private Stack<Integer> s2;

        public Queue(){
            s1 = new Stack();
            s2 = new Stack();
        }

        public void enqueue(int e){
            s1.push(e);
        }

        public void dequeue(){
            if(s2.isEmpty()){
                while(!s1.isEmpty()){
                    int e = s1.pop();
                    s2.push(e);
                }
            }
            s2.pop();
            return;
        }

        public void peek(){
            if(s2.isEmpty()){
                while(!s1.isEmpty()){
                    s2.push(s1.pop());
                }
            }
            System.out.println(s2.peek());
            return;
        }

    }
}