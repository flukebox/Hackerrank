import scalaz._
import scalaz.Scalaz._


def isBigGang(x: Int): (Boolean, String) =
  (x > 9, "Compared gang size to 9.")

implicit class PairOps[A](pair: (A, String)) {
  def applyLog[B](f: A => (B, String)): (B, String) = {
    val (x, log) = pair
    val (y, newlog) = f(x)
    (y, log ++ newlog)
  }
}

(3, "Smallish gang. ") applyLog isBigGang

implicit class PairOps1[A, B: Monoid](pair: (A, B)) {
  def applyLog[C](f: A => (C, B)): (C, B) = {
    val (x, log) = pair
    val (y, newlog) = f(x)
    (y, log |+| newlog)
  }
}

(3, "Smallish gang. ") applyLog isBigGang

3.set("Smallish gang.")

def vectorFinalCountDown(x: Int): Writer[Vector[String], Unit] = {
  import annotation.tailrec
  @tailrec
  def doFinalCountDown(x: Int, w: Writer[Vector[String], Unit]): Writer[Vector[String], Unit] = x match {
    case 0 => w >>= { _ => Vector("0").tell }
    case x => doFinalCountDown(x - 1, w >>= { _ =>
      Vector(x.shows).tell
    })
  }
  val t0 = System.currentTimeMillis
  val r = doFinalCountDown(x, Vector[String]().tell)
  val t1 = System.currentTimeMillis
  r >>= { _ => Vector((t1 - t0).shows + " msec").tell }
}

def listFinalCountDown(x: Int): Writer[List[String], Unit] = {
  import annotation.tailrec
  @tailrec
  def doFinalCountDown(x: Int, w: Writer[List[String], Unit]): Writer[List[String], Unit] = x match {
    case 0 => w >>= { _ => List("0").tell }
    case x => doFinalCountDown(x - 1, w >>= { _ =>
      List(x.shows).tell
    })
  }
  val t0 = System.currentTimeMillis
  val r = doFinalCountDown(x, List[String]().tell)
  val t1 = System.currentTimeMillis
  r >>= { _ => List((t1 - t0).shows + " msec").tell }
}

val v1 = vectorFinalCountDown(100000).run
v1._1.last

val v2 = listFinalCountDown(100000).run
v2._1.last

val f = ({(_: Int) * 2} |@| {(_: Int) + 10})
f