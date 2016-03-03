package scala.course

trait List[ +T ] {
	def isEmpty : Boolean
	def head : T
	def tail : List[ T ]
	def prepand( elem : T ) : List[ T ] = new Cons( elem, this )
	
}

class Cons[ T ]( val head : T, val tail : List[ T ] ) extends List[ T ] {
	def isEmpty = false
}

object Nil extends List[ Nothing ] {
	def isEmpty : Boolean = true
	def head : Nothing = throw new NoSuchElementException( "Nil.head" )
	def tail : Nothing = throw new NoSuchElementException( "Nil.tail" )
}

object Test {
	val x : List[ String ] = Nil
	val y = x.prepand( "jai" )
	
	
	
}