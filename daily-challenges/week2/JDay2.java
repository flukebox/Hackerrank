package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

public class JDay2 {
    static class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;
    static void init(InputStream input) {
      reader = new BufferedReader(new InputStreamReader(input));
      tokenizer = new StringTokenizer("");
    }
    
    static String next() throws IOException{
      while (!tokenizer.hasMoreTokens()){
        tokenizer = new StringTokenizer(reader.readLine());
      }
      return tokenizer.nextToken();
    }
    
    static long nextLong() throws IOException {
      return Long.parseLong( next() );
    }
    
    static int nextInt() throws IOException {
      return Integer.parseInt( next() );
    }

    static double nextDouble() throws IOException {
      return Double.parseDouble( next() );
    }
  }
  
  public static void main(String[] args) throws IOException {
    Reader.init(System.in);
    int n = Reader.nextInt();

    Node[] nodes = new Node[n+1];
    int[] weights = new int[n+1];

    for(int i=0; i< n ; i++){
      weights[i+1] = Reader.nextInt();
    }

    for(int i=1; i< n ; i++){
      int id1 = Reader.nextInt();
      int id2 = Reader.nextInt();
      if(nodes[id1]==null) nodes[id1] = new Node(weights[id1], id1);
      if(nodes[id2]==null) nodes[id2] = new Node(weights[id2], id2);
      nodes[id1].addC(nodes[id2]);
      nodes[id2].addC(nodes[id1]);
    }
    
    Node root = nodes[1];
    setWeights(root, n);
    System.out.println(getMinDiff(root, Integer.MAX_VALUE, 0));

  }
  
  static int getMinDiff(Node node, int min, int total){
    Node temp = node;
    while(min >= Math.abs(temp.wt+total-2*temp.max)){
      min=Math.abs(temp.wt+total-2*temp.max);
      total = total+temp.wt-temp.max;
      temp = temp.getM();
    }
   return min;
  }

  static void setWeights(Node node, int n){
    Stack<Node> stack = new Stack<Node>();
    Queue<Node> queue = new LinkedList<Node>();
    boolean[] visited = new boolean[n+1];
    queue.add(node);
    while(!queue.isEmpty()){
      Node temp = queue.remove();
      if(!visited[temp.idx]){
        visited[temp.idx]=true;
        boolean leaf = true;
        for(Node c:temp.getC()){
          leaf &=visited[c.idx];
          if(c.parent==null){
            // set parent
            c.setParent(temp);
            // remove from child
            c.getC().remove(temp);
          }
        }
        if(leaf){
          temp.wt = temp.val;
        }else{
          stack.push(temp);
        }
        queue.addAll(temp.getC());
      }
    }

    while(!stack.isEmpty()){
      Node temp = stack.pop();
      temp.setWt();
    }    
  }  
}

class Node{
  int val, idx, wt, maxIdx=-1, max=-1;
  private List<Node> childs = new ArrayList<Node>();
  private Node maxChild = null;
  Node parent = null;
  
  Node(int v, int i){
    val = v; idx = i;
  }
  
  void setParent(Node node){
    parent = node;
  }
  
  Node getM(){
    return maxChild;
  }
  
  void setWt(){
    wt = val;
    for(Node c:childs){
      assert c.parent==this;
      wt += c.wt;
      if(max < c.wt){
        max = c.wt;
        maxChild = c;
      }
    }
  }
  
  List<Node> getC(){
    return childs;
  }
  
  void addC(Node node){
    childs.add(node);
  }
}


