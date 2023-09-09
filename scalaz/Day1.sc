import scalaz.Scalaz._
import scalaz._
1 === 1

1 ?|? 1

1 lte 2

2.show

2.shows

"Jaiks".println

'a' to 'd'

'a' |-> 'f'

3 |=> 5

'B'.succ

sealed trait TrafficLight0
case object Red extends TrafficLight0
case object Yellow extends TrafficLight0
case object Green extends TrafficLight0

implicit val TrafficLightEqual1: Equal[TrafficLight0] = Equal.equal(_ == _)

case class TrafficLight(name: String)
val red = TrafficLight("red")
val yellow = TrafficLight("yellow")
val green = TrafficLight("green")
implicit val TrafficLightEqual2: Equal[TrafficLight] = Equal.equal(_ == _)
red === yellow


/**
  * A Yes-NO typeclass
  * @tparam A
  */

trait CanTruthy[A] { self =>
  /** @return true, if `a` is truthy. */
  def truthys(a: A): Boolean
}
object CanTruthy {
  def apply[A](implicit ev: CanTruthy[A]): CanTruthy[A] = ev
  def truthys[A](f: A => Boolean): CanTruthy[A] = new CanTruthy[A] {
    def truthys(a: A): Boolean = f(a)
  }
}
trait CanTruthyOps[A] {
  def self: A
  implicit def F: CanTruthy[A]
  final def truthy: Boolean = F.truthys(self)
}
object ToCanIsTruthyOps {
  implicit def toCanIsTruthyOps[A](v: A)(implicit ev: CanTruthy[A]) =
    new CanTruthyOps[A] {
      def self = v
      implicit def F: CanTruthy[A] = ev
    }
}

implicit val intCanTruthy: CanTruthy[Int] = CanTruthy.truthys({
  case 0 => false
  case _ => true
})

import ToCanIsTruthyOps._

10.truthy

implicit def listCanTruthy[A]: CanTruthy[List[A]] = CanTruthy.truthys({
  case Nil => false
  case _   => true
})


List("foo").truthy

implicit val nilCanTruthy: CanTruthy[scala.collection.immutable.Nil.type] =
  CanTruthy.truthys(_ => false)

Nil.truthy

implicit val booleanCanTruthy: CanTruthy[Boolean] = CanTruthy.truthys(identity)
false.truthy

/**
  * To delay the evaluation of the passed arguments, we can use pass-by-name:
  * @param cond
  * @param ifyes
  * @param ifno
  * @tparam A
  * @tparam B
  * @tparam C
  * @return
  */
def truthyIf[A: CanTruthy, B, C](cond: A)(ifyes: => B)(ifno: => C) = {
  if (cond.truthy) ifyes else ifno
}

truthyIf (Nil) {"YEAH!"} {"NO!"}
truthyIf (2 :: 3 :: 4 :: Nil) {"YEAH!"} {"NO!"}
truthyIf (true) {"YEAH!"} {"NO!"}
