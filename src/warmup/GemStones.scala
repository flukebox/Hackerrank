package warmup

object GemStones {
  def main(args:Array[String]){
    val stones = (1 to readLine.toInt).map(_=>readLine)
    val histogram = new Array[Int](26);
    val histos = stones.map( x=>{
      val hist = new Array[Int](26);
      for(c<-x){
        hist(c-'a')=1
      }
      hist
    })
    histos.map(hist => {
      for(i <- 0 until 26){
        if(hist(i)==1) histogram(i)+=1
      }
    })
    println(histogram.filter(_ ==stones.length).length)
  }
}