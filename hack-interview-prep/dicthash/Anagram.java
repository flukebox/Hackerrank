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
     * Complete the 'sherlockAndAnagrams' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */

    public static int sherlockAndAnagrams(String s) {
        // Write your code here
        int n = s.length();
        int count = 0;
        for(int i = 0; i < n; i++){
            // get all substrings
            for(int size = 1; size <= n - i; size++){
                String s1 = s.substring(i, i+size);
                // all subsequent substring from i+1 of size 'size'
                for(int j = i+1; j <= n-size; j++){
                    String s2 = s.substring(j, j+size);
                    if(isAnagram(s1, s2)){
                        count ++;
                    }
                }
            }
        }
        return count;
    }

    private static boolean isAnagram(String s1, String s2){
        // iff  length of s1 == s2 && all chars in S1 are same as S2
        // by design, we are sending same length substrings only
        HashMap<Character, Integer> cc = new HashMap<>();
        for(char c : s1.toCharArray()){
            cc.put(c, cc.getOrDefault(c, 0) +1);
        }
        for(char key : s2.toCharArray()){
            if(cc.getOrDefault(key, 0) > 0){
                cc.put(key, cc.get(key) - 1);
            } else
                return false;
        }
        // if everything match,  It's Anagram
        return true;
    }


}

public class Anagram {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String s = bufferedReader.readLine();

                int result = Result.sherlockAndAnagrams(s);

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

