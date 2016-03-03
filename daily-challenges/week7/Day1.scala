package week7

object Day1 {
	def main(args:Array[String]){
	  (1 to readLine.toInt).map( _  => {
	    val Array(a, b, c) = readLine.split(" ").map(_.toInt)
	    if ((( c > a ) && ( c > b)) || c % gcd(a, b)!=0){
	      println("NO")
	    }else{
	      println("YES")
	    }
	  })
	}
	def gcd(a: Int, b: Int): Int = if (b == 0) a.abs else gcd(b, a % b)
}