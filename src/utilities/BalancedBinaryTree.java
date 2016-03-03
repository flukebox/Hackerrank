package utilities;
import java.util.Random;

public class BalancedBinaryTree{
  Node root;
  
  class Node{
    int h,v;
    Node l,r,p;
  }
  
  public void insert(int v){
    Node node = new Node();
    node.v = v;
    if(root==null){
      root = node;
      root.p = null;
    }else{
      insert(root, node);
    }
  }
  
  private void insert(Node croot, Node node){
    if(node.v <= croot.v){
      if(croot.l==null){
        croot.l = node;
        node.p = croot;
      }else{
        insert(croot.l, node);
      }
    }else{
      if(croot.r==null){
        croot.r=node;
        node.p = croot;
      }else{
        insert(croot.r, node);
      }
    }
    croot.h = (Math.max(croot.l!=null?croot.l.h:-1, croot.r!=null?croot.r.h:-1)+1);
  }
  
  public void inorder(){
    System.out.print("[IN]");
    inorder(root); 
    System.out.println();
  }
  
  private void inorder(Node croot){
    if(croot==null) return;
    if(croot.l!=null) inorder(croot.l);
    System.out.print(" "+croot.h+":"+croot.v+" ");
    if(croot.r!=null) inorder(croot.r);
  }
  
  public void preorder(){
    System.out.print("[PRE]");
    preorder(root);
    System.out.println();
  }
  
  private void preorder(Node croot){
    if(croot==null) return;
    System.out.print(" "+croot.h+":"+croot.v+" ");
    if(croot.l!=null) preorder(croot.l);
    if(croot.r!=null) preorder(croot.r);
  }
  
  public void postorder(){
    System.out.print("[POST]");
    postorder(root);
    System.out.println();
  }
  
  private void postorder(Node croot){
    if(croot==null) return;
    if(croot.l!=null) postorder(croot.l);
    if(croot.r!=null) postorder(croot.r);
    System.out.print(" "+croot.h+":"+croot.v+" ");
  }
  
  public int getMin(){
    return getMin(root).v;
  }
  
  private Node getMin(Node croot){
    if(croot==null) throw new RuntimeException("There is NO tree");
    Node temp = croot;
    while(temp.l!=null){
      temp = temp.l;
    }
    return temp;
  }
  
  public int getMax(){
    return getMax(root).v;
  }
  
  private Node getMax(Node croot){
    if(croot==null) throw new RuntimeException("There is NO tree");
    Node temp = croot;
    while(temp.r!=null){
      temp = temp.r;
    }
    return temp;
  }
  
  public Node find(int v){
    Node temp = root;
    while(temp!=null){
      if(temp.v==v) break;
      else if(v<=temp.v){
        temp = temp.l;
      }else{
        temp = temp.r;
      }
    }
    return temp;
  }
  
  public int delete(int v){
    Node temp = root;
    while(temp!=null){
      if(temp.v==v) break;
      else if(v<=temp.v){
        temp = temp.l;
      }else{
        temp = temp.r;
      }
    }
    // we got the node which we want to delete
    if(temp!=null){
      delete(temp);
    }
    return 0;
  }
  
  
  private void replaceNodeInParent(Node node, Node child){
    if(node.p==null){
      // we are at root
      this.root = child;
    }else{
      // non-root node
      if(node==node.p.l){
        node.p.l = child;
      }else {
        node.p.r = child;
      }
    }
    if(child!=null){
      child.p = node.p;
    }
    updateHeight(node.p);
  }
  
  private void updateHeight(Node node){
    if(node!=null){
      node.h = (Math.max(node.l!=null?node.l.h:-1, node.r!=null?node.r.h:-1)+1);
      updateHeight(node.p);
    }
  }
  
  private void delete(Node node){
    if(node.l!=null && node.r!=null){
      //have both child find successor in right or predecessor in left
      Node suc = getMin(node.r); 
      node.v = suc.v;
      delete(suc);
    }else if (node.l!=null){
      // have left child
      replaceNodeInParent(node, node.l);
    }else if(node.r!=null){
      // have right child
      replaceNodeInParent(node, node.r);
    }else{
      // have no child
      replaceNodeInParent(node, null);
    }
  }
  
  
  public static void main(String[] args){
    Random r = new Random();
    BalancedBinaryTree bbt = new BalancedBinaryTree();
    int size = 10;
    //Heap heap = new Heap(size, true);
    HeapWithAugment heap = new HeapWithAugment(size, true);
    int[] vals = new int[size];
    
    while(size-->0){
      int v = r.nextInt(10000);
      bbt.insert(v);
      heap.insert(v);
      vals[size]=v;
      bbt.preorder();
      heap.printHeap();
      System.out.println("Min="+bbt.getMin()+", Max="+bbt.getMax()+", H1="+heap.peek());
    }
    
    System.out.println("Done building up the tree");
    size=10;
    for(int i=size-1; i>=0; i--){
      System.out.println("Min="+bbt.getMin()+", Max="+bbt.getMax()+", Delete="+vals[i]+", H1="+heap.peek());
      bbt.delete(vals[i]);
      //heap.pop();
      heap.pop(vals[i]);
      bbt.preorder();
      heap.printHeap();
    }
  }
}

