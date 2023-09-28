package Oneweek.day4;

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
     * Complete the 'minimumBribes' function below.
     *
     * The function accepts INTEGER_ARRAY q as parameter.
     */

    public static void minimumBribes(List<Integer> q) {
        // Write your code here
        int n = q.size();
        int bribes = 0;
        for(int i = n-1; i > 0; i--){
            // we are right index
            if(q.get(i) == i+1) continue;
            else {
                // right element would be at i-1 or i-2
                if(q.get(i-1) == (i+1)){
                    bribes += 1;
                    q.remove(i-1);
                }else if(q.get(i-2) == (i+1)){
                    bribes += 2;
                    q.remove(i-2);
                } else  {
                    System.out.println("Too chaotic");
                    return;
                };
            }
        }
        System.out.println(bribes);
    }

}

public class Bribes {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> q = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList());

                Result.minimumBribes(q);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
}