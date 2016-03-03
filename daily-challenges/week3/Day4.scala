package week3

object Day4{
	
	def main(args:Array[String]){
  	(1 to readLine.toInt).map( _ => solve(readLine.toInt)).foreach(println)
  } 
	
  def solve(n:Int):Int={
  	if((n&1)==1) 1
  	else{
  	  val nim = Integer.SIZE - Integer.numberOfLeadingZeros(n)
  	  val xor = 1^nim
  	  var ans = Int.MaxValue
  	  for(i <- 1 to nim){
  	  	val oV = 1 << (i-1)
  	  	if((xor^i) < i){
  	  		val nnim = xor ^ i
  	  		val nV = nnim match {
  	  			case 0 => 0
  	  			case x if(i-x)==1 => 1<<(x-1)
  	  			case x => (1<<x)-1
  	  		}
  	  		return (oV-nV)
  	  	}
  	  }
  	  ans
  	}
  }
}
