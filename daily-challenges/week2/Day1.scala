package week2

object Day1 {
	def main(args:Array[String]){
    val tc = readInt
    (1 to tc).toList.foreach(x=>{
      val n = readInt
      val a = readInt
      val b = readInt
      var nums = scala.collection.immutable.SortedSet[Int]()
      (1 to n-1).toList.foreach(x=>nums += a*x+(n-1-x)*b)
      println(nums.mkString(" "))
    })
	}
}