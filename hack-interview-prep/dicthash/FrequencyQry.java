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

public class FrequencyQry {

    // Complete the freqQuery function below.
    static List<Integer> freqQuery(List<List<Integer>> queries) {
        List<Integer> out = new ArrayList<>();
        // storage for data where we store data -> freq map
        HashMap<Integer, Integer> data = new HashMap<>();
        // storage for freq where we store freq -> data map
        HashMap<Integer, HashSet<Integer>> freq = new HashMap<>();
        for(int q = 0; q < queries.size(); q++){
            List<Integer> query = queries.get(q);
            // Add Query
            if (query.get(0) == 1) {
                int key = query.get(1);
                int c_freq = data.getOrDefault(key, 0);
                int n_freq = c_freq + 1;
                data.put(key, n_freq);
                if (c_freq > 0) {
                    freq.get(c_freq).remove(key);
                }
                HashSet<Integer> l = freq.getOrDefault(n_freq, new HashSet<Integer>());
                l.add(key);
                freq.put(n_freq, l);
            }
            // Delete Query
            if (query.get(0) == 2) {
                int key = query.get(1);
                int c_freq = data.getOrDefault(key, 0);
                int n_freq = c_freq - 1;
                if (c_freq > 0) {
                    freq.get(c_freq).remove(key);
                }
                if (n_freq > 0) {
                    data.put(key, n_freq);
                    HashSet<Integer> l = freq.getOrDefault(n_freq, new HashSet<Integer>());
                    l.add(key);
                    freq.put(n_freq, l);
                }else {
                    data.remove(key);
                }
            }
            // Search Query
            if(query.get(0) == 3) {
                if (freq.getOrDefault(query.get(1), new HashSet<>()).size() > 0) {
                    out.add(1);
                } else out.add(0);
            }
        }
        return out;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> ans = freqQuery(queries);

        bufferedWriter.write(
                ans.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
