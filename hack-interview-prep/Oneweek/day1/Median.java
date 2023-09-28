package week1prep;


import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class Result {

    /*
     * Complete the 'findMedian' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */
    public static int findIndex(List<Integer> arr, int index) {
        int n = arr.size();
        assert (n != 0);
        int last = arr.get(n-1);
        int pos = 0;
        ArrayList<Integer> smaller = new ArrayList<>();
        ArrayList<Integer> bigger = new ArrayList<>();
        for(int i = 0; i< n-1; i++){
            int c = arr.get(i);
            if(c < last) {
                smaller.add(c);
            } else if (c > last){
                bigger.add(c);
            } else pos++;
        }
        if(smaller.size() > index) return findIndex(smaller, index);
        else if(smaller.size() + pos > index) return last;
        else return findIndex(bigger, index- smaller.size()-pos);
    }
    public static int findMedian(List<Integer> arr) {
        return findIndex(arr, arr.size()/2);
    }

}

public class Median {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = Result.findMedian(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
