package mar14

import java.util.Scanner

object CommonDivisors{
  def main(args:Array[String]){
    val sc = new Scanner(System.in);
    for(tc <- 1 to sc.nextLine().toInt){
      val nd = primeFactors(sc.nextInt());
      val md = primeFactors(sc.nextInt());
      val common = nd.intersect(md);
      if(common.size>0){
        println(common.groupBy(_.toInt).mapValues(_.size+1).values.reduce(_*_));
      }else{
      	println(1);
      }
    }
    sc.close();
  }
  
  def divisors(n:Int):List[Int]={
      var pf = List[Int]();
      for(d <- 2 to Math.sqrt(n).toInt){
        if( n%d==0 ){
          pf=pf:+d;
        }
      }
      return pf;
  }
  
  def primeFactors(n:Int):List[Int]={
      var pf = List[Int]();
      var d = 2;
      var _n= n;
      while(_n > 1){
        while( _n%d==0 ){
          pf=pf:+d;
          _n=_n/d;
        }
        d=d+1;
      }
      return pf;
  }  
}