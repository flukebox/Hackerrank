import scala.math._

object ListWorkSheet{

	trait List[ +T ] {
	  def isEmpty : Boolean
	  def head : T
	  def tail : List[ T ]
	  def prepand[ U >: T]( elem : U ) : List[ U ] = new Cons( elem, this )
	}
	class Cons[ T ]( val head : T, val tail : List[ T ] ) extends List[ T ] {
	  def isEmpty = false
	}
	
	object Nil extends List[ Nothing ] {
	  def isEmpty : Boolean = true
	  def head : Nothing = throw new NoSuchElementException( "Nil.head" )
	  def tail : Nothing = throw new NoSuchElementException( "Nil.tail" )
	}
	
  val x : List[ String ] = Nil                    //> x  : ListWorkSheet.List[String] = ListWorkSheet$Nil$@182decdb
  val y = x.prepand( "jai" )                      //> y  : ListWorkSheet.List[String] = ListWorkSheet$Cons@180bc464
}


object IntSetsWorkSheet{
  val t1 = new NonEmpty(3, Empty, Empty)
  val t2 = t1 incl 4
  val t3 = t2 incl 1

  val t4 = new NonEmpty(9, new NonEmpty(2, Empty, Empty), new NonEmpty(12, Empty, Empty))
  t4 union t2
  t2 union t4
  t1 union t3
  t3 union t4
  t4 union t3

  val a:Array[NonEmpty] = Array(t1)
  
	abstract class IntSet {
	  def incl(x:Int):IntSet
	  def contains(x:Int):Boolean
	  def union(other:IntSet):IntSet
	}
	
	object Empty extends IntSet{
	  def contains(x:Int):Boolean = false
	  def incl(x:Int):IntSet = new NonEmpty(x, Empty, Empty)
    def union(other:IntSet):IntSet = other
	  override def toString = "."
	}
	
	class NonEmpty(elem:Int, left:IntSet, right:IntSet) extends IntSet{
	  def contains(x:Int):Boolean =
	    if ( x < elem ) left contains x
	    else if ( x > elem) right contains x
	    else true
	
	  def incl(x:Int):IntSet =
	    if ( x < elem ) new NonEmpty(elem, left incl x, right)
	    else if ( x > elem) new NonEmpty(elem,  left , right incl x)
	    else this

    def union(other:IntSet):IntSet = left union right union other incl elem

    override def toString = "{" + left + elem + right +"}"
	}

}

object Naturals {
abstract class Nat {
  def isZero:Boolean
  def predecessor:Nat
  def successor:Nat =  new Succ(this)
  def + (that:Nat):Nat
  def - (that:Nat):Nat
}

object Zero extends Nat{
  def isZero:Boolean = true
  def predecessor:Nat = throw new Error("0.predecessor")
  def + (that:Nat):Nat = that
  def - (that:Nat):Nat = if(that.isZero) this else throw new Error("Negative number")
}

class Succ(n:Nat) extends Nat {
  def isZero:Boolean = false
  def predecessor:Nat = n
  def + (that:Nat):Nat = new Succ(n+that)
  def - (that:Nat):Nat = if(that.isZero) this else  n - that.predecessor
}
  val one = new Succ(Zero)
  val two = new Succ(one)
  
  two - one - one isZero
}



object exercise {
  val tolerance = 0.0001
  
  def isCloseEnough(x: Double, y:Double) = abs ((x-y)/x)/x < tolerance
  def fixedPoint(f:Double => Double)(firstGuess:Double) = {
    def iterate(guess:Double):Double ={
      val next = f(guess)
      if (isCloseEnough(guess, next)) next
      else iterate(next)
    }
    iterate(firstGuess)
  }

  fixedPoint( x =>  1 + x /2 )(3.0)
  
  def sqrt(x:Double) = fixedPoint( y => (y+x/y)/2 )(1.0)

  sqrt(10)
                                                  
  def averageDamp(f:Double => Double)(x:Double) = (x + f(x))/2

  def sqrt2(x:Double) = fixedPoint(averageDamp(y => x/y))(1)
  sqrt2(10)


  abstract class Base {
    def foo = 1
    def bar : Int
  }

  class Sub extends Base {
    override def foo = 2
    def bar = 3
  }
  
  val b:Base = new Sub
  println(b)
  println(b.bar)
  println(b.foo)
    
}

object ScalaCourse {
  println("Welcome to the Scala worksheet")

  def loop:Boolean = loop

  def and( x:Boolean, y:Boolean):Boolean = {
    if (x) y else false
  }

  and(false, true)
  and(true, true)
  and(true, false)

  def and2( x:Boolean, y: => Boolean):Boolean = {
    if (x) y else false
  }

  // NON TERMINATING
  //and(false, loop)

  // Terminating
  and2(true, true)
  
  
  def abs( x:Double) = if (x > 0) x else -x


                                                  
                                                  
  def sqrt(x:Double):Double = {
  
		  def sqrtIter(guess:Double):Double = {
		    if(isGoodEnough(guess)) guess
		    else sqrtIter(improve(guess))
		  }
		  
		  def isGoodEnough(guess:Double):Boolean = {
		      abs( guess * guess - x )/x< 0.0001
		  }
		  
		  def improve(guess:Double) = (guess + x/guess)/2
  
      sqrtIter(1.0)
  }

  sqrt(1)
  sqrt(2)
  sqrt(2.0e10)
  sqrt(2e-10)
  sqrt(6e60)
  sqrt(1e60)
  
  
  def fact(n:BigInt) = {
    def factn(x:BigInt, vl:BigInt):BigInt = if(x==0) vl else factn( x-1, x*vl)
    factn(n, 1)
  }
  
  fact(115)
                                                  
                                                  
                                                  
                                                  
                                                  
  def sum( f : Int => Int, a:Int, b:Int):Int ={
    def loop( a:Int, acc:Int):Int = {
      if ( a > b) acc
      else loop( a+1, f(a) + acc)
    }
    loop( a, 0)
  }
  
  sum( x => x, 1, 10)
  sum( x => x*x, 3, 5)
  sum( x => x*x*x, 1, 10)
   


  def sumCurry( f: Int => Int) (a:Int, b:Int):Int = {
    if ( a > b) 0 else f(a) + sumCurry(f)(a+1, b)
  }
  
  
  sumCurry(x => x)(1, 10)
  sumCurry(x => x*x*x)(1, 10)
 
 
  def mapReduce(f:Int => Int, combine:(Int, Int) => Int, zero:Int)(a:Int, b:Int):Int = {
    if( a > b) zero
    else combine(f(a), mapReduce(f, combine, zero)(a+1, b))
  }
                                                  
  def product(f:Int => Int)( a:Int, b:Int):Int = mapReduce(f, (x, y) => x*y, 1)(a, b)
                                                  
  product( x=> x) ( 1, 5)
  

}