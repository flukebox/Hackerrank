package dp

object ArraySum {

  def main(args:Array[String]): Unit ={
    val tc  = readLine().toInt
    (1 to tc).foreach(x =>{
      val n  = readLine.toInt
      val elements = readLine.split(" ").map(_.toInt)
      val maxNC = if(elements.filter(_>=0).isEmpty) elements.max else elements.filter(_>=0).sum
      var maxC = elements(0)
      elements.slice(1, elements.length).foldLeft(elements(0))((currSum, e) => {
          val sum = if ( currSum <= 0 && currSum < e) e else if (currSum + e >= 0) currSum + e else e
          if(sum > maxC) maxC = sum
          sum
      })
      println(maxC+" "+maxNC)
    })
  }
}
