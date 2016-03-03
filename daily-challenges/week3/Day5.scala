package week3
object Day5{
  val M = 1000000007
  val N = 15000000
	def main(args:Array[String]){
		val Array(n, m) =  readLine.split(" ").map(_.toLong)
    val primes = Array.fill[Boolean](N+1)(true)
		buildPrimes(primes)
    val min = math.min(n, m)
    var ans:Long = 1L
    for( p <- 2 to min.toInt){
    	if(primes(p)){
        var pow:Long = 0L
        var cp:Long = p
        while((n/cp)*(m/cp)>0){
          pow += (n/cp)*(m/cp)
          cp *=p
        }
      	ans = (ans*modPower(p, pow))%M
    	}
    }
  	println(ans)
  } 
  
  def buildPrimes(primes:Array[Boolean]) {
  	primes(0) = false
  	primes(1) = false
    for (i <- 2 until primes.length)
      if (primes(i))
        for (j <- new Range(2*i, primes.length, i))
          primes(j) = false;
  }

  def modPower(base:Long, pows:Long):Long={
  	var pow = pows%(M-1)
    var t:Long = 1L;
    var p:Long = base;
    while(pow >= 1) {
      if((pow & 1) == 1) t = (t * p) % M
      p = (p * p) % M
      pow = pow >>> 1
    }
    t
  }  
}
