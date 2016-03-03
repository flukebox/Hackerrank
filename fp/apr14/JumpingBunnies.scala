import java.util.Scanner

object JumpingBunnies {
   def main(args:Array[String]){
      val sc = new Scanner(System.in)
      val n = sc.nextLine().toInt
      println(sc.nextLine().split(" ").map(x=>x.toLong).toList.reduce(lcm))
    }
    def gcd(a: Long, b: Long):Long = if (b==0) a else gcd(b, a%b)
    def lcm(a: Long, b: Long):Long = (a/gcd(a,b))*b
}