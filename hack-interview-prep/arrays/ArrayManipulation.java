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
     * Complete the 'arrayManipulation' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. 2D_INTEGER_ARRAY queries
     */

    /** Let's use prefixArrays for optimal usecase **/
    public static long arrayManipulation(int n, List<List<Integer>> queries) {
        // Write your code here
        long[] ar = new long[n];
        for(int i = 0; i<queries.size(); i++){
            int a = queries.get(i).get(0);
            int b = queries.get(i).get(1);
            int k = queries.get(i).get(2);
            // set prefix sum for a-1 till b-1 and then reverse after b-1
            ar[a-1] += k;
            if(b < n) ar[b] -= k;
        }
        long max = Long.MIN_VALUE;
        for(int i = 1; i< n; i++){
            ar[i] += ar[i-1];
            if(ar[i] > max) max = ar[i];
        }
        // return max
        return max;
    }

    // brute force
    /*
    public static long arrayManipulation(int n, List<List<Integer>> queries) {
        // Write your code here
        long[] ar = new long[n];
        for(int i = 0; i<queries.size(); i++){
            int a = queries.get(i).get(0);
            int b = queries.get(i).get(1);
            int k = queries.get(i).get(2);
            for(int j = a-1; j<=b-1; j++){
                ar[j] += k;
            }
        }

        // return max
        long max = Long.MIN_VALUE;
        for(int i= 0; i<n; i++) max = Math.max(max, ar[i]);
        return max;
    }**/

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, m).forEach(i -> {
            try {
                queries.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        long result = Result.arrayManipulation(n, queries);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
