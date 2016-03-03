package warmup

object LoveLetter {
	def main(args:Array[String]){
	  (1 to readLine.toInt).foreach( _ => {
	  	val x = readLine
	    println(x.zip(x.reverse).map( e => Math.abs(e._1.toInt - e._2.toInt)).sum/2)
	  })
	}
}