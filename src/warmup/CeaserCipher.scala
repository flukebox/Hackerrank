package warmup

object CeaserCipher {
	def main(args:Array[String]): Unit ={
    val n = readLine.toInt
    val uen = readLine
    val k = readLine.toInt%26
    println(uen.map{
      case x if x >= 97 && x <= 122 => if ((x+k) >122) 97+(x+k)%122 else x+k
      case x if x >= 65 && x <= 90 => if ((x+k) > 90) 65+(x+k)%90 else x+k
      case x => x
    }.map(_.toChar).mkString)
  }
}