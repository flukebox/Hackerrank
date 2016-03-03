package sorting

object GameOfRotation {
  def main(args:Array[String]){
    val n = readLine.toInt
    val nums = readLine.split(" ").map(x=>x.toLong).toList
    val sum = nums.reduce(_+_)
    val sum1 = nums.zip(1 to nums.length).map( x => x._1*x._2).reduce(_+_)
    val tos = nums.scanLeft(0L)(_+_)
    println(tos.zipWithIndex.map( x => sum1+n*x._1-x._2*sum).max)
  }
}