import java.util.Scanner;

class COT5 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Treap treap = new Treap();
    int n = Integer.parseInt(sc.nextLine());
    while(n-->0){
      String[] temp = sc.nextLine().split(" ");
      switch(Integer.parseInt(temp[0])){
        case 0:{
          treap.add(Long.parseLong(temp[1]), Long.parseLong(temp[2]));
          break;
        }
        case 1:{
          treap.delete(Long.parseLong(temp[1]));
          break;
        }
        case 2:{
          System.out.println(treap.getPathLen(Long.parseLong(temp[1]), Long.parseLong(temp[2])));
          break;
        }
      }
    }
    sc.close();
  }
  
  static class Node{
    long k, w;
    Node(long k_, long w_){this.k=k_; this.w=w_;}
    Node left, right, parent;
    @Override
    public String toString() {
      return "[{h="+hashCode()+", k="+k+", w="+w+", P="+(parent!=null?parent.hashCode():parent)
          +", L="+(left!=null?left.hashCode():left)
          +", R="+(right!=null?right.hashCode():right)+"}] ";
    }
  }

  static class Treap{
    Node root;
    void inorder(Node croot){
      if(croot==null) return;
      if(croot.left!=null) inorder(croot.left);
      System.out.println(croot);
      if(croot.right!=null) inorder(croot.right);
    }
    void add(long k, long w){
      Node node = new Node(k,w);
      if(root==null){
        root=node;
      }else{
        add(root, node);
      }
      // heapify
      heapify(node);
    }
    
    long getPathLen(long k1, long k2){
      Node temp1 = root, temp2=root;
      long l1=0, l2=0, cl=0;
      while(temp1.k!=k1 || temp2.k!=k2){
        if(k1<temp1.k){
          temp1 = temp1.left; l1++;
        }else if(k1>temp1.k){
          temp1 = temp1.right; l1++;
        }
        if(k2<temp2.k){
          temp2 = temp2.left; l2++;
        }else if(k2>temp2.k){
          temp2 = temp2.right; l2++;
        }
        if(temp1==temp2)cl++;
      }
      return l1+l2-2*cl;
    }
    
    void delete(long k){
      Node temp = root;
      while(temp!=null){
        if(temp.k==k) break;
        else if(k<temp.k){
          temp = temp.left;
        }else{
          temp = temp.right;
        }
      }
      // we got the node which we want to delete
      if(temp!=null){
        delete(temp);
      }
    }
    
    private void replaceNodeInParent(Node node, Node child){
      if(node.parent==null){
        // we are at root
        this.root = child;
      }else{
        // non-root node
        if(node==node.parent.left){
          node.parent.left = child;
        }else {
          node.parent.right = child;
        }
      }
      if(child!=null){
        child.parent = node.parent;
      }
    }
    
    private Node getMin(Node croot){
      Node temp = croot;
      while(temp.left!=null){
        temp = temp.left;
      }
      return temp;
    }
    
    private void delete(Node node){
      if(node.left!=null && node.right!=null){
        //have both child find successor in right or predecessor in left
        Node suc = getMin(node.right); 
        node.k = suc.k;
        node.w = suc.w;
        heapify(node);
        delete(suc);
      }else if (node.left!=null){
        // have left child
        replaceNodeInParent(node, node.left);
      }else if(node.right!=null){
        // have right child
        replaceNodeInParent(node, node.right);
      }else{
        // have no child
        replaceNodeInParent(node, null);
      }
    }
    
    private void add(Node curr, Node node){
      if(curr.k>node.k){
        // left 
        if(curr.left!=null){
          add(curr.left, node);
        }else{
          curr.left=node;
          node.parent=curr;
        }
      }else{
        // right
        if(curr.right!=null){
          add(curr.right, node);
        }else{
          curr.right=node;
          node.parent=curr;
        }
      }
    }
    
    private void heapify(Node node){
      if(node.parent!=null && node.w>node.parent.w){
        if(node.parent.left==node){
          // node is left child of parent
          if(node.parent.parent==null){
            // node's parent is root 
            node.parent.left = node.right;
            if(node.right!=null) node.right.parent = node.parent;
            node.right = node.parent;
            node.parent = null;
            node.right.parent = node;
            root = node;
            return;
          }else if(node.parent.parent.left==node.parent){
            // node's parent is left child
            node.parent.parent.left=node;
            node.parent.left = node.right;
            if(node.right!=null) node.right.parent = node.parent;
            node.right = node.parent;
            node.parent = node.parent.parent;
            node.right.parent = node;
          }else{
            // node's parent is right child
            node.parent.parent.right=node;
            node.parent.left = node.right;
            if(node.right!=null) node.right.parent = node.parent;
            node.right = node.parent;
            node.parent = node.parent.parent;
            node.right.parent = node;
          }
        }else{
          // else right child of parent
          if(node.parent.parent==null){
            // node's parent is root 
            node.parent.right = node.left;
            if(node.left!=null) node.left.parent = node.parent;
            node.left = node.parent;
            node.left.parent = node;
            node.parent = null;
            root = node;
            return;
          }else if(node.parent.parent.left==node.parent){
            // node's parent is left child
            node.parent.parent.left=node;
            node.parent.right = node.left;
            if(node.left!=null) node.left.parent = node.parent;
            node.left = node.parent;
            node.parent = node.parent.parent;
            node.left.parent = node;
          }else{
            // node's parent is right child
            node.parent.parent.right=node;
            node.parent.right = node.left;
            if(node.left!=null) node.left.parent = node.parent;
            node.left = node.parent;
            node.parent = node.parent.parent;
            node.left.parent = node;
          }
        }
        heapify(node);
      }
    }
  }
}
