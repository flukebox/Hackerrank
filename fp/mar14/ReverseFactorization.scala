/**

512000000 8
2 3 5 7 11 13 17 19

858000000 8
2 3 5 7 11 13 17 19

 */

package mar14

import scala.collection.mutable.Queue
import scala.Array.canBuildFrom
class Node(val v:Int, val parent:Node)

object ReverseFactorization{

	def main(args:Array[String]){
    val List(n, k) = readLine.split(" ").map( x=> x.toInt ).toList
    val as = readLine.split(" ").map( x => x.toInt ).toList.sorted
    solveBFS(n, as)
  }

	 def solveBFS(n:Int, as:List[Int]){
    val reached = scala.collection.mutable.Set[Int]()
    val queue =  new Queue[Node]
    queue.enqueue(new Node(1, null))
    while(!queue.isEmpty){
      val q = queue.dequeue
      for(a <- as) yield {
      	val cval = q.v*a 
        if( cval < n && n % cval == 0 && !reached.contains(cval)) {
        	reached += cval
          queue.enqueue( new Node(cval, q))
        }else if(cval == n) {
        	// trace to root
        	var sol = List(cval)
        	var root = q
        	while(root!=null){
        		sol = sol :+ root.v
        		root = root.parent
        	}
          println(sol.reverse.mkString(" "))
          return
        }
      }
    }
    println(-1)
  }

	def solve(n:Int, as:List[Int]){
		val queue =  new Queue[List[Int]]
		queue.enqueue(List(1))
    while(!queue.isEmpty){
    	val q = queue.dequeue
    	for( a <- as){
    		if(q.last*a < n && n % q.last*a == 0){
    			queue.enqueue( q :+ q.last*a)
    		}else if(q.last*a == n){
    		  println((q :+ q.last*a).mkString(" "))
    		  return
    		}
    	}
    }
		println(-1)
	}
	
  def lexiSmaller(x:List[Long], y:List[Long]):Boolean={
    for(i <- 1 to x.length-2){
      if(x(i)<y(i))return true;
    }
    return false;        
  }
  
  def primeFactors(n:Int):List[Int]={
    var pf = List[Int]();
    var d = 2;
    var _n= n;
    while(_n > 1){
      while( _n%d==0 ){
        pf=pf:+d;
        _n=_n/d;
      }
      d=d+1;
    }
    return pf;
  }  
}
