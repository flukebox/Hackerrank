package math

object SummingNSeries {
  val m = 1000000007
    def main(args:Array[String]){
    (1 to readInt).map(_ => {
      val n = readLong % m
          (n*n)%m
    }).foreach(println)
  }
}