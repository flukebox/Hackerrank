package week7;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class JDay3{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    String str = sc.next();
    FastSuffixArrays suffix = new FastSuffixArrays(str+"$");
    Deque<Integer> lcpQ = new ArrayDeque<Integer>();
    Deque<Integer> countQ = new ArrayDeque<Integer>();
    long max = str.length();
    for (int i = 1; i < str.length()+1; i++){
        int lcp = suffix.LCP[i];
        int tempCount = 0;
        while(!lcpQ.isEmpty() && lcp <= lcpQ.peek()){
          long clcp = lcpQ.pop();
          long cc = countQ.pop();
          long cval = (cc+tempCount+1)*clcp;
          if( max < cval) max = cval;
          tempCount += cc;
        }
        if(lcp>=2){
          lcpQ.push(lcp);
          countQ.push(tempCount+1);
        }
    }
    int tempCount = 0;
    while(!lcpQ.isEmpty()){
      long clcp = lcpQ.pop();
      long cc = countQ.pop();
      long cval = (cc+tempCount+1)*clcp;
      if( max < cval) max = cval;
      tempCount += cc;
    }
    System.out.println(max);
    sc.close();
  }
}

class FastSuffixArrays{
  private static int MAX_N = 100001;
  char T[];              // the input string, up to 100K characters
  int n;                 // the length of input string
  int[] RA, tempRA;      // rank array and temporary rank array
  int[] SA, tempSA;      // suffix array and temporary suffix array
  int[] c;               // for counting/radix sort
  int[] Phi;             // for computing longest common prefix
  int[] PLCP;
  int[] LCP;             // LCP[i] stores the LCP between previous suffix "T + SA[i-1]" and current suffix "T + SA[i]"

  void countingSort(int k) {
    int i, sum, maxi = Math.max(300, n);    // up to 255 ASCII chars or length of n
    for (i = 0; i < MAX_N; i++) c[i] = 0;   // clear frequency table
    for (i = 0; i < n; i++)                 // count the frequency of each rank
      c[i + k < n ? RA[i + k] : 0]++;
    for (i = sum = 0; i < maxi; i++) {
      int t = c[i]; c[i] = sum; sum += t;
    }
    for (i = 0; i < n; i++)               // shuffle the suffix array if necessary
      tempSA[c[SA[i] + k < n ? RA[SA[i] + k] : 0]++] = SA[i];
    for (i = 0; i < n; i++)               // update the suffix array SA
      SA[i] = tempSA[i];
  }

  void constructSA() {              // this version can go up to 100000 characters
    int i, k, r;
    for (i = 0; i < n; i++) RA[i] = T[i];                      // initial rankings
    for (i = 0; i < n; i++) SA[i] = i;          // initial SA: {0, 1, 2, ..., n-1}
    for (k = 1; k < n; k <<= 1) {            // repeat sorting process log n times
      countingSort(k);       // actually radix sort: sort based on the second item
      countingSort(0);               // then (stable) sort based on the first item
      tempRA[SA[0]] = r = 0;                  // re-ranking; start from rank r = 0
      for (i = 1; i < n; i++)                         // compare adjacent suffices
        tempRA[SA[i]] =      // if same pair => same rank r; otherwise, increase r
          (RA[SA[i]] == RA[SA[i-1]] && RA[SA[i]+k] == RA[SA[i-1]+k]) ? r : ++r;
      for (i = 0; i < n; i++)                          // update the rank array RA
        RA[i] = tempRA[i];
    }
  }

  void computeLCP() {
    int i, L;
    Phi[SA[0]] = -1;                                              // default value
    for (i = 1; i < n; i++)                                 // compute Phi in O(n)
      Phi[SA[i]] = SA[i-1];         // remember which suffix is behind this suffix
    for (i = L = 0; i < n; i++) {                  // compute Permuted LCP in O(n)
      if (Phi[i] == -1) { PLCP[i] = 0; continue; }                 // special case
      while (i + L < T.length && Phi[i] + L < T.length && T[i + L] == T[Phi[i] + L]) L++; // L will be increased max n times
      PLCP[i] = L;
      L = Math.max(L-1, 0);                          // L will be decreased max n times
    }
    for (i = 1; i < n; i++)                                 // compute LCP in O(n)
      LCP[i] = PLCP[SA[i]];   // put the permuted LCP back to the correct position
  }

  public FastSuffixArrays(String str) {
    c = new int[MAX_N];
    RA = new int[MAX_N];
    tempRA = new int[MAX_N];
    SA = new int[MAX_N];
    tempSA = new int[MAX_N];
    Phi = new int[MAX_N];
    PLCP = new int[MAX_N];
    LCP = new int[MAX_N];
    //System.out.printf("Enter a string T below, we will compute its Suffix Array:\n");
    T = str.toCharArray();
    n = T.length;
    constructSA();                                                   // O(n log n)
    computeLCP();                                                          // O(n)
  }
}