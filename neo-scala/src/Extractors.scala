


case class User(firstName:String, lastName:String, score:Int)

def adavnce(xs:List[User]) = xs match {
  case User(_, _, score1)::User(_, _, score2)::_ => score1 - score2
  case _ => 0
}


trait User1 {
  def name:String
}
class FreeUser(val name:String) extends User1
class PremiumUser(val name:String) extends User1

object FreeUser{
  def unapply(u: FreeUser): Option[String] = Some(u.name)
}

object PremiumUser{
  def unapply(u: PremiumUser): Option[String] = Some(u.name)
}
FreeUser.unapply(new FreeUser("jai"))

object User1 {

  def m(u:User1) = u match {
    case FreeUser(name) => "Hello "+ name
    case PremiumUser(name) => "Welcome back dear "+name +" "
  }
}


val l1 = List(1, 2, 3, 4)
val mayBeList = Some(l1)

def index(i:Int):Option[Int]={
  for{
    l <- mayBeList
    v <- l.lift(i)
  } yield v
}
