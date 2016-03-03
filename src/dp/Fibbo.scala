package dp

object Fibbo {

  def main(args:Array[String]): Unit ={
    val Array(a, b, n)  = readLine.split(" ").map(_.toInt)
    val series = Array.ofDim[BigInt](n)
    series(0)=a
    series(1)=b
    (2 until n).map( x =>{
      series(x) = series(x-1)*series(x-1)+series(x-2)
    })
    println(series(n-1))
  }
}
