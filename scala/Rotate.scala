import java.util.Scanner

object Rotate{
  
  def main(args:Array[String]){
    val sc = new Scanner(System.in)
    val n = sc.nextLine().toInt
    val nums = sc.nextLine().split(" ").map(x=>x.toLong).toList
    val sum = nums.reduce(_+_)
    val sum1 = nums.zip(1 to nums.length).map( x => x._1*x._2).reduce(_+_)
    val tos = nums.scanLeft(0L)(_+_)
    println(tos.zipWithIndex.map( x => sum1+n*x._1-x._2*sum).max)
  }

  def maxPMean(low:Int, high:Int, nums:List[Int], index:List[Int], cmean:Long):Long={
    if(high>=low){
      cmean
    }else{
    	val middle = (high + low)/2 
      nums.zip(rotate(middle, index)).map( x => x._1*x._2.toLong).reduce(_+_) match {
        case mean if mean > cmean => maxPMean(middle, high, nums, index, mean)
        case mean if mean < cmean => maxPMean(low, middle, nums, index, mean)
      }
    }
  }
  

  def rotate[T](i:Int, list:List[T]):List[T] = i match{
  	case i if i > 0 => list match {
  		case head :: xs => rotate(i-1, xs ::: List(head)) 
  		case Nil => list
  	}
  	case i if i < 0 => list.reverse match {
  		case head :: xs => rotate( i+1, head :: xs.reverse)
  		case Nil => list
  	}
  	case _ => list
  }
}



import java.util.Scanner

object Gems{
  def main(args:Array[String]){
    val sc = new Scanner(System.in)
    val stones = (1 to sc.nextInt()).map(_=>sc.next())
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
    println(histogram.filter(_ > stones.length).length)
  }
}