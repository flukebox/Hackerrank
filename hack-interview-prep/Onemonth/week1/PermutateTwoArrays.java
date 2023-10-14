package Onemonth.week1;

import java.util.Comparator;
import java.util.List;

public class PermutateTwoArrays {

    /*
     * Complete the 'twoArrays' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. INTEGER k
     *  2. INTEGER_ARRAY A
     *  3. INTEGER_ARRAY B
     */

    public static String twoArrays(int k, List<Integer> A, List<Integer> B) {
        // Write your code here
        A.sort(Comparator.comparingInt(a -> a));
        B.sort(Comparator.comparingInt(a -> -a));

        int n = A.size();
        for(int i = 0; i < n; i++){
            if(A.get(i)+B.get(i) < k) return "NO";
        }
        return "YES";
    }

}
