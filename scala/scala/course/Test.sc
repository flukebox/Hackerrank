object Test {

  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def abs(x:Double) = if (x < 0 ) -x else x       //> abs: (x: Double)Double

  def isGoodEnough(guess:Double, x:Double) = {
    abs(guess*guess - x)/x < 0.0001
  }                                               //> isGoodEnough: (guess: Double, x: Double)Boolean
  
  def improve(guess:Double, x:Double) ={
      (guess + x/guess)/2
  }                                               //> improve: (guess: Double, x: Double)Double

  def sqrtIter(guess: Double, x:Double): Double = {
    if (isGoodEnough(guess, x)) guess
    else sqrtIter(improve(guess, x), x)
  }                                               //> sqrtIter: (guess: Double, x: Double)Double
  
  def sqrt(x:Double) = sqrtIter(1.0, x)           //> sqrt: (x: Double)Double

  sqrt(2)                                         //> res0: Double = 1.4142156862745097
  sqrt(4)                                         //> res1: Double = 2.0000000929222947
  sqrt(10e-10)                                    //> res2: Double = 3.162278058889937E-5
  sqrt(1e60)                                      //> res3: Double = 1.0000000031080746E30
 
 
 
 import scala.concurrent._
 import ExecutionContext.Implicits.global
 
 val f:Future[Double] = future{
  sqrt(2)
 }                                                //> f  : scala.concurrent.Future[Double] = scala.concurrent.impl.Promise$Default
                                                  //| Promise@49993335
 println(f.failed)                                //> scala.concurrent.impl.Promise$DefaultPromise@3108bc
 println(f.isCompleted)                           //> true
 println(f.value)                                 //> Some(Success(1.4142156862745097))
 
 
 
 
 
  
}