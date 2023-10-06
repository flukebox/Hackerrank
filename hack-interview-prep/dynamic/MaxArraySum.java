package dynamic;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MaxArraySum {

    // Complete the maxSubsetSum function below.
    static int maxSubsetSum(int[] arr) {
        int n = arr.length;
        if(n == 1) return Math.max(0, arr[0]);
        if(n == 2) return Math.max(0, Math.max(arr[0], arr[1]));

        int[] dp = new int[n];
        boolean[] last = new boolean[n];
        dp[0] = arr[0] < 0 ? 0 : arr[0];
        last[0] = arr[0] < 0 ? false: true;
        dp[1] = arr[1] < arr[0] ?   arr[0] : arr[1];
        last[1] = arr[1] < arr[0] ?  false : true;

        for(int i = 2; i < n; i++){
            dp[i] = last[i-1] ? Math.max(dp[i-2], dp[i-2] + arr[i]) : Math.max(dp[i-1], dp[i-1] + arr[i]);
        }

        return Math.max(dp[n-1], dp[n-2]);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = maxSubsetSum(arr);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
