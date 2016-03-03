package week1
import java.util.Scanner

object Day4 {
  def main(args:Array[String]){
    val sc = new Scanner(System.in)
    val (n, q) = (sc.nextInt(), sc.nextInt())
    val chars = sc.next().toCharArray()
    for (tc <- 1 to q){
    	var i = sc.nextInt()
    	var c = sc.next().charAt(0)
    	chars(i-1)=c
    	val str = new String(chars)
    	val suffices = (0 until str.length).map( x => str.substring(x)).sorted
    	var distinct = (chars.length*(chars.length+1))/2
    	for( j <- 1 until str.length){
    	  distinct = distinct - suffices(j).zip(suffices(j-1)).takeWhile(Function.tupled(_ == _)).length
    	}
    	println(distinct)
    }
  }
}