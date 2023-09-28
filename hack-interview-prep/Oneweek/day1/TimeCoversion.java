package week1prep;

import java.io.*;

import static java.util.stream.Collectors.joining;

class Result {

    /*
     * Complete the 'timeConversion' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String timeConversion(String s) {
        // Write your code here
        String AmPm = s.substring(8);
        String hr = s.substring(0,2);
        int hour = Integer.parseInt(s.substring(0,2));
        if(AmPm.equals("AM")) hour = hour%12;
        if(AmPm.equals("PM")) hour = 12 + hour%12;
        String hourString = (( hour < 12) ? "0" : "") + hour;
        return hourString+s.substring(2,8);

    }

}

public class TimeCoversion {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        String result = Result.timeConversion(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}