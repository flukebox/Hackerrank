package week1prep;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'plusMinus' function below.
     *
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static void plusMinus(List<Integer> arr) {
        // Write your code here
        float pos = 0;
        float neg = 0;
        float zero = 0;
        int n = arr.size();
        for(int i = 0; i < n; i++){
            if(arr.get(i) == 0) zero++;
            if(arr.get(i) < 0) neg++;
            if(arr.get(i) > 0) pos++;
        }
        System.out.println(String.format("%.6f", pos/n));
        System.out.println(String.format("%.6f", neg/n));
        System.out.println(String.format("%.6f", zero/n));
    }

}

public class PlusMinus {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        Result.plusMinus(arr);

        bufferedReader.close();
    }
}
