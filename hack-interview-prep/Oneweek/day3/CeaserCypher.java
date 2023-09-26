package Oneweek.day3;

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
     * Complete the 'caesarCipher' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING s
     *  2. INTEGER k
     */

    public static String caesarCipher(String s, int k) {
        int n = s.length();
        StringBuilder str = new StringBuilder();
        char[] shifts = new char[52];
        for(int i = 0;  i < 26; i++){
            shifts[i] = (char) ('a' +  ( i + k ) % 26);
            shifts[i+26] = (char) ('A' + (i + k ) % 26);
        }

        for(int i = 0; i < n; i++){
            char c = s.charAt(i);
            if(c >= 'a' && c <= 'z'){
                str.append(shifts[c-'a']);
            } else if (c >= 'A' && c <= 'Z'){
                str.append(shifts[c-'A'+26]);
            } else str.append(c);
        }
        return str.toString();
    }

}

public class CeaserCypher {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String s = bufferedReader.readLine();

        int k = Integer.parseInt(bufferedReader.readLine().trim());

        String result = Result.caesarCipher(s, k);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
