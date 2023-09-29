package Oneweek.day6;

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
     * Complete the 'bfs' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER m
     *  3. 2D_INTEGER_ARRAY edges
     *  4. INTEGER s
     */

    public static List<Integer> bfs(int n, int m, List<List<Integer>> edges, int s) {
        // Write your code here
        int[][] adj = new int[n][n];
        for(int i = 0; i <m; i++){
            int n1 = edges.get(i).get(0)-1;
            int n2 = edges.get(i).get(1)-1;
            adj[n1][n2]=1;
            adj[n2][n1]=1;
        }

        boolean[] traversed = new boolean[n];
        int[] d = new int[n];
        Arrays.fill(d, -1);
        ArrayList<Integer> bfs = new ArrayList<>();
        bfs.add(s-1);
        d[s-1] = 0;
        while(!bfs.isEmpty()){
            int c = bfs.remove(0);
            if(!traversed[c]){
                for(int i = 0; i < n; i++){
                    if(adj[c][i] == 1 && d[i] ==-1 && !traversed[i]){
                        bfs.add(i);
                        d[i] = d[c] + 6;
                    }
                }
            }
            traversed[c] = true;
        }

        ArrayList<Integer> dis = new ArrayList<Integer>();
        for(int i =0; i<n; i++){
            if(i+1 != s) dis.add(d[i]);
        }
        return dis;
    }

}
public class BFS {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);

                int m = Integer.parseInt(firstMultipleInput[1]);

                List<List<Integer>> edges = new ArrayList<>();

                IntStream.range(0, m).forEach(i -> {
                    try {
                        edges.add(
                                Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                        .map(Integer::parseInt)
                                        .collect(toList())
                        );
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                int s = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> result = Result.bfs(n, m, edges, s);

                bufferedWriter.write(
                        result.stream()
                                .map(Object::toString)
                                .collect(joining(" "))
                                + "\n"
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
