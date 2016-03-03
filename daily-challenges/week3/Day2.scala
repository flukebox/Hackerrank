package week3

object Day2{
  val m = 1000000007
  val pi = Array.fill[Long](200001)(1)
  var temp = 1L
  for(i<- 1 to 200000){
    temp = (temp*10)%m
    pi(i) = (pi(i-1)+temp)%m
  }
  def main(args:Array[String]){
    val nums = readLine.toCharArray().map(x=>(x-'0').toInt).zipWithIndex
    println(nums.map( x => (((x._1*(x._2+1))%m)*(pi(nums.length-x._2-1)))%m).reduce((x,y)=>(x+y)%m))
  }
}

