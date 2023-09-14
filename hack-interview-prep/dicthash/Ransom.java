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
     * Complete the 'checkMagazine' function below.
     *
     * The function accepts following parameters:
     *  1. STRING_ARRAY magazine
     *  2. STRING_ARRAY note
     */

    public static void checkMagazine(List<String> magazine, List<String> note) {
        // Put all words with count in hashmap from magazine
        HashMap<String, Integer> words = new HashMap<String, Integer>();
        for(int i = 0; i < magazine.size(); i++){
            String word = magazine.get(i);
            words.put(word, words.getOrDefault(word, 0)+ 1);
        }

        // Check for each word in note and decrease count by 1 from words
        // if word not found, No match else Yes
        for(int i = 0; i < note.size(); i++) {
            if (words.getOrDefault(note.get(i), 0) == 0) {
                System.out.println("No");
                return;
            } else {
                words.put(note.get(i), words.get(note.get(i)) - 1);
            }
        }
        System.out.println("Yes");
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int m = Integer.parseInt(firstMultipleInput[0]);

        int n = Integer.parseInt(firstMultipleInput[1]);

        List<String> magazine = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .collect(toList());

        List<String> note = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .collect(toList());

        Result.checkMagazine(magazine, note);

        bufferedReader.close();
    }
}
