package search;
import java.util.Arrays;
import java.util.Scanner;
/**
 * www.fq.math.ca/Scanned/14-2/alonso.pdf
 * http://www.encyclopediaofmath.org/index.php/Arithmetic_series
 */

public class ArithmaticProgession {
  private static int M = 1000003;
  private static int[] facts = new int[M];
  private static long[] auxk;
  private static long[] auxv;
  private static long[] auxd;
  private static int[] auxkDelta;
  private static long[] auxvDelta;
  static {
    facts[0]=1;
    long temp = 1;
    for(int i=1; i<M; i++){
      temp = (temp*i)%M;
      facts[i] = (int)temp;
    }
  }
  
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int[][] series = new int[n+1][4];
    for(int i=1; i<=n; i++){
      for(int j=0; j<3; j++){
        series[i][j] = sc.nextInt();
      }
    }
    auxd = new long[4*n];
    auxv = new long[4*n];
    auxk = new long[4*n];
    auxvDelta = new long[4*n];
    auxkDelta = new int[4*n];
    Arrays.fill(auxvDelta, 1);
    build(series, 1, n, 1);
    int c = 0;
    int q = sc.nextInt();
    long [][] anss = new long[q][]; 
    for(int i=0; i<q; i++){
      int query = sc.nextInt();
      int start = sc.nextInt();
      int end = sc.nextInt();
      if(query==0){
        long[] ans = query(start, end, 1, 1, n);
        if(ans[1]!=0) ans[1] = (ans[1]*fact(ans[0]))%M;
        anss[c++] = ans;
      }else{
        update(start, end, 1, n, sc.nextInt(), 1);
      }
    }
    
    for(int i=0; i<c; i++){
      System.out.println(anss[i][0]+" "+anss[i][1]);
    }
    sc.close();
  }
  
  static void build(int[][]data, int start, int end, int index){
    if(start==end){
      auxd[index]= data[start][1];
      auxk[index] = data[start][2];
      auxv[index] = modPower(data[start][1], data[start][2]);
    }else{
      int mid = (end+start)>>1, left=index<<1, right=left|1;
    build(data, start, mid, left);
    build(data, mid+1, end, right);
    auxv[index] = (auxv[left]*auxv[right])%M;
    auxk[index] = (auxk[left]+auxk[right]);
    auxd[index] = (auxd[left]*auxd[right])%M;
    }
  }
  
  static void update(int start, int end, int l, int r, int value, int index){
    if(r<start||end<l) return;
    else if(start<=l && r<=end){
      long vupdate = modPower((int)auxd[index], value);
      auxv[index]=(auxv[index]*vupdate)%M;
      auxk[index]+=value*(r-l+1);
      if(l<r){
        auxvDelta[index]=(auxvDelta[index]*vupdate)%M;
        auxkDelta[index]=(auxkDelta[index]+value);
      }
    }else{
      int mid = (r+l)>>1, left=index<<1, right=left|1;
      update(start, end, l, mid, value, left);
      update(start, end, mid+1, r, value, right);
      auxv[index] = ((auxv[left]*auxv[right])%M*auxvDelta[index])%M;
      auxk[index] = auxk[left]+auxk[right]+(auxkDelta[index]*(r-l+1));
    }
  }

  static long[] query(int start, int end, int index, int l, int r){
    if(r<start||end<l){
      /** return null because fall out of place **/
      return new long[]{0,1};
    }else if(start<=l && r<=end) {
      /** return values of current node **/
      return new long[]{auxk[index], auxv[index]};
    }else{
      int mid = (r+l)>>1, left=index<<1, right=left|1;
      if(auxkDelta[index]>0){
        update(l, mid, l, mid, auxkDelta[index], left);
        update(mid+1, r, mid+1, r, auxkDelta[index], right);
        auxkDelta[index]=0;
        auxvDelta[index]=1;
      }
      /** go to right and left child **/
      long [] ansl = query(start, end, left, l, mid);
      long [] ansr = query(start, end, right, mid+1, r);
      return new long[]{ (ansl[0]+ansr[0]), (ansl[1]*ansr[1])%M};
    }
  }
  
  static int fact(long k){
    if(k>=M) return 0;
    return facts[(int) k];
  }
  
  static int modPower(int base, int pow){
    long t = 1;
    long p = base;
    while(pow >= 1) {
      if((pow & 1) == 1) t = (t * p) % M;
      p = (p * p) % M;
      pow >>>= 1;
    }
    return (int) t;
  }
}