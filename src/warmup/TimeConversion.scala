package warmup

object TimeConversion {
	def main(args:Array[String]): Unit ={
    val time = readLine
    time match {
      case "12:00:00AM" => println("00:00:00")
      case "12:00:00PM" => println("12:00:00")
      case _ =>
        val times = time.substring(0, time.length-2).split(":")
        println((time.substring(time.length-2) match {
          case "AM" => if(times(0).toInt == 12) "00" else  times(0)
          case "PM" => if(times(0).toInt == 12) "12" else  times(0).toInt+12
        }) +":"+times.slice(1, 3).mkString(":") )
    }
 	}
}