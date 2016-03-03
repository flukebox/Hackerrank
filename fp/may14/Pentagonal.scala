object Pentagonal{
  val pn = new Array[Long](100001)
  pn(1)=1
  for(i <- 2 to 100000){
    pn(i)=pn(i-1)+4+(i-2)*3
  }
  def main(args:Array[String]){
    for( i <- 1 to readLine.toInt){
	    println(pn(readLine.toInt))
    }
  }
}