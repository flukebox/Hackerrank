import scala.math._

object Choclate{
  val sol = Array.ofDim[Int](26,26,26)
    def solve(x:Int, y:Int, z:Int):Int={
  	if (sol(x)(y)(z) != 0){
  		return sol(x)(y)(z)
  	}  
  	sol(x)(y)(z) = -1

  	for( i <- 2 to x){
  	  if(solve(i-1, min(i-1, y), min(i-1, z)) == -1){
  	  	sol(x)(y)(z) = 1
  	  	return 1
  	  }
  	}
    for( i <- 1 to y){
      if(solve(x, i-1, min(i-1, z)) == -1){
        sol(x)(y)(z) = 1
        return 1
      }
    }
    for( i <- 1 to z){
      if(solve(x, y, i-1) == -1){
        sol(x)(y)(z)=1
        return 1
      }
    }
    sol(x)(y)(z) = -1
    return -1
  }
  
	def main(args:Array[String]){
		    // solve for 25,25,25
    for(k <- 0 to 25){
      for(j <- k to 25){
        for( i <- min(j,1) to 25){
          solve(i, j, k)
        }
      }
    }

    (1 to readLine.toInt).map( x =>{
      val Array(x,y,z) = readLine.split(" ").map(x=>x.toInt)      
      println( if(sol(x)(y)(z)==1) "WIN" else "LOSE")
    })
	}
	
}