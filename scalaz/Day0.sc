// Parametric Polymorphism
def head[A](xs:List[A]):A = xs(0)

head(1 :: 2 :: Nil)

case class Car(make:String)

head(Car("Civic") :: Car("City") :: Car("Amaze"):: Nil)


// SubType Polymorphism

def plus[A](a1:A, a2:A):A = ???

trait  Plus[A]{
  def plus(a2:A):A
}

// We can provide different definition of plus by mixing
// It with different kind of datatype
def plus[A <: Plus[A]](a1: A, a2:A) = a1.plus(a2)


// Ad-Hoc polymorphism
trait PlusAP[A]{
  def plus(a1:A, a2:A):A
}
def plusAP[A:PlusAP](a1:A, a2:A) = implicitly[PlusAP[A]].plus(a1, a2)

class IntPlusAP extends PlusAP[Int]{
  override def plus(a1: Int, a2: Int): Int = a1+a2
}

implicit val intPlusAP = new IntPlusAP

implicit val stringPlushAP = new PlusAP[String] {
  override def plus(a1: String, a2: String): String = a1+a2
}

/**
  * Sum Function
  * @param xs
  * @return
  */

def sum(xs:List[Int]):Int = xs.foldLeft(0)(_+_)
sum(List(1, 2, 3, 4 ,5))


trait Monoid[A]{
  def mappend(a:A, b:A):A
  def mzero:A
}

object IntMonoid extends Monoid[Int]{
  override def mappend(a:Int, b:Int):Int = a+b
  override def mzero:Int = 0
}

def sumIM(xs:List[Int]):Int = xs.foldLeft(IntMonoid.mzero)(IntMonoid.mappend)
sumIM(List(1, 2, 3, 4 ,5))

def sum[A](xs:List[A], m:Monoid[A])= xs.foldLeft(m.mzero)(m.mappend)


def sum[A](xs:List[A])(implicit m:Monoid[A]):A = xs.foldLeft(m.mzero)(m.mappend)

implicit val intMonoid = IntMonoid


def sum[A:Monoid](xs:List[A]):A = {
  val m = implicitly[Monoid[A]]
  xs.foldLeft(m.mzero)(m.mappend)
}

/**
  * FOLD LEFT
  */

implicit val StringMonoid: Monoid[String] = new Monoid[String] {
  def mappend(a: String, b: String): String = a + b
  def mzero: String = ""
}


object FoldLeftList{
  def foldLeft[A, B](xs:List[A], b:B, f:(B,A)=> B) = xs.foldLeft(b)(f)
}

def sum[A:Monoid](xs:List[A]):A = {
  val m = implicitly[Monoid[A]]
  FoldLeftList.foldLeft(xs, m.mzero, m.mappend)
}

implicit val multiMonoid:Monoid[Int] = new Monoid[Int] {
  override def mappend(a: Int, b: Int): Int = a*b
  override def mzero: Int = 1
}

sum[String](List("1", "2", "3", "4" ,"5"))

trait FoldLeft[F[_]]{
  def foldLeft[A, B](xs:F[A], b:B, f:(B,A)=> B):B
}

object FoldLeft{
  implicit val foldLeftList:FoldLeft[List] = new FoldLeft[List]{
    override def foldLeft[A, B](xs: List[A], b: B, f: (B, A) => B): B = xs.foldLeft(b)(f)
  }

}

def sum[M[_]:FoldLeft, A:Monoid](xs:M[A]):A = {
  val m = implicitly[Monoid[A]]
  val fl = implicitly[FoldLeft[M]]
  fl.foldLeft(xs, m.mzero, m.mappend)
}

implicit val foldLeftSeq = new FoldLeft[Seq]{
  override def foldLeft[A, B](xs: Seq[A], b: B, f: (B, A) => B): B = xs.foldLeft(b)(f)
}

trait MonoidOp[A]{
  val F:Monoid[A]
  val value:A
  def |+| (a2:A) = F.mappend(value, a2)
}

implicit def toMonoidOp[A: Monoid](a: A): MonoidOp[A] = new MonoidOp[A] {
  val F = implicitly[Monoid[A]]
  val value = a
}

import scalaz._
import scalaz.Scalaz._

1.some | 2
Some(1).getOrElse(2)

( 1 > 10 )? 1|2