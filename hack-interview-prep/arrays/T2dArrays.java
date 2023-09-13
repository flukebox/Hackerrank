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
     * Complete the 'hourglassSum' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY arr as parameter.
     */

    public static int hourglassSum(List<List<Integer>> arr) {
    // Write your code here
        // pick minimum value as placeholder
        int c_max = Integer.MIN_VALUE;
        // given that you are receiving 6x6 matrices, all hourglasses would be from (1,1) to (4,4) centered
        for(int i=1; i<=4; i++){
            for(int j=1; j<=4; j++){
                /* hourglass is I centered at (i,j)
                ** so basically, we need a sum as follow
                *  (x-1, y-1), (x-1, y), (x-1, y+1)
                *              (x, y)
                *  (x+1, y-1), (x+1, y), (x+1, y+1)
                **/
                int hourglass = ((arr.get(i-1).get(j-1) + arr.get(i-1).get(j) + arr.get(i-1).get(j+1))
                                + arr.get(i).get(j) +
                        (arr.get(i+1).get(j-1) + arr.get(i+1).get(j) + arr.get(i+1).get(j+1)));
                // replace current_max with current hourglass if hourglass exceeds current max
                if(hourglass > c_max){
                    c_max = hourglass;
                }
            }
        }
        return c_max;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        List<List<Integer>> arr = new ArrayList<>();

        IntStream.range(0, 6).forEach(i -> {
            try {
                arr.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result.hourglassSum(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
