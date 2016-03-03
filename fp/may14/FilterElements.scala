object FilterElements{
  def main(args:Array[String]){
    for( i <- 1 to readLine.toInt){
    	val List(n, k) = readLine.split(" ").map(_.toInt).toList
    	val list = readLine.split(" ").map(_.toInt).toList
    	.zipWithIndex
  	  .groupBy(x=>x._1)
  	  .map(x=>(x._1,x._2.map(_._2)))
  	  .filter(x=>x._2.length>=k)
  	  .map(x=>(x._2.head, x._1))
  	  .toList.sorted.map(x=>x._2).mkString(" ")
  	  println(if(list.isEmpty()) -1 else list)
    }
  }
}