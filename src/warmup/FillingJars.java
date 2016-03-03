package warmup;

import java.util.Scanner;

public class FillingJars {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        long net = 0;
        for(int i=0; i<m; i++){
            long from=sc.nextLong();
            long to = sc.nextLong();
            net+=(to-from+1)*sc.nextLong();
        }
        System.out.println(net/n);
        sc.close();
    }
}