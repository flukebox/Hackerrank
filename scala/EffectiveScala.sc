
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
