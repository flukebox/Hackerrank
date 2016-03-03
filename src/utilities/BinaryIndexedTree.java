package utilities;
import java.util.Scanner;


public class BinaryIndexedTree {
  private enum BIT_TYPE { PURQ, RUPQ, RURQ};
  
  public static Bit getBit(String type, int size){
    switch(BIT_TYPE.valueOf(type)){
      case PURQ : return new pointUpdateRangeQuery(size);
      case RUPQ : return new rangeUpdatePointQuery(size);
      case RURQ : return new rangeUpdateRangeQuery(size);
      default: return new rangeUpdateRangeQuery(size);
    }
  }
  
  
  public static void main(String[] ars){
    Scanner sc = new Scanner(System.in);
    int tc = sc.nextInt();
    while(tc-->0){
      int n = sc.nextInt();
      int u = sc.nextInt();
      Bit bit = BinaryIndexedTree.getBit("RURQ", n);
      while(u-->0){
        int i = sc.nextInt();
        int j = sc.nextInt();
        int v = sc.nextInt();
        bit.update(i+1, j+1, v);
      }
      int q = sc.nextInt();
      while(q-->0){
        int i = sc.nextInt();
        System.out.println(bit.get(i+1, i+1));
      }
    }
    sc.close();
  }  
}

interface Bit{
  void update(int index, int value);
  void update(int from, int to, int value);
  long get(int index);
  long get(int from, int to);
}

class pointUpdateRangeQuery implements Bit{
  private int size;
  private int[] aux;
  
  public pointUpdateRangeQuery(int size){
    this.size =  size;
    aux = new int[size+1];
  }
  
  // point update
  public void update(int index, int value){
    int k = index;
    while(k<=size){
      aux[k]+=value;
      k += (k & -k);
    }
  }
  
  // point query -- till index
  public long get(int index){
    long ret = 0;
    int k = index;
    while(k!=0){
      ret += aux[k];
      k -=(k & -k);
    }
    return ret;
  }
  
  // range query -- from -- to
  public long get(int from, int to){
    return get(to)-get(from-1);
  }

  @Override
  public void update(int from, int to, int value) {
    for(int i=from; i<=to; i++){
      update(i, value);
    }
  }
}

class rangeUpdatePointQuery implements Bit{
  private int size;
  private int[] aux;
  
  public rangeUpdatePointQuery(int size){
    this.size =  size;
    aux = new int[size+1];
  }
  
  // point update
  public void update(int index, int value){
    int k = index;
    while(k<=size){
      aux[k]+=value;
      k += (k & -k);
    }
  }
  
  // range update
  public void update(int from, int to, int value){
    update(from, value); 
    update(to+1, -value); 
  }
  
  // point query -- get index
  public long get(int index){
    long ret = 0;
    int k = index;
    while(k!=0){
      ret += aux[k];
      k -=(k & -k);
    }
    return ret;
  }

  @Override
  public long get(int from, int to) {
    long ret = 0;
    for(int i=from; i<=to; i++){
     ret+=get(i);
    }
    return ret;
  }
  
}

class rangeUpdateRangeQuery implements Bit{
  private int size;
  private int[] aux1;
  private int[] aux2;
  
  public rangeUpdateRangeQuery(int size){
    this.size =  size;
    aux1 = new int[size+1];
    aux2 = new int[size+1];
  }
  

  // point update
  public void update(int index, int value){
    update(index, index, value);
  }

  private void update(int[] aux, int index, int value){
    int k = index;
    while(k<=size){
      aux[k]+=value;
      k += (k & -k);
    }
  }
  
  // range update
  public void update(int from, int to, int value){
    update(aux1, from, value);
    update(aux1, to+1, -value);
    update(aux2, from, value*(from-1)); 
    update(aux2, to+1, -value*to); 
  }
  
  private long get(int[] aux, int index){
    long ret = 0;
    int k = index;
    while(k!=0){
      ret += aux[k];
      k -=(k & -k);
    }
    return ret;
  }

  // point query
  public long get(int index){
    return get(aux1, index)*index-get(aux2, index);
  }
  
  // range query -- from -- to
  public long get(int from, int to){
    return get(to)-get(from-1);
  }

}

