package warmup

object SherlockSquares {
	def main(args:Array[String]){
		val n = readLine.toInt
		val sqrs = (1 to 4*10000).map( x => x*x)
		(1 to n).map( x => {
			val Array(a, b) = readLine.split(" ").map(_.toInt)
			var low = binarySearch(sqrs, a, 0, sqrs.length-1)
			var high = binarySearch(sqrs, b, 0, sqrs.length-1)
			if ( low < 0 )  low = (-low-1)
			if ( high < 0)  high = (-high-1)-1
	    println(high - low +1)
			
		})		
	}
	
	def binarySearch(seq:IndexedSeq[Int], x:Int, start:Int, end:Int):Int={
		(start, end, (start+end)/2) match {
      case (s, e, mid) if (s > e)  => -start-1
			case (s, e, mid) if (x > seq(mid)) => binarySearch(seq, x, mid+1, end)
			case (s, e, mid) if (x < seq(mid)) => binarySearch(seq, x, start, mid-1)
			case (_, _, mid) => mid
		}
	}
}