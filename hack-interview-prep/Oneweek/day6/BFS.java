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
        ArrayList<Integer> bfs = new ArrayList<Integer>();
        int[][] adj = new int[n][n];
        int[] distance = new int[n];
        boolean[] traversed = new boolean[n];
        for(int i = 0; i<m; i++){
            List<Integer> edge = edges.get(i);
            adj[edge.get(0)-1][edge.get(1)-1] = 1;
            adj[edge.get(1)-1][edge.get(0)-1] = 1;
        }
        for(int i = 0; i< n; i++) distance[i] = -1;
        distance[s-1] = 0;

        // start queue with s node edges
        for(int i = 0; i<n; i++){
            if(adj[s-1][i] == 1) {
                bfs.add(i);
                distance[i] = 6;
            }
        }
        traversed[s-1] = true;
        // while queue is not empty
        while(!bfs.isEmpty()){
            int e = bfs.remove(0);
            if(!traversed[e]){
                for(int i = 0; i<n; i++){
                    if(adj[e][i] ==1 && distance[i] ==-1) {
                        distance[i] = distance[e] + 6;
                        bfs.add(i);
                    }
                }
            }
            traversed[e] = true;
        }
        ArrayList<Integer> res = new ArrayList<>();
        for(int i = 0; i<n; i++){
            if( i+1 != s) res.add(distance[i]);
        }
        return res;
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
