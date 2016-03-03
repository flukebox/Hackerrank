package greedy

object CuttingBoards {

  def main(args:Array[String]): Unit ={
    val tc  = readLine().toInt
    (1 to tc).foreach(x =>{
      // y = m, x = n
      val Array(m, n)  = readLine.split(" ").map(_.toInt)
      val cy = readLine.split(" ").map(_.toLong).sorted.reverse
      val cx = readLine.split(" ").map(_.toLong).sorted.reverse
      val mod = 1000000007
      var i = 0
      var j = 0
      var cost = 0.toLong
      while(i < n-1 && j < m-1){
        if(cx(i) >= cy(j)){
          cost = (cost + cx(i)*(1+j))%mod
          i += 1
        }else{
          cost = (cost + cy(j)*(1+i))%mod
          j += 1
        }
      }

      while(i < n-1){
        cost = (cost + cx(i)*(1+j))%mod
        i += 1
      }

      while(j < m-1){
        cost = (cost + cy(j)*(1+i))%mod
        j += 1
      }
      println(cost)
    })
  }
}
