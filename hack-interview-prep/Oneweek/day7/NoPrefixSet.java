package Oneweek.day7;

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
     * Complete the 'noPrefix' function below.
     *
     * The function accepts STRING_ARRAY words as parameter.
     */

    // a,b,c,d,e,f,g,h,i,j

    public static void noPrefix(List<String> words) {
        // Write your code here
        int n = words.size();
        HashSet<String> prefix = new HashSet<String>();
        HashSet<String> preWords = new HashSet<String>();

        for(int i =0; i <n; i++){
            String a = words.get(i);

            // this word has NOT been seen in prefix otherwise it's bad
            if(prefix.contains(a)){
                System.out.println("BAD SET");
                System.out.println(a);
                return;
            }
            for(int j =1; j <=a.length(); j++){
                String b = a.substring(0, j);
                // this word's prefix has NOT been seen in other words otherwise it's bad
                if(preWords.contains(b)){
                    System.out.println("BAD SET");
                    System.out.println(a);
                    return;
                }
                prefix.add(b);
            }
            preWords.add(a);
        }
        System.out.println("GOOD SET");
    }
}

public class NoPrefixSet {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> words = IntStream.range(0, n).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .collect(toList());

        Result.noPrefix(words);

        bufferedReader.close();
    }
}