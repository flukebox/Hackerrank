package warmup

object DiagonalSum {
	def main(args:Array[String]): Unit ={
		val n = readLine.toInt
    val tup = (0 until n).map(i => {
      val row = readLine.split(" ").map(_.toInt)
      (row(i), row(n-i-1))
    }).foldLeft((0, 0))((c, e) => (c._1+e._1, c._2+e._2))
    println(Math.abs(tup._2-tup._1))
	}
}