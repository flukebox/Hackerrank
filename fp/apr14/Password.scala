object Password{
  def main(args:Array[String]){
    (1 to readLine.toInt).map(x =>{
    	val n = readLine.toInt
    	val passwords = readLine.split(" ").toList
      val password = readLine
      if(!solve(password, passwords)){
        println("WRONG PASSWORD")
      }
    })
  }
  def solve(str:String, base:List[String]):Boolean={
    val sol = new Array[Int](str.length+1)
    var queue = List(0)
    while(!queue.isEmpty){
    	val head = queue.head
    	queue = queue.tail
  		for(b <- base){
  			if((head+b.length)<=str.length && b == str.substring(head, head+b.length) && sol(head+b.length) == 0){
            sol(head+b.length)=b.length
            queue =  queue :+ (head+b.length)
  			}
  		}
    }
    
    var sols = List[String]()
    if(sol(str.length) != 0){
    	var idx = str.length
    	while(idx!=0 && idx-sol(idx)>=0){
    		sols  = sols :+ str.substring(idx-sol(idx), idx)
    	  idx -= sol(idx)
    	}
    	println(sols.reverse.mkString(" "))
      true
    }else{
    	false
    }
  }
}