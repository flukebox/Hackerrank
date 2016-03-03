import java.math.BigInteger;
import java.util.*;
class CHSEQ22 {
  private static BigInteger M = new BigInteger("1000000007");
  private static BigInteger two = new BigInteger("2");
  static class Interval{
    int i, j;
    Interval(int i_, int j_){
      i=i_;j=j_;
    }
    @Override
    public String toString(){
      return i+":"+j;
    }    
    @Override
    public boolean equals(Object obj){
      if(obj instanceof Interval){
        if(this.i==((Interval)obj).i && this.j==((Interval)obj).j) return true;
      }
      return false;
    }
    @Override
    public int hashCode(){
      return (i+":"+j).hashCode();
    }
  }

  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();
    HashMap<Integer, HashSet<Interval>> pathsS = new HashMap<Integer, HashSet<Interval>>();
    HashMap<Integer, HashSet<Interval>> pathsE = new HashMap<Integer, HashSet<Interval>>();
    HashSet<Interval> intervals = new HashSet<Interval>();
    for(int i=0; i<m; i++){
      Interval iv = new Interval(sc.nextInt(), sc.nextInt());
      if(intervals.contains(iv)) continue;
      if(!pathsE.containsKey(iv.j)){
        pathsE.put(iv.j, new HashSet<Interval>());
      }
      if(!pathsS.containsKey(iv.i)){
        pathsS.put(iv.i, new HashSet<Interval>());
      }
      
      if(pathsS.containsKey(iv.j+1)){
        for(Interval niv:pathsS.get(iv.j+1)){
          Interval toadd = new Interval(iv.i, niv.j);
          intervals.remove(toadd);
          pathsS.get(iv.i).add(toadd);
          pathsE.get(niv.j).add(toadd);
        }
      }
      if(pathsE.containsKey(iv.i-1)){
        for(Interval niv:pathsE.get(iv.i-1)){
          Interval toadd = new Interval(niv.i, iv.j);
          intervals.remove(toadd);
          pathsS.get(niv.i).add(toadd);
          pathsE.get(iv.j).add(toadd);
        }
      }
      
      if(pathsS.containsKey(iv.j+1) && pathsE.containsKey(iv.i-1)){
        for(Interval niv1:pathsE.get(iv.i-1)){
          for(Interval niv2:pathsS.get(iv.j+1)){
            Interval toadd = new Interval(niv1.i, niv2.j);
            intervals.remove(toadd);
            pathsS.get(niv1.i).add(toadd);
            pathsE.get(niv2.j).add(toadd);
          }
        }
      }
      intervals.add(iv);
      if(pathsS.get(iv.i).contains(iv)){
        intervals.remove(iv);
      }else{
        pathsS.get(iv.i).add(iv);
      }
      if(pathsE.get(iv.j).contains(iv)){
        intervals.remove(iv);
      }else{
        pathsE.get(iv.j).add(iv);
      }
    }
    long ans = two.modPow(new BigInteger(""+intervals.size()), M).longValue();
    if(ans<0) ans = ans+1000000007;
    System.out.println(ans);
    sc.close();
  }
}
