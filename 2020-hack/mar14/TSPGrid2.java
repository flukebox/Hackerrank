package mar14;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;
import java.util.Map.Entry;

public class TSPGrid2 {
  private static final long[] ROW = new long[]{0L, 2000000000L, 2200000000L, 2220000000L, 2222000000L, 2222200000L, 
    2222220000L, 2222222000L, 2222222200L, 2222222220L, 2222222222L};
  private static List<long[]> baseSoln = null;
  private static List<List<Integer>[]> baseSolnPaths = null;
  private static Hashtable<Long, List<long[]>> feasibleSolnsForMask = null;
  
  public static void main(String[] args) {
    baseSoln = new ArrayList<long[]>();
    baseSolnPaths = new ArrayList<List<Integer>[]>();
    feasibleSolnsForMask = new Hashtable<Long, List<long[]>>();
    Scanner sc = new Scanner(System.in);
    int m = sc.nextInt(); 
    int n = sc.nextInt();
    int[][] horz = new int[m][n-1];
    int[][] vert = new int[m-1][n];
    for(int i=0; i<m; i++){
      for(int j=0; j<n-1; j++){
        horz[i][j]=sc.nextInt();
      }
    }
    for(int i=0; i<m-1; i++){
      for(int j=0; j<n; j++){
        vert[i][j]=sc.nextInt();
      }
    }
    
    buildBaseCases(m, 0, 0, 0, baseSoln);
    System.out.println("Total number of base solution => "+baseSoln.size());
    for(long[] sol:baseSoln){
      long mkey = ROW[m]-sol[0];
      if(feasibleSolnsForMask.containsKey(mkey)){
        feasibleSolnsForMask.get(mkey).add(sol);
      }else{
        ArrayList<long[]> mlist = new ArrayList<long[]>();
        mlist.add(sol);
        feasibleSolnsForMask.put(mkey,  mlist);
      }
    }
    System.out.println("Total number of base mask Key => "+feasibleSolnsForMask.size());
    System.out.println(m+":"+n+"==>"+solve(m,n));
    sc.close();
  }
  
  
  public static void solve(int m, int n, int[][] horz, int[][] vert){
    int cost = 0;
    if(m==2 && n==2){
      cost += horz[0][0]+horz[1][0]; 
      cost += vert[0][0]+horz[0][1]; 
    }else if(m==3 && n==2){
      // transition 
    }
    System.out.println(cost);
  }
  
  public static long solve(int m, int n){
    Hashtable<Long, Long> masksSol = new Hashtable<Long, Long>();
    
    long totalSol = 0;
    long currMask = 0;
    for(int i=0; i<n; i++){
      Hashtable<Long, Long> temp = new Hashtable<Long, Long>();
      if(i==0){
        List<long[]> rowsols = feasibleSolnsForMask.get(currMask);
        for(long[] sol:rowsols){
          if(i+1==n && sol[1]==0){
            System.out.println("i=>"+i+", row="+sol[0]+", mask="+sol[1]);
            totalSol+=1L;
          }else if(i+1<n && sol[1]!=0){
            long key = sol[1];
            System.out.println("i=>"+i+", row="+sol[0]+", mask="+sol[1]);
            if(temp.containsKey(key)){
              temp.put(key, temp.get(key)+1L);
            }else{
              temp.put(key, 1L);
            }
          }
        }
      }else{
        for(Entry<Long, Long> entry:masksSol.entrySet()){
          currMask = entry.getKey();
          List<long[]> rowsols = feasibleSolnsForMask.get(currMask);
          for(long[] sol:rowsols){
            if(i+1==n && sol[1]==0){
              System.out.println("i=>"+i+", row="+sol[0]+", mask="+sol[1]);
              totalSol+=entry.getValue();
            }else if(i+1<n && sol[1]!=0){
              System.out.println("i=>"+i+", row="+sol[0]+", mask="+sol[1]);
              long key = sol[1];
              if(temp.containsKey(key)){
                temp.put(key, temp.get(key)+entry.getValue());
              }else{
                temp.put(key, entry.getValue());
              }
            }
          }
        }
      }
      masksSol=temp;
    }
    return totalSol;
  }
  
  private static boolean isValid(long currSol, long currMask, int m){
    long mask[] = {1000000000, 100000000, 10000000, 1000000, 100000, 10000, 1000,100, 10, 1};
    int count0 = 0, count1=0;
    for(int i=0; i<m; i++){
      if((currSol/mask[i])%10==0){
        count0++;
      }
      if((currSol/mask[i])%10==1){
        count1++;
      }
    }
    return (count1&1)==0 && count0==0;
  }
  
  
  private static int getDegree(long currSol, int currIdx){
    long mask[] = {1000000000, 100000000, 10000000, 1000000, 100000, 10000, 1000,100, 10, 1};
    return (int)(currSol/mask[currIdx])%10;
  }
  
  private static long[] addDegree(long currSol, long currMask,  int currIdx, int type){
    long mask[] = {1000000000, 100000000, 10000000, 1000000, 100000, 10000, 1000,100, 10, 1};
    switch(type){
      case 1:{
        currSol += mask[currIdx];
        currMask += mask[currIdx];
        break;
      }
      case 2:{
        currSol += mask[currIdx]+mask[currIdx+1];
        break;
      }
      case 3:{
        currSol += 2*mask[currIdx]+mask[currIdx+1];
        currMask += mask[currIdx];
        break;
      }
    }
    return new long[]{currSol, currMask};
  }
  

  private static void addPath(int m, long currSol, long currMask){
    
  }
  
  private static void buildBaseCases(int m, int currIdx, long currSol, long currMask, List<long[]> sol){
    if(currIdx==m){
      if(isValid(currSol, currMask, m)){
        sol.add(new long[]{currSol, currMask});
      }
    }else{
      int degree = getDegree(currSol, currIdx);
      if(degree<2){
        long[] temp;
        temp = addDegree(currSol, currMask, currIdx, 1);
        buildBaseCases(m, currIdx+1, temp[0], temp[1], sol);
        if(currIdx+1<m){
          temp = addDegree(currSol, currMask, currIdx, 2);
          buildBaseCases(m, currIdx+1, temp[0], temp[1], sol);
          if(degree==0){
            temp = addDegree(currSol, currMask, currIdx, 3);
            buildBaseCases(m, currIdx+1, temp[0], temp[1], sol);
          }
        }
      }
      buildBaseCases(m, currIdx+1, currSol, currMask, sol);
    }
  }
}
