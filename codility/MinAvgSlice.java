// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class MinAvgSlice {
    public int solution(int[] A) {
        // Implement your solution here
        int n = A.length;
        double[] two = new double[n];
        double[] three = new double[n];
        int index = -1;
        double min_avg = Double.MIN_VALUE;
        for(int i = 0; i < n-1; i++){
            two[i] = 1.0*(A[i]+A[i+1])*1.0/2;
            if(min_avg > two[i]){
                min_avg = two[i];
                index = (i + 1);
            }
        }
        for(int i = 0; i < n-2; i++){
            three[i] = 1.0*(A[i]+A[i+1]+A[i+2])*1.0/3;
            if(min_avg > three[i]){
                min_avg = three[i];
                index = (i + 1);
            }
        }
        return index;
    }
}