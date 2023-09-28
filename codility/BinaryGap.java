// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class BinaryGap {
    public int solution(int N) {
        // Implement your solution here
        int gap = 0;
        int maxGap = 0;
        int mask = 1 << 30;
        int[] islands = new int[31];
        for(int i =0; i < 31; i++){
            islands[i] = ((mask&N) == mask)?1:0;
            mask = mask >> 1;
        }
        int lastOne = -1;
        for(int i =0; i < 31; i++){
            if(islands[i] == 1){
                if(lastOne != -1){
                    gap = i-lastOne-1;
                    if(gap>maxGap) maxGap = gap;
                }
                lastOne = i;
            }
        }
        return maxGap;
    }}