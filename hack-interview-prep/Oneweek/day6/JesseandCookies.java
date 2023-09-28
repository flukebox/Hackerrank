package Oneweek.day6;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'cookies' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER k
     *  2. INTEGER_ARRAY A
     */

    public static int cookies(int k, List<Integer> A) {
        // Write your code here
        int n = A.size();
        int operation = 0;
        if (k == 0) return operation;

        Heap heap = new Heap(n);
        for(int i = 0; i < n; i++){
            heap.insert(A.get(i));
        }
        while(heap.peek() < k && heap.size() > 1){
            operation ++;
            int min1 = heap.pop();
            int min2 = heap.pop();
            int nc = min1 + 2* min2;
            heap.insert(nc);
        }

        if(heap.peek() >= k) return operation;
        else return -1;
    }
}

class Heap{
    private int size;
    private int maxSize;
    private int[] data;

    public Heap(int size){
        this.maxSize = size;
        this.size = 0;
        this.data = new int[size];
    }

    public void swap(int i, int j){
        data[i] = data[i]^data[j];
        data[j] = data[i]^data[j];
        data[i] = data[i]^data[j];
    }

    public void insert(int e){
        // size already exceeds
        if(size == maxSize) throw new RuntimeException("Heap already fulll");
        data[size] = e;
        int pidx = (size-1)/2;
        int cidx = size;

        while((pidx >= 0) && (data[pidx] > data[cidx])){
            swap(pidx, cidx);
            cidx = pidx;
            pidx = (cidx-1)/2;
        }
        // increase size;
        size++;
    }

    public int peek(){
        // size already exceeds
        if(size >= 0) return data[0];
        else throw new RuntimeException("Heap is Empty");
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return (size == 0);
    }
    public boolean isLeaf(int pos){
        // pos is bigger than minimal parent idx possible and smaller than size
        return (pos >(size-1)/2 && pos <= size);
    }


    public int pop(){
        // size already exceeds
        if(isEmpty()) throw new RuntimeException("Heap already Empty");
        int head = data[0];
        data[0] = data[size-1];
        size--;
        int  cidx = 0, r,l;
        while(!isLeaf(cidx)){
            l = cidx*2+1; r = l+1;
            // right is smaller than left
            if((r <= size) && (data[r] < data[l]) && (data[r] < data[cidx])){
                swap(r, cidx);
                cidx = r;
            }else if(data[l] < data[cidx]){
                swap(l, cidx);
                cidx = l;
            } else break;
        }
        return head;
    }
    public void printHeap(){
        StringBuilder str = new StringBuilder();
        str.append("MIN [");
        for(int i=0; i<size; i++){
            str.append(" "+i+":"+data[i]+" ");
        }
        str.append( "] ");
        System.out.println(str.toString());
    }

}


public class JesseandCookies {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> A = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = Result.cookies(k, A);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}