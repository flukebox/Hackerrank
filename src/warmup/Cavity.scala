package warmup

object Cavity {
	def main(args:Array[String]): Unit ={
    val n = readLine.toInt
    val map = Array.ofDim[Int](n, n)
    (0 until n).map( i =>  map(i) = readLine.trim.split("").filter(!_.isEmpty).map(_.toInt) )
    (0 until n).map( i => {
      println((0 until n).map( j => {
        if( (i > 0 && i < n-1) && (j > 0 && j < n-1) &&
          (map(i)(j) > map(i-1)(j)) && (map(i)(j) > map(i+1)(j))
            && (map(i)(j) > map(i)(j-1)) && (map(i)(j) > map(i)(j+1))
        ){
          "X"
        }else map(i)(j)
      }).mkString)
    })
  }
}