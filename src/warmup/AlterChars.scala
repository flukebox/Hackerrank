package warmup

object AlterChars {
	def main(args:Array[String]){
	  (1 to readLine.toInt).foreach( _ => {
	  	println(readLine.zipWithIndex.foldLeft(('_', 0))((p, c) => {
	  			(c._1,  if( p._1 == c._1 ) (p._2+1) else p._2)
	  	})._2)
	  })
	}
}