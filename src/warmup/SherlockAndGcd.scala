package warmup

object SherklockAndGcd {
	def main(args:Array[String]){
		(1 to readLine.toInt).map( _ => {
		  val n = readLine.toInt
		  println( if (readLine.split(" ").map(_.toInt).toList.fold(0)((a, b) => gcd(a, b)) > 1)  "NO"  else "YES")
		})
	}
	def gcd(a:Int, b:Int):Int = if (b == 0) a.abs else gcd(b, a%b)
  def lcm(a:Int, b:Int):Int = (a*b).abs/gcd(a,b)
}