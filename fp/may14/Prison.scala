
object Prison{
	def readEdges(m:Int, edges:Map[Int, List[Int]]):Map[Int, List[Int]]={
		if(m==0) edges
		else {
		  val List(n1, n2) = readLine.split(" ").map(_.toInt).toList
		  readEdges(m-1, edges + 
		  		(n1 -> (edges.getOrElse(n1, List()) :+ n2)) +
		  		(n2 -> (edges.getOrElse(n2, List()) :+ n1)))
		}
	}
	
	def getC(queue:List[Int], edges:Map[Int, List[Int]], visited:Array[Boolean], c:List[Int]):List[Int]={
		if(queue.isEmpty) c
		else{
				if(!visited(queue.head)){
					visited(queue.head)=true
					getC(queue.tail ++ edges.getOrElse(queue.head, List()).filter(!visited(_)), edges, visited, c :+ queue.head)
				}else{
          getC(queue.tail, edges, visited, c)
				}
		}
	}
	
	def getCC(idx:Int, edges:Map[Int, List[Int]], visited:Array[Boolean], cc:List[List[Int]]):List[List[Int]]={
		if(idx == visited.length) cc
		else if(visited(idx)){
			getCC(idx+1, edges, visited, cc)
		}else{
      val c = getC(List(idx), edges, visited, List[Int]())
      getCC(idx+1, edges, visited, cc :+ c)
  	}
	}
	
  def main(args:Array[String]){
    val (n, m) = (readLine.toInt, readLine.toInt)
    val edges = readEdges(m, Map[Int, List[Int]]())
    val cc = getCC(1, edges, new Array[Boolean](n+1), List[List[Int]]())
    println(cc.map(x=>math.sqrt(x.size).ceil.toInt).reduce(_+_))
  }
}