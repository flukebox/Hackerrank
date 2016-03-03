package math

object BdayGift {
  def main(args:Array[String]){
        println(format("%.1f", ((1 to readInt).map(_ => readDouble).sum * 0.5)))
  }
}