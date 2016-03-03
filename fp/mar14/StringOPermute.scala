package mar14

object StringOPermute{
  def main(args:Array[String]){
    for(tc <- 1 to readLine.toInt){
      val temp = readLine.toCharArray
      for( i <- 0 until temp.length/2){
        val t = temp(2*i+1)
        temp(2*i+1)=temp(2*i)
        temp(2*i)=t
      }
      println(temp.toList.mkString(""))
    }
  }
}