
class Foo{
  def apply() = new Foo
}

object FooMaker{
  def apply() = new Foo
  def xyz = new Foo
}

val foo = FooMaker()
foo()
foo()
FooMaker.xyz

val list = List(1, 2, 3)


def foo[A, B](f:A=>List[A], b: B)=f(b)

import java.util.Scanner
import java.util.Hashtable
import scala.io.Source

val filename = "/Users/jsingh1/Downloads/Cancel_1_417864177_-9223372034673811351.xml";
val data = Source.fromFile(filename).getLines();
