package sorting;

import java.util.Scanner;  

public class CountingSort3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n  = sc.nextInt();
        int[] count = new int[100];
        for(int i=0; i<n; i++){
            count[sc.nextInt()]++;
            sc.next();
        }
        int total = 0;
        for(int i=0; i<100; i++){
            total += count[i];
            System.out.print(total+" ");
        }
        sc.close();
    }
}