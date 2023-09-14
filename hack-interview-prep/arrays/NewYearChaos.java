
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'minimumBribes' function below.
     *
     * The function accepts INTEGER_ARRAY q as parameter.
     */

    public static void minimumBribes(List<Integer> q) {
        List<Integer> o = new ArrayList<>(q);

        // Write your code here
        // Basically, any person can jump at max 2 number before
        // NONE jumps backwards
        // Net min jumps -- Sum(Total Jumps forward if each jump <= 2)
        // We will work it as modified form of selection sort
        // We will find out max element from end, and see if it displaced at max 2 places
        // if yes, remove max element and repeat
        // if not -- Too chaotic

        // Total bribes
        int net_bribes = 0;
        // Current max to find
        int maxToFind = o.size();
        // starting point
        int i = o.size() - 1;
        // current bribes done for current max displacement
        int current_bribes = 0;
        // while we are in Array
        while (i >= 0 && maxToFind > 0){
            // we found current max
            if(o.get(i) == maxToFind){
                // if bribes > 2, Too Chaotic
                if(current_bribes > 2){
                    System.out.println("Too chaotic");
                    return;
                } else {
                    net_bribes += current_bribes;
                    o.remove(i);
                    i = o.size() - 1;
                    current_bribes = 0;
                    maxToFind = o.size();
                }
            }else{
                i--;
                current_bribes++;
            }
        }
        // Just print total jumps done
        System.out.println(net_bribes);
    }

}

public class NewYearChaos {
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
