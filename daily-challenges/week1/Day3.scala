package week1


import java.util.Scanner
import scala.collection.mutable.Map
import scala.collection.generic.Sorted

object Day3 {
  val MOD = 1000000007
  def main(args:Array[String]){
    
  	val sc = new Scanner(System.in)
    val (n, p)= (sc.nextInt(), sc.nextInt())
    val brackets = Map[Int, Int]()
    for( i <- 1 to math.sqrt(p).toInt){
      brackets += (i -> 1)
      brackets +=(p/i -> (p/i - ((p/(i+1).toDouble).floor.toInt+1) +1))
    }
    val size = brackets.size
    val base = brackets.toList.sortWith((x1, x2) => x1._1 < x2._1).map(x=> x._2)
    var current = new Array[Int](size)
    var old = new Array[Int](size)
    var sum = 0L
    var total = 0L
    for( i <- 2 to n){
    	for ( j <- 1 to size){
    	  current(size-j) = (if(i==2) base(j-1) else old(j-1))
    	}
    	total = 0L
    	sum = 0L
      for ( j <- 1 to size){
      	total = (total.toLong + current(size-j))%MOD
        current(size-j) = ((total*base(size-j))%MOD).toInt
        sum = (sum+current(size-j))%MOD
      }
    	val temp = old
    	old = current
    	current = temp
    }
    println(sum)
  }
}