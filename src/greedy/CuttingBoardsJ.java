package greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

class CuttingBoardsJ {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for(int t=0; t<tc; t++){
            int m = sc.nextInt();
            int n = sc.nextInt();
            Long[] cy = new Long[m-1];
            Long[] cx = new Long[n-1];
            for(int j=0; j<cy.length; j++)  cy[j]=sc.nextLong();
            for(int i=0; i<cx.length; i++)  cx[i]=sc.nextLong();
            Arrays.sort(cy, Collections.reverseOrder());
            Arrays.sort(cx, Collections.reverseOrder());
            System.out.println(solve(cy, cx));
        }
    }

    private static long solve(Long[] cy, Long[] cx){
        int i= 0, j= 0, mod = 1000000007;
        Long cost = 0L;
        while(i < cx.length && j < cy.length){
            if(cx[i] < cy[j])
                cost = (cost+cy[j++]*(1+i))%mod;
            else
                cost = (cost+cx[i++]*(1+j))%mod;
        }
        while(i < cx.length)  cost = (cost + cx[i++]*(1+j))%mod;
        while(j < cy.length)  cost = (cost + cy[j++]*(1+i))%mod;
        return cost;
    }
}