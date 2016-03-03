import scala.collection.immutable.List
import java.io.IOException
import scala.annotation.varargs
import scala.annotation.tailrec
import scala.xml._
import scala.xml.transform.RuleTransformer
import scala.xml.transform.RewriteRule
import java.io.FileInputStream
import java.io.InputStreamReader
import scala.collection.mutable.ArrayBuffer
import Source._
import javax.imageio.ImageIO
import java.awt.event.ActionEvent
import scala.util.parsing.combinator.RegexParsers
import mar14.Node
import scala.annotation.implicitNotFound

object ScratchPad {
  class Student{
    def id: String = null;
  }
  
  class Employee{
    def id:String = null; 
  }
  
  trait Logger{
    def log(msg:String) // an abstract method
  }
  
  trait ConsoleLogger extends Logger with Cloneable with Serializable{
    def log(msg:String){
      println(msg)
    }
  }
  
  abstract class Account{ 
    var balance : Double = 0
        def withdraw(amount:Double)
  }
  
  class SavingsAccount extends Account with ConsoleLogger{
    def withdraw(amount:Double){
      if(amount > balance ) log("In sufficient funds");
      else balance = balance - amount;
    }
  }
  
  
  object MainObject{
    def main(args:Array[String]){
      val a = Array("Neil", "Nitin", "Mukesh");
      val b = Array("Neil", "Nitin", "Mukesh");
      a.corresponds(b)(_.equalsIgnoreCase(_));
      runInThread{
        println("Hello MOFO World !")
        Thread.sleep(1000)
        println("Bye Sweetie !!!")
      }
    }
    
    def runInThread( block: => Unit ){
      new Thread{
        override def run(){ block}
      }.start()
    } 
  }
  
  /** get the digits from a number */
  def digits(n:Int):Set[Int]={
      if(n < 0) 
        digits(-n)
        else if (n < 10) 
          Set(n)
          else 
            digits(n/10) + n%10
  }
  
  /**
   * Different ways to get the sum of a list items
   */
  def sum(lst:List[Int]):Int=lst match {
    case Nil => 0
    case h::t => h + sum(t)
  }
  
  def sumr(lst:List[Int]):Int={
      if ( lst == Nil) 0
      else lst.head + sumr(lst.tail)
  }
  
  def sumi(lst:List[Int])=lst.reduce(_+_)
      
      
      val freq = scala.collection.mutable.Map[Char, Int]()
      "mississipi".foreach( x => freq(x) = freq.getOrElse(x, 0)+1)
      
      (Map[Char, Int]() /: "mississipi"){
    (m, c) => m + ( c -> (m.getOrElse(c, 0)+1))
  }
  
  "mississipi".foldLeft(Map[Char, Int]())((m, c) => m + ( c -> (m.getOrElse(c, 0)+1)))
  
  
  // Zip
  List(1,2,3,4).zipAll(List(1,2), 1, 1)
  List(1,2,3,4).zip(List(1,2))
  
  /** Streams **/
  def numsFrom(n:BigInt):Stream[BigInt]={
    n #:: numsFrom(n+1)
  }
  // DO NOT USE stream.force
  import scala.math._
  import scala.io._
  
  Source.fromFile("/Users/jai.singh/workspace/research/pcsaudf/src/main/java/PCSATest.scala").getLines.toStream
  (1 until 100).view.map( x => pow(10, x)).map( 1/_).force.foreach(println)
  (1 until 100).view.map( x => pow(10, x)).map( 1/_)
  
  
  // parellel in order
  (1 to 10).par.foreach(println)
  for( i <- (1 to 100).par) yield i +" "
  
  // out-of-order 
  for( i <- (1 to 10).par) println(i)
  (1 to 100).par.aggregate(0)(_ - _, _ - _)
  
  
  /**
   * CASES and CASE CLASSES 
   */
  val ch = '+'
  ch match {
    case '+' => 1
    case '-' => -1
    case _ if Character.isDigit(ch) => Character.digit(ch, 10)
    case _ => 0
  }
  
  
  // val obj = Some[]
  // obj match {
  //	 case x:Int => x
  //	 case x:String => Integer.parseInt(x)
  //	 case _:BigInt => Integer.MAX_VALUE
  //	 case _ => 0
  // }
  val obj = Map[String, Int]()
      obj match {
        case m:Map[String, Int] => println("Yo String")
        case m:Map[_ , _] => println("Yo Map")
  }
  
  
  val arr = Array(4,2)
      
      arr match {
        case Array(1,2,_*) => println("got 1,2,_*")
        case Array(x,y) => println("got "+x+", "+y)
        case Array(x,y,_*) => println("got "+x+", "+y+",_*")
        case _ => println("Got something else")
  }
  
  abstract class Amount
  case class Dollar(value:Double) extends Amount
  case class Currency(value:Double, unit:String) extends Amount
  case object Nothing extends Amount
  
  val amt:Object = Dollar(2)
  
  println(
      amt match{
        case Dollar(v) =>  "$"+v
        case Currency(_, u) => "Oh Noes, I got "+u
        case Nothing => ""
      })
      
      val v = 1 :: 2 :: Nil
      
      
      //case class ::[E] (head:E, tail:List[E]) extends List[E] 
      abstract class Item
  case class Article(desc:String, price:Double) extends Item
  case class Bundle(desc:String, dis:Double, items:Item*) extends Item
  
  val bun = Bundle("Fathers Day Special", 20, 
      Article("Scala for Dummies", 19.99),
      Bundle("Another Wine Shop", 9.99,
          Article("Old Monk", 4.99),
          Article("New Monk", 4.99)))
          
          //abstract class List
          //case object Nit extends List
          //case class ::(head:Any, tail:List) extends List
          
          
          class unchecked extends annotation.Annotation        
          
          
          class Books{
    
    @throws(classOf[IOException])
    def read(file:String){
    }
    
    /** Variable number of arguments **/
    def process1(args:String*)
    def process2(args:Seq[String])
    @varargs def process3( args : String*)
    
    
    /** Tail Recursion **/
    @tailrec
    def sum1(seq:Seq[Int]):BigInt={
      if(seq.isEmpty) 0 else seq.head + sum1(seq.tail)
    }
    
    @tailrec
    def sum2(seq:Seq[Int], partial:BigInt):BigInt={
      if(seq.isEmpty) partial else sum2(seq.tail, partial+seq.head)
    }
    
  }
  
}

class XML{
  val elem = <a href="www.google.com">Google</a>
      val items = new NodeBuffer
      val names = List("Jai", "Hemant", "Gaurav")
      items += <li>Fred</li>
      items += <li>Wilma</li>
      items += <ul>{ for(name <- names) yield <li>{name}</li> }</ul>
  val nodes:NodeSeq = items
      
      // get the href
      elem.attributes("href")
      elem \ "@href"
      
      // XPATH
      
      // get child li
      items \ "li"
      
      // Pattern Matching
      elem match {
        case <li>{_}</li> => println("Its a list item with a single child")
        case <li>{_*}</li> => println("Its a list item with child")
        // with bounded variables 
        case <li>{child}</li> => println(child)
        // with bounded wild cards
        case <li>{ children @ _ *}</li> => for (c <- children ) yield c
        case <a/> => println("Its a link")
        case _ => println("No Matching pattern found")		
      }
      
      // Modifying XML Elements and Attributes
      val list = <ul><li>Fred</li><li>Jane</li></ul>
          val list2 = list.copy(label="ol")
          val list3 = list.copy(child = list.child ++ <li>Another Item</li>)
          
          val img = <img src="hamster.jpg"/>
          val img2 = img % Attribute(null, "alt", "An Image of a Hamster", Null)
          val img3 = img % Attribute(null, "alt", "An Image of a Hamster", Attribute(null, "src", "frog.jpg", Null))
          
          // Transforming XML
          val rule1 = new RewriteRule{
        override def transform (n : Node) = n match {
          case e @ <ul>{_*}</ul> => e.asInstanceOf[Elem].copy(label="ol")
          case _ => n
        }
      }
      
      val rule2 = rule1
          // do the transformation accorind to above rule
          val transformed = new RuleTransformer(rule1, rule2)
      
      // Loading from XML file
      import scala.xml.XML
      import java.net.URL
      
      val root = XML.loadFile("myfile.xml")
      
      // from InputStream
      val filename = "myfile.xml"
      val root2 = XML.load(new InputStreamReader(new FileInputStream(filename)))
      val root3 = XML.load(new URL("http://google.com"))
      
      
      // Parser to preserve comments, CDATA and whitespaces
      import scala.xml.parsing.ConstructingParser
      import java.io.File
      import scala.xml.parsing.XhtmlParser
      import scala.xml.dtd.ParsedEntityDecl
      import scala.xml.dtd.IntDef
      
      val parser = ConstructingParser.fromFile(new File(filename), preserveWS=true)
      val doc = parser.document
      val iroot = doc.docElem
      
      val xhtmlparser = new XhtmlParser(scala.io.Source.fromFile(filename))
      val xhtmldoc = parser.initialize.document
      
      // adding UnKnown Entity
      parser.ent ++= List( 
          "nbsp" -> ParsedEntityDecl("nbsp", IntDef("\u00A0")),
          "eacute" -> ParsedEntityDecl("eacute", IntDef("\u00E9")))
          
          // Saving XML document
          XML.save(filename, iroot)
          
          // Pretty Printer
          val printer = new PrettyPrinter( width = 100, step = 4)
      
}


class TypeParameters {
  
  // Generic Classes
  class Pair1[T, S](val first:T, val second:S)
  
  // Generic Functions
  def getMiddle[T] (a:Array[T]) = a(a.length/2)
  
  // Bound on Types 
  class Pair2[+T](val first:T, val second:T){
    // wrong| error 
    // def smaller = if (first.compareTo(second) < 0) first else second
    def replaceFirst ( nfirst : T) = new Pair2[T](nfirst, second) 
  }
  
  // Correct one with upper bound <:
  class Pair3[T <: Comparable[T]](val first:T, val second:T){
    def smaller = if (first.compareTo(second) < 0) first else second
  }
  
  // lower bound
  class Pair4[T](val first:T, val second:T){
    def replaceFirst ( nfirst : T) = new Pair4[T](nfirst, second) 
        
        // lower bound with super type
        def replaceFirst2[R >: T]( nfirst: R) = new Pair4[R](nfirst, second)
  }
  
  // View Bounds
  class Pair5[T <% Comparable[T]](val first:T, val second:T){
    def smaller = if (first.compareTo(second) < 0) first else second
  }
  
  // View Bounds with Ordered 
  class Pair6[T <% Ordered[T]](val first:T, val second:T){
    def smaller = if (first < second) first else second
  }
  
  // Context Bounds
  class Pair7[T : Ordering](val first:T, val second:T){
    def smaller(implicit ord:Ordering[T]) = if (ord.compare(first, second) < 0) first else second
  }
  
  // Manifest Bounds
  def makePair[T:Manifest](first:T, second:T)={
    val r = new Array[T](2)
        r(0)=first
        r(1)=second
  }
  
  // Multiple Bounds
  // T <: Lower >: Lower
  // Bounds with multiple trait requirements
  // T <: Comparable[T] with Serializable with Clonable
  // Bounds with multiple view Bounds
  // T <% Comparable[T] <% String
  // Multiple manifest Bounds
  // T : Ordering : Manifest
  
  // Type Constraints
  // T =:= U  whether type T equals U
  // T <:< U  whether T is subtype of U
  // T <%< U  whether T is view-convertible to U
  
  
  class Pair8[T](val first:T, val second:T){
    def smaller(implicit ev:T <:< Ordered[T]) = if (first < second) first else second
  }
  // You can make a pair of Files but caller smaller on that pair will fail
  
  def firstLast[A, C <: Iterable[A]] (it:C) = (it.head, it.tail)
      def firstLast2[A, C](it:C)(implicit ev : C <:< Iterable[A]) = (it.head, it.tail)
      
      // Variance + => type is covariant in T i.e it varies in same direction 
      class Person
      class Student extends Person
      
      def makeFriends(p:Pair2[Person])
  
  val pa = new Pair2[Student]( new Student, new Student)
  makeFriends(pa)
  
  class Pair9[+T](val first:T, val second:T)
}

object AdvancedTypes {
  
  object Title
  
  // Singleton Type and Object Type
  class Document {
    var title:String = ""
        var author:String = ""
        private var useNextArgsAs:Any = null
        def setTitle(title:String):this.type = { this.title = title; this}
  def setAuthor(author:String):this.type = { this.author  = author; this}
  def set(obj:Title.type):this.type = { useNextArgsAs = obj; this}
  def to(arg:String) = if(useNextArgsAs == Title) title = arg
  }
  
  val article = new Document
      article.setTitle("YoLoSwag").setAuthor("Yo Yo HoneySingh")
      
      class Book extends Document{
    var chapter:String = ""
        def addChapter(chapter:String):this.type = {this.chapter = chapter; this}
  }
  
  val book = new Book
      book.setTitle("Mastram").setAuthor("SadhuYadav").addChapter("Savita Bhabhi")
      
      book set Title to "Scala for the Impatient"
      
      // Type Projections
      class Network{
    class Member(val name:String){
      // using Type Projection for Member class
      val contacts = new ArrayBuffer[Network#Member]
    }
    
    private val members = new ArrayBuffer[Member]
        
        def join(name:String) = {
      val m = new Member(name)
      members += m
      m
    }
  }
  
  val chatter = new Network
      val myFace = new Network
      
      val fred = chatter.join("Fred")
      val barney  = myFace.join("Barney")
      
      fred.contacts += barney
      
      // abstract type
      abstract class Reader{
    type Contents
    def read(fileName:String):Contents
  }
  
  // structural type similiar to Duck Typing in python/javascript
  def appendLines(target:{def append(str:String):Any}, lines:Iterable[String]){
    for( l <- lines) target.append(l+"\n")
    // so appendLiens method should be called on any type/class who have a method named append
  }
  
  // Compound type
  val image = new ArrayBuffer[java.awt.Shape with java.io.Serializable]
      val rect  = new java.awt.Rectangle(5, 10, 30, 40)
  image += rect
  
  // that imageshape extends the intersection type Shape with Serializable
  // with structural type declaration
  trait ImageShape extends java.awt.Shape with Serializable { def contains(p:java.awt.Point):Boolean}
  // here we make sure that ImageShare would be subtype of Shape and Serializable 
  // and It must have a method named contains with Point parameter
  
  // { def append(str:String):Any } is an abbreviation for Anyref { def append(str:String):Any}
  
  // Infix Type
  val map = Map[String, Int]()
      type mapType = String Map Int
      
      // Existential Types
      // Array[T]  forSome { type T <: JComponent }
      // Array[T]  forSome { type T}
      // Map[T, U] forSome { type T, type U
      // n.Member forSome { val n : Network}
      def process[M <: n.Member forSome { val n:Network}](m1:M, m2:M) = (m1, m2)
      
      val wilma = chatter.join("wilma")
      process(fred, wilma) // -- OK because fred, wilma is derived from same subclass
      // process(fred, barney) // -- Error because fred, barney is derived from different subclass
      
      // Self Type
      trait Logged {
    def log(msg:String)
  }
  trait LoggedException extends Logged{
    this:Exception => 
  def log() { log(getMessage())}
  }
  
  val f = new javax.swing.JFrame with Logged
      // val f = new javax.swing.JFrame with LoggedException
      
      
      // You NEED to inherit self Type Explicitly
      trait ManagedException extends LoggedException {
    this:Exception =>
  }
  
  // Abstract Type 
  class StringReader extends Reader {
    type Contents = String
        def read(filename:String) = "YoY"
  }
  
  class ImageReader extends Reader{
    type Contents = java.awt.image.BufferedImage
        def read(filename:String) = ImageIO.read(new java.io.File(filename))
  }
  
  trait ReaderTrait[C]{
    def read(filename:String):C
  }
  
  class StringReaderWithTrait extends ReaderTrait[String]{
    def read(filename:String):String = "YoY"
  }
  
  class ImageReaderWithTrait extends ReaderTrait[java.awt.image.BufferedImage]{
    def read(filename:String) = ImageIO.read(new java.io.File(filename))
  }
  
  // Family PolyMorphism
  trait Listener[E]{
    def occurred(e:E):Unit
  }
  
  trait SourceE[E, L <: Listener[E]]{
    private val listeners = new ArrayBuffer[L]
        def add(l:L) { listeners += l}
    def remove(l:L) { listeners -=l}
    def fire(e:E){ 
      for( l <- listeners) l.occurred(e)
    }
  }
  
  trait ActionListener extends Listener[java.awt.event.ActionEvent]
      
      class Buttons extends SourceE[ActionEvent, ActionListener]{
    def click(){
      fire(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "click"))
    }
  }
}  


// Parsing IN Scala
object Parsing{
  import scala.util.parsing.combinator.RegexParsers
  
  class ExprParser extends RegexParsers{
    val number = "[0-9]+".r
        def expr:Parser[Any] = term ~ opt (("+"|"-") ~ expr)
        def term:Parser[Any] = factor ~ rep("*" ~ factor)
        def factor:Parser[Any] = number | "(" ~ expr ~ ")"
  }
  
  var parser = new ExprParser
      
      
      import scala.util.parsing.combinator._
      class Arith extends JavaTokenParsers {   
    def expr: Parser[Any] = term~rep("+"~term | "-"~term)
        def term: Parser[Any] = factor~rep("*"~factor | "/"~factor)
        def factor: Parser[Any] = floatingPointNumber | "("~expr~")"
  }
  val parser2 = new Arith
      parser2.parseAll(parser2.expr, "3-4*5")
      
      
      import scala.util.parsing.combinator._
      class JSON extends JavaTokenParsers {   
    def value : Parser[Any] = obj | arr | 
        stringLiteral | 
        floatingPointNumber | 
        "null" | "true" | "false"
        def obj   : Parser[Any] = "{"~repsep(member, ",")~"}"
        def arr   : Parser[Any] = "["~repsep(value, ",")~"]"
        def member: Parser[Any] = stringLiteral~":"~value
  }
  
  import java.io.FileReader
  object ParseJSON extends JSON {
    def main(args: Array[String]) {
      val reader = new FileReader(args(0))
      println(parseAll(value, reader))
    }
  }
}


// Actors aka AKKA
object Actor{
  
  import scala.actors.Actor
  
  class HiActor extends Actor{
    def act(){
      while(true){
        receive{
          case "Hi" => println("Yellow")
        }
      }
    }
  }
  val actor = new HiActor
      // start the actor
      actor.start
      
      // send a message
      actor ! "Hi"
}

// Implicit Conversions
object ImplicitConversions{
  
  import java.nio.file._
  object Hello extends App{
    for( v <- Lights.values){
      println(v.id+"=>"+v);
    }
    println("Yo! Bitch !!!!")
    
    implicit def makeFileVisitor( f:(Path) => Unit ) = new SimpleFileVisitor[Path]{
      override def visitFile( p: Path, attrs:attribute.BasicFileAttributes)={
        f(p)
        FileVisitResult.CONTINUE
      }
    }
  }
  
  object Lights extends Enumeration{
    val red, blue, yellow = Value;
  }

  
  // implicit conversion
  class RichFile(val from:java.io.File){
  	def read = Source.fromFile(from.getPath).toString()
  }
  
  implicit def file2RichFile(from:java.io.File) = new RichFile(from)

  val contents = new java.io.File("Readme.md").read

  // implicit parameters
  case class Delimiters(left:String, right:String)
  implicit val delimiters = Delimiters("<!", "!>")
  
  def quote(what:String)(implicit delims:Delimiters) = delims.left + what + delims.right
  quote("Bonjour Le Monde")(Delimiters("<<", ">>"))

  
  // implicit conversions with implicit parameters
  def smaller[T](a:T, b:T)(implicit order: T => Ordered[T]) = if(a<b) a else b
   
  // Context Bound
  class Pair[T:Ordering](val first:T, val second:T){
  	def smaller1(implicit ord:Ordering[T]) = if (ord.compare(first, second) < 0) first else second 
    def smaller2 = if ( implicitly[Ordering[T]].compare(first, second) < 0) first else second 
    def smaller3 = {
    	import Ordered._
    	if (first < second) first else second
    }
  }
  
  // Evidence 
  // You need to provide information to compiler that C is type convertible to Iterable[A]
  def  firstLast[A, C](it:C)(implicit ev:C <:< Iterable[A]) = (it.head, it.tail)
  
  // Implicit Not Found
  @implicitNotFound(msg=" Cannot prove that ${from} <:< ${To} ")
  abstract class <:< [-From, +To] extends Function1[From, To]
  
  class factInt(n:Int){
  	def ! = (1 to n).reduce(_*_) 
  }
  
  implicit def int2factInt(from:Int) = new factInt(from)
  
  // computes factorial of 10
  10!
  
}

object DelimitedConitinuations{
	
}

// RECAP 
object Recap{
	var x = 5
	
}


abstract class SubjectObserver{
  type S <: Subject
  type O <: Observer

  trait Subject {
    self:S =>
    private var observers:List[O] = List()
    def subscribe(obs:O) = observers = obs :: observers
    def publish = for ( obs <- observers) obs.notify(this)
  }
  
  trait Observer {
    def notify (sub:S)
  }
}

object SensorReader extends SubjectObserver {
  type S = Sensor
  type O = Display
  
  abstract class Sensor extends Subject {
    val label:String
    var value:Double = 0.0
    def changeValue ( v:Double) = {
     value = v
     publish
    }
  }
  
  class Display extends Observer{
    def notify(sub:Sensor) = println(sub.label+" has value "+ sub.value)
  }
}

object Test {
  import SensorReader._
  val s1 = new Sensor { val label = "Sensor1" }
  val s2 = new Sensor { val label = "Sensor2" }

  def main(args:Array[String]){
    val d1 = new Display
    val d2 = new Display
    s1.subscribe(d1)
    s1.subscribe(d2)
    s2.subscribe(d1)
    s1.changeValue(2)
    s1.changeValue(3)
  }
  
}


