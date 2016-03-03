import scala.io.Source

object MyMain{
	def main(args:Array[String]) = {
	  if(args.length>0){
      println("==============================")     
      for(line <- Source.fromFile(args(0)).getLines()){
          println(line.length+"\t::\t"+line)	   
          println("Yo Yo HoneySingh, This is Cool")
  	  }
      println("==============================")     
	  }
	
	  val firstArg = if (args.length > 0) args(0) else ""
	  val friend = firstArg match {
	     case "salt" => "pepper"
	     case "chips" => "potato"
	     case "eggs" => "bacon"
	     case _ => println("huh?")
	  } 
	  
	  println("Printing friend =>" + friend)
	  
		val wg = new WorldlyGreeter("Hello")
		wg.greet()
    var pet:Friendly = new Dog();
    println(pet.greet())
    pet = new Cat();
    println(pet.greet())
    pet = new Dog() with ExclaimatoryGreeter;
    println(pet.greet())
    pet = new Cat() with ExclaimatoryGreeter;
    println(pet.greet())
	}
}

class WorldlyGreeter(greeting:String){

	def greet() = {
		val worldlygreeting = WorldlyGreeter.worldify(greeting)
		println(worldlygreeting)

	}

	object WorldlyGreeter{
		def worldify(s:String) =  s+" , world !"
	}
}

trait Friendly{
  def greet() = "Hi"
}

class Dog extends Friendly{
  override  def greet() = "Woof"
}

class Cat extends Friendly{
  override  def greet() = "Meow"
}

trait ExclaimatoryGreeter extends Friendly{
  override  def greet() = super.greet()+"!"
}

