package week7

object Day3 {
  def main(args:Array[String]){
    val str = readLine
    println(str.inits.flatMap(_.tails.toList.init).map( x => x.size*( x allIn str).size).max)
  }
  implicit class AllIn(val s: String) extends AnyVal{
    def allIn(t: String): List[String] = (
        for (m <- s"(${s.head})(?=(${s.tail}))".r.findAllMatchIn(t))
          yield (m group 1) + (m group 2)
        ).toList
  }
}