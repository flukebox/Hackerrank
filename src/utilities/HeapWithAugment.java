package utilities;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class HeapWithAugment{
  private Hashtable<Integer, List<Node>> table = new Hashtable<Integer, List<Node>>(); 
  private boolean minType = true; 
  private Node[] heap;
  private int maxSize = 0, count = 0;
  
  class Node{
    private int v, i;
    Node(int v, int i){
      this.v=v;
      this.i=i;
    }
  }
  
  public HeapWithAugment(int size, boolean type){
    this.maxSize = size;
    this.minType = type;
    heap = new Node[size];
  }
  
  public int size(){
    return count;
  }
  
  private boolean op(int i, int j){
    return minType?heap[i].v<=heap[j].v:heap[i].v>=heap[j].v;
  }
  
  public void insert(int v){
    if(count>=maxSize) throw new RuntimeException("Heap overflow");
    Node node = new Node(v, count);
    heap[count]=node;
    if(table.containsKey(v)){
      table.get(v).add(node);
    }else{
      ArrayList<Node> list = new ArrayList<Node>();
      list.add(node);
      table.put(v, list);
    }
    int pidx = (count-1)/2, cidx=count;
    while(pidx>=0 && (pidx!=cidx) && !op(pidx, cidx)){
      swap(pidx, cidx);
      cidx = pidx;
      pidx = (pidx-1)/2;
    }
    count++;
  }
  
  public int peek(){
    if(count>0){
      return heap[0].v;
    }else{
      throw new RuntimeException("No Element in Heap");  
    }
  }
  
  private boolean isleaf(int pos) {
    return ((pos>(count-1)/2)&&(pos<=count));
  }
  
  public boolean pop(int v){
    if(table.containsKey(v) && table.get(v).size()>0){
      Node node = table.get(v).remove(table.get(v).size()-1);
      heap[node.i]=heap[--count];
      heap[node.i].i = node.i; 
      int pos=node.i, rchild, sidx;
      while(!isleaf(pos)){
        sidx = pos*2+1; rchild=sidx+1;
        if(rchild<=count && !op(sidx, rchild)){
          sidx=rchild;
        }
        if(sidx<=count && !op(pos, sidx)){
          swap(sidx, pos);
          pos = sidx;
        }else{
          break;
        }
      }
      int pidx = (pos-1)/2 , cidx=pos;
      while((pidx>=0) && (pidx!=cidx) && !op(pidx, cidx)){
        swap(pidx, cidx);
        cidx = pidx;
        pidx = (pidx-1)/2;
      }
      return true;
    }
    return false;
  }
  
  
  public int pop(){
    int temp = 0;
    if(count>0) {
      temp=heap[0].v;
      pop(temp);
      return temp;
    }else{
      throw new RuntimeException("No Element in Heap");  
    }
  }
  
  // swap 
  public void swap(int i, int j){
    // replace ith node with jth
    Node temp = heap[i];
    heap[i]=heap[j];
    heap[j]=temp;
    heap[i].i = i; heap[j].i = j;
  }
  
  public void printHeap(){
    System.out.print("["+(minType?"MIN":"MAX")+"]");
    for(int i=0; i<count; i++){
      System.out.print(" "+i+":"+heap[i].v+" ");  
    }
    System.out.println();
  }
  
  public void inorder(){
    System.out.print("[IN-"+(minType?"MIN":"MAX")+"]");
    inorder(0);
    System.out.println();
  }
  
  private void inorder(int idx){
    if(idx>=count) return;
    if(2*idx+1<count) inorder(2*idx+1);
    System.out.print(" "+idx+":"+heap[idx].v+" ");
    if(2*idx+2<count) inorder(2*idx+2);
    
  }
  
  public void preorder(){
    System.out.print("[PRE-"+(minType?"MIN":"MAX")+"]");
    preorder(0);
    System.out.println();
  }
  
  private void preorder(int idx){
    if(idx>=count) return;
    System.out.print(" "+idx+":"+heap[idx].v+" ");
    if(2*idx+1<count) inorder(2*idx+1);
    if(2*idx+2<count) inorder(2*idx+2);
  }
}