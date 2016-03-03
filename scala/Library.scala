import java.util.Scanner
import scala.collection.immutable.List
import java.util.Collections
import java.util.{List => JList}

object Library{
  def main(args:Array[String]){
    val sc = new Scanner(System.in);
    for(tc <- 1 to sc.nextLine().toInt){
      val n = sc.nextLine().toInt;
      val books = sc.nextLine().split(" ").map(x=>Integer.parseInt(x));
      val aux = new Array[List[Int]](4*n);
      // build Our Range Minimum Queries
      buildRMQ(books, aux, 1, n, 1);
      for(nq <- 1 to sc.nextLine().toInt){
        val query = sc.nextLine().split(" ").map(x=>Integer.parseInt(x));
        query(0) match {
          case 0 => {
            System.out.println(getKth(queryRMQ(aux, 1, n, 1, query(1), query(2)), query(3)));
          }
          case 1 => {
            updateRMQ(aux, 1, n, 1, query(1), books(query(1)-1), query(2));
          }
        }
      }
    }
  }
  
  import _root_.scala.collection.JavaConversions._
  import java.util.{Collections, List => JList}
  class SearchableSeq[T](a: Seq[T])(implicit ordering: Ordering[T]) {
    val list: JList[T] = a.toList;
    def binarySearch(key: T): Int = Collections.binarySearch(list, key, ordering);
  }
  implicit def seqToSearchable[T](a: Seq[T])(implicit ordering: Ordering[T]) = new SearchableSeq(a)(ordering);
  
  /**
   * Return Kth rank element from sorted list 
   */
  def getKth(sortedList:Array[List[Int]], k:Int):Int={
  	var lists = Array[List[Int]]();
  	var maxLenIdx = 0; var maxLen = 0;
    for(i <- 0 to sortedList.length-1){
    	val list = sortedList(i).slice(0, k);
    	if(!list.isEmpty){
    		lists=lists++Array(list);
        if(maxLen<list.length){
          maxLen = list.length;
          maxLenIdx = lists.size-1;
        }
    	}
    }
    if(maxLen<=1){
      val finallist = lists.flatten.sorted;
      return finallist(k-1);
    }
    val middle = lists(maxLenIdx)(maxLen/2);
    var lowerHalfs = Array[List[Int]]();
    var upperHalfs = Array[List[Int]]();
    var lowerLen = 0;
    for(i <- 0 to lists.length-1){
      var insertIdx = lists(i).binarySearch(middle);
      if(insertIdx<0) insertIdx = -insertIdx - 1;
      val lower = lists(i).slice(0,insertIdx);
      val upper = lists(i).slice(insertIdx, lists(i).length);
      if(!lower.isEmpty){
      	lowerHalfs=lowerHalfs++Array(lower);
        lowerLen += lower.length;
      }
      if(!upper.isEmpty){
        upperHalfs=upperHalfs++Array(upper);
      }
    }
    
    if(lowerLen==k-1) return middle;
    else if(lowerLen>=k) return getKth(lowerHalfs, k);
    else return getKth(upperHalfs, k-lowerLen);
  }
  
  def buildRMQ(data:Array[Int], aux:Array[List[Int]], start:Int, end:Int, index:Int){
    if(start==end){
      aux(index) = List(data(start-1));
    }else{
      val mid = (start+end)>>1; val left = index<<1; val right = left|1;
      buildRMQ(data, aux, start, mid, left);
      buildRMQ(data, aux, mid+1, end, right);
      aux(index)=(aux(left):::aux(right)).sorted;
    }
  }
  
  def updateRMQ(aux:Array[List[Int]], start:Int, end:Int, index:Int, idx:Int, prevalue:Int, value:Int){
    if(start==end && start==idx){
      aux(index) = List(value);
    }else if(start<end){
      val mid = (start+end)>>1; val left = index<<1; val right = left|1;
      updateRMQ(aux, start, mid, left, idx, prevalue, value);
      updateRMQ(aux, mid+1, end, right, idx, prevalue, value);
      var insertIdx = aux(index).binarySearch(prevalue);
      aux(index) = aux(index).slice(0, insertIdx):::aux(index).slice(insertIdx+1, aux(index).length);
      insertIdx = aux(index).binarySearch(value);
      if(insertIdx==0){
        aux(index)= value +: aux(index);
      }else if(insertIdx == aux(index).length){
        aux(index)=aux(index) :+ value;
      }else{
      	if(insertIdx<0) insertIdx = -insertIdx - 1;
        aux(index)= (aux(index).slice(0,insertIdx):+value):::aux(index).slice(insertIdx, aux(index).length);
      }
    }
  }
  
  def queryRMQ(aux:Array[List[Int]], start:Int, end:Int, index:Int, qstart:Int, qend:Int):Array[List[Int]]={
      if(end<qstart || qend<start) Array(List[Int]());
      else if(qstart<=start && end<=qend) Array(aux(index));
      else{
        val mid = (start+end)>>1; val left = index<<1; val right = left|1;
        queryRMQ(aux, start, mid, left, qstart, qend)++queryRMQ(aux, mid+1, end, right, qstart, qend);
      }
  }
}