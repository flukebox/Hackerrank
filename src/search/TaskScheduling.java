package search;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class TaskScheduling {
  static class Node implements Comparable<Node>{
    int d, m;
    Node(int d_, int m_){
      d=d_;m=m_;
    }
    @Override
    public int compareTo(Node o) {
      if((this.d-o.d)!=0)  return (this.d-o.d);
      else return this.m-o.m;
    }
    @Override
    public String toString(){
      return d+":"+m;
    }    
  }
  
  static HashSet<Integer> seen = new HashSet<Integer>();
  @SuppressWarnings("unchecked")
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int size = sc.nextInt();
    ArrayList<Node> dm = new ArrayList<Node>();
    for(int i=0; i<size; i++){
      dm.add(new Node(sc.nextInt(), sc.nextInt()));
    }
    
    /** clone input and create the index mapping **/
    ArrayList<Node> dmClone = ((ArrayList<Node>) dm.clone());
    Collections.sort(dmClone);
    HashMap<Node, Integer> indexMap = new HashMap<Node, Integer>();
    
    for(int i=0; i<size; i++){
      indexMap.put(dmClone.get(i), i+1);
    }
    SegmentTree maxst= new SegmentTree(size);
    maxst.build(1, size, 1);
    for(int i=0; i<dm.size(); i++){
      Node node = dm.get(i);
      int index = indexMap.get(node);
      seen.add(index);
      maxst.update(index, index, 1, size, -node.d, 1);
      maxst.update(index, size, 1, size,  node.m, 1);
      System.out.println(maxst.query(1, size, 1, 1, size)[0]);
    }
    sc.close();
  }
  
  private static class SegmentTree{
    private int[] aux;
    private int[] idx;
    private int[] auxDelta;
    
    public SegmentTree(int size){
      aux = new int[4*(size+1)];
      auxDelta = new int[4*(size+1)];
      idx = new int[4*(size+1)];
    }

    public void build(int l, int r, int index){
      if(l==r){
        aux[index]=Integer.MIN_VALUE;
      }else{
        int mid = (r+l)>>1, left=index<<1, right=left|1;
        build(l, mid, left);
        build(mid+1, r, right);
      }
    }
    
    public int max(int l, int r){
      if(idx[l]==0) return r;
      if(idx[r]==0) return l;
      if(aux[l]>aux[r]) return l;
      else return r;
    }

    public int[] max(int[] l, int[] r){
      if(l[1]==0) return r;
      if(r[1]==0) return l;
      if(l[0]>r[0]) return l;
      else return r;
    }

    
    public void update(int start, int end, int l, int r, int value, int index){
      if(r<start||end<l) return;
      else if(start<=l && r<=end){
        if(l==r){
            if(seen.contains(l)){
              if(aux[index]==Integer.MIN_VALUE){
                aux[index]=auxDelta[index]+value;
                auxDelta[index]=0;
                idx[index]=1;
              }else{
                aux[index]+=value;
              }
            }else{
              auxDelta[index]+=value;
            }
        }else if(l<r){
            aux[index]+=value;
            auxDelta[index]+=value;
        }
      }else{
        int mid = (r+l)>>1, left=index<<1, right=left|1;
        if(auxDelta[index]>0){
          update(l, mid, l, mid, auxDelta[index], left);
          update(mid+1, r, mid+1, r, auxDelta[index], right);
          auxDelta[index]=0;
        }
        update(start, end, l, mid, value, left);
        update(start, end, mid+1, r, value, right);
        aux[index]=aux[max(left, right)];
        idx[index]=idx[max(left, right)];
      }
    }

    public int[] query(int start, int end, int index, int l, int r){
      if(r<start || end<l){
        /** return null because fall out of place **/
        return new int[]{0,0};
      }else if(start<=l && end>=r){
        /** return values of current node **/
        return new int[]{aux[index]<0?0:aux[index], l};
      }else{
        int mid = (r+l)>>1, left=index<<1, right=left|1;
        if(auxDelta[index]>0){
          update(l, mid, l, mid, auxDelta[index], left);
          update(mid+1, r, mid+1, r, auxDelta[index], right);
          auxDelta[index]=0;
        }
        /** go to right and left child **/
        return max(query(start, end, left, l, mid), query(start, end, right, mid+1, r));
      }
    }
  }
}