package week7

object Day2 {
	def main(args:Array[String]){
    def main(args:Array[String]){
        val n = readLine.toInt
        val data = readLine.split(" ").map(_.toInt)
        val xor = data.fold(0)(_^_)
        println(data.map(x =>(x - (x^xor))).filter( _ > 0).size)
    }   
	}
}