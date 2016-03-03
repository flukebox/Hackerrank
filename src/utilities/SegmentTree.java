package utilities;
import java.util.Arrays;


public class SegmentTree {
  private enum ST_TYPE {MIN, MAX, SUM};
  
  private int[] aux;
  private int[] auxDelta;
  private ST_TYPE type;

  public static void main(String[] args){
    int[] series = new int[]{30, 12, 23, 10, 15, 50, 8, 9, 7, 1, 4, 6};
    int n = series.length;
    SegmentTree st = new SegmentTree(n, ST_TYPE.MIN.name());
    for(int i=1; i<=n; i++){
      st.update(i, i, 1, n, series[i-1], 1);
      System.out.println(st.query(i, n, 1, 1, n));
    }
    
  }
  
  public SegmentTree(int size, String type){
    this.type = ST_TYPE.valueOf(type);
    aux = new int[4*(size+1)];
    switch (this.type) {
      case MIN : Arrays.fill(aux, Integer.MAX_VALUE);break;
      case MAX : Arrays.fill(aux, Integer.MIN_VALUE);break;
      default : break;
    }
    
    auxDelta = new int[4*(size+1)];
  }
  
  private int operate(int lv, int rv){
    switch (type) {
      case MIN : return Math.min(lv, rv);
      case MAX : return Math.max(lv, rv);
      case SUM : return lv+rv;
    }
    return nullValue();
  }
  
  private int nullValue(){
    switch (type) {
      case MIN : return Integer.MAX_VALUE;
      case MAX : return Integer.MIN_VALUE;
      case SUM : return 0;
    }
    return 0;
  }

  public void build(int[]data, int start, int end, int index){
    if(start==end){
      aux[index]= data[start];
    }else{
      int mid = (end+start)>>1, left=index<<1, right=left|1;
      build(data, start, mid, left);
      build(data, mid+1, end, right);
      aux[index]=operate(aux[left], aux[right]);
    }
  }
  
  public void update(int start, int end, int l, int r, int value, int index){
    if(r<start||end<l) return;
    else if(start<=l && r<=end){
        if(aux[index]==Integer.MAX_VALUE||aux[index]==Integer.MIN_VALUE) aux[index]=0;
        aux[index]+=value;
        if(l<r){
          auxDelta[index]+=value;
        }
    }else{
      int mid = (r+l)>>1, left=index<<1, right=left|1;
      update(start, end, l, mid, value, left);
      update(start, end, mid+1, r, value, right);
      aux[index]=operate(aux[left], aux[right])+auxDelta[index];
    }
  }
  
  public int query(int start, int end, int index, int l, int r){
    if(r<start || end<l){
      /** return null because fall out of place **/
      return nullValue();
    }else if(start<=l && end>=r) {
      /** return values of current node **/
      return aux[index];
    }else{
      int mid = (r+l)>>1, left=index<<1, right=left|1;
      if(auxDelta[index]>0){
        update(l, mid, l, mid, auxDelta[index], left);
        update(mid+1, r, mid+1, r, auxDelta[index], right);
        auxDelta[index]=0;
      }
      /** go to right and left child **/
      return operate(query(start, end, left, l, mid),  query(start, end, right, mid+1, r));
    }
  }
  
}
