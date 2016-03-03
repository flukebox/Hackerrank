package week19

object Day1 {
	def main(args:Array[String]): Unit ={
    val edges = readLine.split(" ").map(_.toInt) zipWithIndex
    val cycle1 = edges.filter(e => Array(0, 3, 4).contains(e._2)).map(e => e._1).sum
    val cycle2 = edges.filter(e => Array(0, 1, 5).contains(e._2)).map(e => e._1).sum
    val cycle3 = edges.filter(e => Array(0, 1, 2, 3).contains(e._2)).map(e => e._1).sum
    val min = Array(cycle1, cycle2, cycle3).min

    val delta = min*(-1)
    if(delta<0) println(0)
    else
    if(Array(
    // check for a
    Array(cycle1+delta, cycle2+delta, cycle3+delta).forall(_>=0),
    // check for b
    Array(cycle1, cycle2+delta, cycle3+delta).forall(_>=0),
    // check for c
    Array(cycle1, cycle2, cycle3+delta).forall(_>=0),
    // check for d
    Array(cycle1+delta, cycle2, cycle3+delta).forall(_>=0),
    // check for e
    Array(cycle1+delta, cycle2, cycle3).forall(_>=0)).contains(true))  println(delta)
      else
        println(-1)
  }
}