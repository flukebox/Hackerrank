package week1prep;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'miniMaxSum' function below.
     *
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static void miniMaxSum(List<Integer> arr) {
        int n = arr.size();
        // we have sorted arrays, now get min and max sump
        long min = Long.MAX_VALUE, max = Long.MIN_VALUE;
        long total = 0;
        for(int i = 0; i<n; i++){
            if(min > arr.get(i)) min = arr.get(i);
            if(max < arr.get(i)) max= arr.get(i);
            total += arr.get(i);
        }
        System.out.println((total-max)+" "+(total-min));
    }
}

public class MiniMaxSum {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        Result.miniMaxSum(arr);

        bufferedReader.close();
    }
}
