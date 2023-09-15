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

public class CountTriplets {

    // Complete the countTriplets function below.
    static long countTriplets(List<Long> arr, long r) {
        HashMap<Long, List<Integer>> indices = new HashMap<>();
        // let's first put each number and where it occures -- num -> List of Indices where it happens to be
        for (int i=0; i<arr.size(); i++) {
            List<Integer> l = indices.getOrDefault(arr.get(i), new ArrayList<>());
            l.add(i);
            indices.put(arr.get(i), l);
        }

        // Now, we iterate over each arr element one by one and check if we have suitable match/s
        long triplets = 0;
        for (int j=1; j<arr.size()-1; j++) {
            // we get number at  index j,
            long num = arr.get(j);
            // check if r == 1 and we do contains indicices for this number
            if(r == 1 && indices.containsKey(num)){
                // if true, we simply get count by nC3 as we could choose any three number
                // (which are essentially all same), and condition i < j < k automatically
                // taken care of
                long n = indices.get(num).size();
                // return nC3 as answer
                triplets += (n*(n-1)*(n-2)/6L);
                // remove this element from indices
                indices.remove(num);
            }
            // else , we have to check that number is indeed a multiple
            else if(num != 1 && num%r == 0){
                if(indices.containsKey(num/r) && indices.containsKey(num*r)){
                    // get indicies for possible candidates which could be i's
                    List<Integer> indices_i =  indices.get(num/r);
                    // get indicies for possible candidates which could be k's
                    List<Integer> indices_k =  indices.get(num*r);
                    // let's do binary search to get all i's & k's which actually fullfill conditions
                    // of i < j < k
                    long count_i = -Collections.binarySearch(indices_i, j)-1;
                    long count_k = indices_k.size()+Collections.binarySearch(indices_k, j)+1;
                    // Add these triplets to all counts
                    triplets += ( count_i * count_k);
                }
            }
        }
        return  triplets;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/flukebox/Downloads/ct4.txt"));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Long::parseLong)
                .collect(toList());

        long ans = countTriplets(arr, r);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
