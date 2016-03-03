package week3
object Day1{
  def main(args:Array[String]){
  (1 to readLine.toInt).map( _ => {
      val carr = readLine.toCharArray()
      val n = carr.length
      (0 to n/2 - 1).map( x => math.abs(carr(x)-carr(n-x-1))).sum
    }).foreach(println)
  }
}
