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

    static int M = 1000000007;
    static int pow(long a, long b, int m){
        if(b == 0) return 1;
        long res = 1;
        while( b > 0){
            // if last bit is set, update result by multiplying current power of a
            if((b&1) == 1)
                res = (res * a ) % m;
            b = b/2;
            a = (a * a) % m;
        }
        return (int) res % m;
    }

    /*
     * Complete the 'legoBlocks' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER m
     */

    public static int legoBlocks(int n, int m) {
        // ways to get wall of 1xm
        long[] C = new long[1000+1];
        C[0] = 1; // 1x0
        C[1] = 1; // 1x1
        C[2] = 2; // 1x2
        C[3] = 4; // 1x3
        C[4] = 8; // 1x4
        // C[i] = C[i-1] + C[i-2] + C[i-3]+C[i-4];
        for(int i = 5; i <= m; i++){
            C[i] = (C[i-1] + C[i-2] + C[i-3]+C[i-4])%M;
        }

        long[] T = new long[m+1];
        for(int i = 1;  i <=m; i++ ){
            // Count T[i] -- wall of n X i -- wall of height n and width i
            // T[i] = pow(C[i], n) -- But we have to remove all those cases when there could be a single vertical line
            // T[i] = pow(C[i], n) - Summation( T[i-j]*T[j]) where J = 1 to i-1
            long add = pow(C[i], n, M);
            long del = 0;
            for(int j = 1; j < i; j++){
                del = (del + (T[j]*pow(C[i-j], n, M)%M))%M;
            }
            T[i] = (add - del)%M;
        }
        return (int) (T[m] < 0 ? T[m]+M : T[m]);
    }

}

public class LegoBlocks {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);

                int m = Integer.parseInt(firstMultipleInput[1]);

                int result = Result.legoBlocks(n, m);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}