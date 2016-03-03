package warmup

object LibraryFine {
	def main(args:Array[String]): Unit ={
    val Array(d1, m1, y1) = readLine.split(" ").map(_.toInt)
    val Array(d2, m2, y2) = readLine.split(" ").map(_.toInt)
    println(((d1, m1, y1), (d2, m2, y2)) match {
      case _ if y1 <= y2 && m1 <= m2 && d1 < d2 => 0
      case _ if y1 == y2 && m1 == m2 => (d1-d2)*15
      case _ if y1 == y2 => (m1-m2)*500
      case _ => 10000
    })
  }
}