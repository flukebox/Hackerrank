object GameOfKyles{
  def main(args:Array[String]){
  	val nim = new Array[Int](401)
    nim(0)=0; nim(1)=1; nim(2)=2;
    for(i <- 3 to 400){
      val s1 = (0 to ((i-2)/2)+1).map(j => nim(j)^nim(i-2-j)).toSet
      val s2 = (0 to ((i-1)/2)+1).map(j => nim(j)^nim(i-1-j)).toSet
      nim(i)=getMex(s1 ++ s2, 0)
    }  
    (1 to readLine.toInt).map( _ =>{
      readLine// skip line
      if (readLine.split("X+").map(x=>nim(x.length)).reduce(_^_) == 0)
        "LOSE"
      else 
        "WIN"
    }).foreach(println)
  }  
  
  def getMex(set:Set[Int], n:Int):Int={
  	if(set.contains(n)) getMex(set, n+1) else n
  }
  
}

object simpleNimer{
	  val solve = new Array[Int](1000)
    solve(0) = -1
    solve(1) = 1
    solve(2) = -1
    solve(3) = 1
    solve(4) = 1
  
  def winningMove(k:Int):Boolean={
    k  match {
      case x if solve(x) != 0 => if(solve(x) == -1) false else true
      case _ => {
        for (i<- List(1,3,4)){
          if(!winningMove(k-i)){
              solve(k)=1
              return true
          }
        }
        solve(k) = -1
        false
      }
    }
  }
}