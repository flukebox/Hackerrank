package warmup

object StairCase {
	def main(args:Array[String]): Unit ={
    val n = readLine.toInt
    (1 to n).map{ i => println(" "*(n-i)+"#"*i)}
 	}
}