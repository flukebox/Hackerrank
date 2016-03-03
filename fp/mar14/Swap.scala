package mar14

import java.util.Scanner

object Swap {
  def main(args:Array[String]){
    val sc = new Scanner(System.in);
    val n = sc.nextInt();
    val nodes = new Array[Node](n+1);
    val depthWise = Array.fill[List[Int]](n+1)(List());
    nodes(1)=new Node(1, 1, null, null);
    depthWise(1)=depthWise(1):+1;
    var maxDepth = 1;
    for(i <- 1 to n){
      val l = sc.nextInt();
      val r = sc.nextInt();
      val d = nodes(i).depth+1;
      if(l != -1){
        nodes(l)=new Node(l, d, null, null);
        nodes(i).addLeft(nodes(l));
        depthWise(d) = depthWise(d) :+ l; 
        maxDepth = d;
      }
      if(r != -1){
        nodes(r)=new Node(r, d, null, null);
        nodes(i).addRight(nodes(r));
        depthWise(d) = depthWise(d) :+ r; 
        maxDepth = d;
      }
    }
    
    for(i <- 1 to sc.nextInt()){
      val k = sc.nextInt();
      var ck = k;
      while(ck<=maxDepth){
        depthWise(ck).foreach(x => nodes(x).swap());
        ck = ck + k;
      }
      printInOrder(nodes(1));
      println();
    }
    sc.close();
  }
  
  class Node(v:Int, d:Int, l:Node, r:Node){
    val value = v;
    var left = l;
    var right = r;
    val depth = d;
    def swap(){
      val temp = left;
      left = right;
      right = temp;
    }
    def addRight(r:Node){right = r;}
    def addLeft(l:Node){left = l;}
    
  }
  
  def printInOrder(node:Node){
    if(node != null){
      printInOrder(node.left);
      print(node.value+" ");
      printInOrder(node.right);
    }
  }
}