package warmup

object PlusMinus {
	def main(args:Array[String]): Unit ={
    readLine
    val row = readLine.split(" ").map(_.toInt)
    val len = row.length.toDouble
    println(row.filter( _ > 0).length.toDouble/len)
    println(row.filter( _ < 0).length.toDouble/len)
    println(row.filter( _ == 0).length.toDouble/len)
	}
}