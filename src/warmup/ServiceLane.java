package warmup;

import java.util.*;

public class ServiceLane {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int data[] = new int[n+1];
        for(int i=0; i<n; i++) data[i+1]=sc.nextInt();
        SegmentTree st = new SegmentTree(n, "MIN");
        st.build(data, 1, n, 1);
        while(k-->0){
            int i = sc.nextInt()+1;
            int j = sc.nextInt()+1;
            System.out.println(st.query(i, j, 1, 1, n));
        }
        sc.close();
    }
  }


  class SegmentTree {
    private enum ST_TYPE {MIN, MAX, SUM};
    private int[] aux;
    private ST_TYPE type;
    public SegmentTree(int size, String type){
      this.type = ST_TYPE.valueOf(type);
      aux = new int[4*(size+1)];
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
      if(l==r){
        if(start<=l && end>=r){
          aux[index]+=value;
        }
      }else{
        int mid = (r+l)>>1, left=index<<1, right=left|1;
        update(start, end, l, mid, value, left);
        update(start, end, mid+1, r, value, right);
        aux[index]=operate(aux[left], aux[right]);
      }
    }
    
    public int query(int start, int end, int index, int l, int r){
      if(start<=l && end>=r) {
        /** return values of current node **/
        return aux[index];
      }
      
      if(start> r || l > end){
        /** return null because fall out of place **/
        return nullValue();
      }
      int mid = (r+l)>>1, left=index<<1, right=left|1;
      /** go to right and left child **/
      return operate(query(start, end, left, l, mid),  query(start, end, right, mid+1, r));
    }
    
  }

