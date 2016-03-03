package dp

object CoinChanges {
  def main(args:Array[String]): Unit ={
    val Array(n, m)  = readLine.split(" ").map(_.toInt)
    val coins = readLine.split(" ").map(_.toInt).sorted
    val changes = Array.ofDim[Long](n+1, m)
    changes(0) = changes(0).map( _ => 1.toLong)
    (1 to n).map( i =>{
      (0 until m).map( j =>{
        val x =  if(i-coins(j)>=0) changes(i-coins(j))(j) else 0
        val y =  if(j-1>=0) changes(i)(j-1) else 0
        changes(i)(j) = x+y
      })
    })
    println(changes(n)(m-1))
  }
}
