package week3

import java.math.BigInteger
import scala.util.Random

object Day3{
  def main(args:Array[String]){
  	(1 to readLine.toInt).map(_ => {
  		val Array(n, k) = readLine.split(" ").map(_.toLong)
  		solve(n,k)
  	}).foreach(println)
  }
  
  def solve(n:Long, k:Long):String={
  	if (n==1 || k > n/2 || (k==1 && !prime(n)) || (n%2==1 && k==2 && !prime(n-2))) "No"
  	else if (n%2==1 && k>=3) solve(n-3, k-1)
  	else "Yes" 
  }
  
  def prime(n:Long):Boolean={
    new BigInteger(""+n).isProbablePrime(2000)
  }
}
