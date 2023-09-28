package utilities;
public class Heap{
  private boolean mintype = true; 
  private int[] heap;
  private int maxsize, count = 0;
  // for ith index,  parent at (i-1)/2, lchild = 2*i+1, rchild = 2*i+2
  
  Heap(int size, boolean type){
    this.maxsize = size;
    this.mintype = type;
    heap = new int[size];
  }
  
  private boolean op(int i, int j){
      return mintype?heap[i]<=heap[j]:heap[i]>=heap[j];
  }
  
  public void insert(int v){
    if(count==maxsize) throw new RuntimeException("Heap overflow");
    heap[count]=v;
    int pidx = (count-1)/2, cidx=count;
    while((pidx>=0) && (pidx!=cidx) && !op(pidx, cidx)){
      swap(pidx, cidx);
      cidx = pidx;
      pidx = (pidx-1)/2;
    }
    count++;
  }
  
  public int peek(){
    if(count>0){
      return  heap[0];
    }else{
      throw new RuntimeException("No Element in Heap");  
    }
  }
  
  private boolean isleaf(int pos) {
    return ((pos>(count-1)/2) && (pos <=count));
  }
  
  public int pop(){
    if(count>0) {
      int temp = heap[0];
      heap[0]=heap[--count];
      int pos=0, rchild, sidx;
      while(!isleaf(pos)){
        sidx = pos*2+1; rchild=sidx+1;
        if(rchild<=count && !op(sidx, rchild)){
          sidx=rchild;
        }
        if(sidx<=count && op(sidx, pos)){
          swap(sidx, pos);
          pos = sidx;
        }else{
          break;
        }
      }
      return temp;
    }else{
      throw new RuntimeException("No Element in Heap");  
    }
  }
  
  public void printHeap(){
    System.out.print((mintype?"MIN":"MAX")+" [");
    for(int i=0; i<count; i++){
      System.out.print(" "+i+":"+heap[i]+" ");  
    }
    System.out.println("] ");
  }
  // swap 
  public void swap(int i, int j){
    heap[i]^=heap[j];
    heap[j]^=heap[i];
    heap[i]^=heap[j];
  }
}