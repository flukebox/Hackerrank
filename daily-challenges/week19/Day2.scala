package week19

object Day2 {
  import Math.{min, abs}
	def main(args:Array[String]){
    val tc  = readLine().toInt
    (1 to tc).foreach(x =>{
      val List(m, n) = readLine().split(" ").map(_.toInt).toList
      val mv = new Array[(Int, Int)](n+1)
      (1 to n).foreach( i=> {
        val Array(x, y) = readLine().split(" ").map(_.toInt)
        mv(i) = (x, y)
      })
      val DP = Array.ofDim[Int](2, m+1)
      DP(1) = DP(0).map(x =>  Integer.MAX_VALUE)
      DP(0) = DP(0).map(x => abs(mv(1)._1 - mv(1)._2))
      (2 to n).foreach( i => {
        (1 to m).foreach( j =>{
          DP(1)(mv(i-1)._2) = min( DP(1)(mv(i-1)._2),  DP(0)(j)+abs(j-mv(i)._1)+abs(mv(i)._1-mv(i)._2))
          DP(1)(j) = min(DP(1)(j), DP(0)(j)+abs(mv(i-1)._2-mv(i)._1)+abs(mv(i)._1-mv(i)._2))}
        )
        DP(0) = DP(1).clone()
        DP(1) = DP(1).map(x => Integer.MAX_VALUE )
      })
      println(DP(0).fold(Integer.MAX_VALUE)((a, b) => min(a, b)))
    })
	}
}