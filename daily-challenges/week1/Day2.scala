package week1

import java.util.Scanner

object Day2 {
  val MOD = 1000000007
  val modInverse = (1 to 50).map(x=> BigInt(x).modInverse(MOD)).toList
  def main(args:Array[String]){
    val sc = new Scanner(System.in)
    val (a, b) = (sc.nextInt(), sc.nextInt())
    println(if((a==25 && b<=23) || (b==25 && a <=23)){
      nCr(a+b-1, math.min(a,b))
    }else if(math.abs(a-b)==2 && a>23 && b>23){
      (nCr(48,24)*BigInt(2).modPow(math.min(a,b)-24, MOD)).mod(MOD).toInt
    }else{
      0
    })
  }
  
  def nCr(n:Int, r:Int):Int={
      if (r == 0) 1
      else {
        var temp:BigInt = 1
            for(j <- 1 to math.min(r, n-r)){
              temp = (temp * (n-j+1) * modInverse(j-1)).mod(MOD)
            }
      temp.toInt
      }
  }
}