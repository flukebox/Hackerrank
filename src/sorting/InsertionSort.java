package sorting;
import java.util.Scanner;

public class InsertionSort {
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int tc = sc.nextInt();
    int N = 100001;
    while(tc-->0){
      int n = sc.nextInt();
      int[] ar = new int[n];
      int[] aux = new int[N];
      int total = 0;
      long count = 0;
      for(int i=0;i<n;i++){
        ar[i]=sc.nextInt(); 
        count+=(total-getBIT(aux, ar[i]));
        updateBIT(aux, ar[i],1);
        total++;
      }
      System.out.println(count);
    }
    sc.close();
  }    
  
  public static void updateBIT(int[] aux, int index, int value){
    int k = index;
    while(k<=aux.length){
      aux[k]+=value;
      k +=(k&-k);
    }
  }

  public static long getBIT(int[] aux, int index){
    int k = index;
    long ret=0;
    while(k>=1){
      ret+=aux[k];
      k -=(k&-k);
    }
    return ret;
  }

  
/********************************* Merge Sort ****************************************************/
  /* array A[] has the items to sort; array B[] is a work array */
  public static long bottomUpSort(int n, int A[], int B[]){
    long count = 0;
    for (int w=1; w < n; w*=2){
      for (int i = 0; i < n; i+=2*w){
        count += bottomUpMerge(A, i, Math.min(i+w, n), Math.min(i+2*w, n), B);
      }
      System.arraycopy(B, 0, A, 0, n);
    }
    return count;
  }
  
  public static long bottomUpMerge(int A[], int l, int r, int e, int B[]){
    long count = 0;
    for (int j=l, i0=l, i1=r; j<e; j++){
      if (i0 < r && (i1 >= e || A[i0] <= A[i1])){ B[j] = A[i0++];}
      else{ count+=(r-i0); B[j] = A[i1++];}
    }
    return count;
  }
/********************************* Merge Sort ****************************************************/

/************************************ Binary Search Tree -- DUMB *********************************/
  public static void main1(String[] args) {
    Scanner sc = new Scanner(System.in);
    int tc = sc.nextInt();
    while(tc-->0){
      int n = sc.nextInt();
      int[] ar = new int[n];
      Node root = null;
      int count = 0;
      for(int i=0;i<n;i++){
        ar[i]=sc.nextInt(); 
        count += getGtCount(root, ar[i]);
        if(root==null){
          root = new Node();
          root.v = ar[i];
        }else{
          Node node = new Node();
          node.v = ar[i];
          insert(root, ar[i], node);
        }
      }
      System.out.println(count);
    }
    sc.close();
  }    

  static class Node{
    int v = -1;
    int lc = 0, rc = 0;
    Node lchild, rchild;
  }

  static int getGtCount(Node root, int a){
    if(root==null) return 0;
    if(root.v==a) return root.rc;
    if(a<root.v){
      return root.rc+1+getGtCount(root.lchild, a);
    }else{
      return getGtCount(root.rchild, a);
    }
  }

  
  static void insert(Node root, int a, Node node){
    if(a<=root.v){
      if(root.lchild!=null){
        insert(root.lchild, a, node);
      }else{
        root.lchild = node;
      }
      root.lc++;
    }else{
      if(root.rchild!=null){
        insert(root.rchild, a, node);
      }else{
        root.rchild = node;
      }
      root.rc++;
    }
  }

/************************************ Binary Search Tree -- DUMB *********************************/

}
