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
     * Complete the 'gridChallenge' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING_ARRAY grid as parameter.
     */

    public static String gridChallenge(List<String> grid) {
        int n = grid.size();
        int m = grid.get(0).length();
        char[][] matrix = new char[n][m];
        // Write your code here
        for(int i =0; i<n; i++){
            for(int j =0; j<m; j++){
                matrix[i][j] = grid.get(i).charAt(j);
            }
        }
        // sort matrix rows
        for(int r =0; r < n; r++){
            for(int c1 =0; c1 < m; c1++){
                for(int c2 =c1+1; c2 < m; c2++){
                    // if c2 is bigger than c1, switch
                    if(matrix[r][c1] > matrix[r][c2]){
                        char temp =  matrix[r][c1];
                        matrix[r][c1] = matrix[r][c2];
                        matrix[r][c2] = temp;
                    }
                }
            }
        }
        // Now check if columns are sorted
        for(int c =0; c < m; c++){
            for(int r = 1; r < n; r++){
                if(matrix[r-1][c] > matrix[r][c]) return "NO";
            }
        }
        return "YES";
    }
}

public class Grid {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<String> grid = IntStream.range(0, n).mapToObj(i -> {
                            try {
                                return bufferedReader.readLine();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        })
                        .collect(toList());

                String result = Result.gridChallenge(grid);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}