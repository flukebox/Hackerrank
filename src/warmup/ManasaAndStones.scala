package warmup

object ManasaAndStones {
	def main(args:Array[String]){
		val testcases = readLine.toInt
		(0 until testcases).map( _ => {
			val n = readLine.toInt
			val a = readLine.toInt
			val b = readLine.toInt
			val finalvalues = (0 until n).map( x => x*a+(n-1-x)*b).distinct.sorted.toList.mkString(" ")
			println(finalvalues)
		})
	}
}