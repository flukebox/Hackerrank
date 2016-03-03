package search;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;

public class Triplets {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int[] data = new int[n+1];
    for(int j=1; j<=n; j++){
      data[j]=sc.nextInt();
    }
    int[] copy = Arrays.copyOf(data, n+1);
    Hashtable<Integer, Integer> map = new Hashtable<Integer, Integer>();
    Arrays.sort(copy);
    for(int i=1; i<copy.length; i++){
      if(!map.containsKey(copy[i])){
        map.put(copy[i], map.size()+1);
      }
    }
    // reducing to 
    for(int i=1; i<data.length; i++){
      data[i]=map.get(data[i]);
    }
    
    int[] auxlt = new int[map.size()+1];
    int[] auxgt = new int[map.size()+1];
    int[] auxltpair = new int[map.size()+1];
    int[] auxgtpair = new int[map.size()+1];
    int[] ltpair = new int[map.size()+1];
    int[] gtpair = new int[map.size()+1];
     
    int gtPairCount = 0;
    for(int i=1; i<data.length; i++){
      updateBIT(auxgt, data[i], 1);
      if(gtpair[data[i]]==1){
        updateBIT(auxgtpair, data[i], 1);
        gtPairCount++;
      }
      gtpair[data[i]]++;
    }

    long triplets = 0;
    Hashtable<Integer, Integer> table = new Hashtable<Integer, Integer>();
    for(int i=1; i<data.length; i++){
      updateBIT(auxlt, data[i], 1);
      if(gtpair[data[i]]==2){
        updateBIT(auxgtpair, data[i], -1);
        gtPairCount--;
      }
      gtpair[data[i]]--;
      long lt = getBIT(auxlt, data[i]-1)-getBIT(auxltpair, data[i]-1);
      long gt = (n-getBIT(auxgt, data[i]))-(gtPairCount-getBIT(auxgtpair, data[i]));
      if(table.containsKey(data[i])){
        lt -= table.get(data[i]);
      }else{
        table.put(data[i],(int) lt);
      }
      triplets+=(lt*gt);
      updateBIT(auxgt, data[i], -1);
      if(ltpair[data[i]]==1){
        updateBIT(auxltpair, data[i], 1);
      }
      ltpair[data[i]]++;
      n--;
    }
    System.out.println(triplets);
    sc.close();
  }

  static void updateBIT(int[] aux, int k, int value){
    while(k<aux.length){
      aux[k]+=value;
      k +=(k&-k);
    }
  }
  static long getBIT(int[] aux, int k){
    long ret=0;
    while(k>=1){
      ret+=aux[k];
      k -=(k&-k);
    }
    return ret;
  }
}


/**
                int n = nextInt();
                int[] a = new int[n];
                for (int i = 0; i < n; ++i) {
                        a[i] = nextInt();
                }
                int[] b = a.clone();
                Arrays.sort(b);
                HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
                for (int i : b) {
                        if (!map.containsKey(i)) map.put(i, map.size());
                }
                Fenwik c1 = new Fenwik(map.size());
                Fenwik c2 = new Fenwik(map.size());
                long[] last1 = new long[map.size()];
                long[] last2 = new long[map.size()];
                Arrays.fill(last1, -1);
                long ans = 0;
                for (int i = 0; i < n; ++i) {
                        int x = map.get(a[i]);
                        if (last1[x] == -1) {
                                c1.add(x, 1);
                                c2.add(x, last1[x] = c1.get(x - 1));
                                ans += last2[x] = c2.get(x - 1);
                        } else {
                                c2.add(x, c1.get(x - 1) - last1[x]);
                                ans += c2.get(x - 1) - last2[x];
                        }
                }
                out.println(ans);
**/