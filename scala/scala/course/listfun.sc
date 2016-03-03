object listfun {

  val nums = List(2, -4, 5, 7, 1)                 //> nums  : List[Int] = List(2, -4, 5, 7, 1)

  val fruits = List("apple", "pineapple", "orange", "banana")
                                                  //> fruits  : List[String] = List(apple, pineapple, orange, banana)
  
  nums filter ( _ > 0)                            //> res0: List[Int] = List(2, 5, 7, 1)
  nums filterNot( _ > 0)                          //> res1: List[Int] = List(-4)
  nums partition ( _ > 0)                         //> res2: (List[Int], List[Int]) = (List(2, 5, 7, 1),List(-4))
  nums takeWhile( _ > 0)                          //> res3: List[Int] = List(2)
  nums dropWhile(_ > 0)                           //> res4: List[Int] = List(-4, 5, 7, 1)
  nums span ( _ > 0)                              //> res5: (List[Int], List[Int]) = (List(2),List(-4, 5, 7, 1))
  
  val data = List("a", "a", "a", "b", "c", "c", "a")
                                                  //> data  : List[String] = List(a, a, a, b, c, c, a)
  def pack[T](xs:List[T]):List[List[T]] = xs match {
    case Nil => Nil
    case x :: xs1 =>
      val (first, rest) = xs span ( y => y ==x )
      first :: pack(rest)
  }                                               //> pack: [T](xs: List[T])List[List[T]]

  pack(data)                                      //> res6: List[List[String]] = List(List(a, a, a), List(b), List(c, c), List(a))
                                                  //| 
  def encoding[T](xs: List[T]):List[(T, Int)] = {
    pack(xs) map ( ys => (ys.head, ys.length))
  }                                               //> encoding: [T](xs: List[T])List[(T, Int)]
  
  encoding(data)                                  //> res7: List[(String, Int)] = List((a,3), (b,1), (c,2), (a,1))
  
  def concat[T](xs:List[T], ys:List[T]):List[T] = (xs foldRight ys) ( _ :: _)
                                                  //> concat: [T](xs: List[T], ys: List[T])List[T]
  concat(fruits, data)                            //> res8: List[String] = List(apple, pineapple, orange, banana, a, a, a, b, c, c
                                                  //| , a)
  
  val str1 = "Yo Mama So Hot"                     //> str1  : String = Yo Mama So Hot
  val str2 = "How Funny"                          //> str2  : String = How Funny
  
  str1.map( x => x*2)                             //> res9: scala.collection.immutable.IndexedSeq[Int] = Vector(178, 222, 64, 154,
                                                  //|  194, 218, 194, 64, 166, 222, 64, 144, 222, 232)
  
  str1 zip str2                                   //> res10: scala.collection.immutable.IndexedSeq[(Char, Char)] = Vector((Y,H), (
                                                  //| o,o), ( ,w), (M, ), (a,F), (m,u), (a,n), ( ,n), (S,y))
  str1 flatMap ( x => List('.', x, '.'))          //> res11: String = .Y..o.. ..M..a..m..a.. ..S..o.. ..H..o..t.



  def queens(n: Int):Set[List[Int]]  = {
    def placeQueens(k:Int):Set[List[Int]] = {
      k match {
        case 0 => Set(List())
        case _ =>
          for {
            queens <- placeQueens(k-1)
            col <- 0 until n
            if isSafe(col, queens)
          } yield col :: queens
      }
    }
    
    def isSafe(col:Int, queens:List[Int]):Boolean = {
      val row = queens.length
      (row - 1 to 0 by -1  zip queens) forall {
        case (r, c) => col != c && scala.math.abs(col -c ) != row -r
      }
    }

    placeQueens(n)
  }                                               //> queens: (n: Int)Set[List[Int]]
  
  def show(queens:List[Int]) = {
    val lines =
        for( col <- queens.reverse) yield Vector.fill(queens.length)("* ").updated(col, "X ").mkString
    "\n" +  lines.mkString("\n")
  }                                               //> show: (queens: List[Int])String
  
  
  queens(4) take 2 map show  mkString "\n"        //> res12: String = "
                                                  //| * * X * 
                                                  //| X * * * 
                                                  //| * * * X 
                                                  //| * X * * 
                                                  //| 
                                                  //| * X * * 
                                                  //| * * * X 
                                                  //| X * * * 
                                                  //| * * X * "
 
  val mnem = Map( 2 -> "ABC", 3 -> "DEF", 4-> "GHI", 5-> "JKL" , 6 -> "MNO", 7 ->  "PQRS", 8 ->  "TUV", 9 ->  "WXYZ")
                                                  //> mnem  : scala.collection.immutable.Map[Int,String] = Map(5 -> JKL, 6 -> MNO
                                                  //| , 9 -> WXYZ, 2 -> ABC, 7 -> PQRS, 3 -> DEF, 8 -> TUV, 4 -> GHI)
  val charCode = mnem flatMap { case (v, x)  => x map ( c => (c, v))}
                                                  //> charCode  : scala.collection.immutable.Map[Char,Int] = Map(E -> 3, X -> 9, 
                                                  //| N -> 6, T -> 8, Y -> 9, J -> 5, U -> 8, F -> 3, A -> 2, M -> 6, I -> 4, G -
                                                  //| > 4, V -> 8, Q -> 7, L -> 5, B -> 2, P -> 7, C -> 2, H -> 4, W -> 9, K -> 5
                                                  //| , R -> 7, O -> 6, D -> 3, Z -> 9, S -> 7)
                                                  
 val charCode2 = for{
	 (v, str) <- mnem
	 ch <- str
  } yield (ch, v)                                 //> charCode2  : scala.collection.immutable.Map[Char,Int] = Map(E -> 3, X -> 9,
                                                  //|  N -> 6, T -> 8, Y -> 9, J -> 5, U -> 8, F -> 3, A -> 2, M -> 6, I -> 4, G 
                                                  //| -> 4, V -> 8, Q -> 7, L -> 5, B -> 2, P -> 7, C -> 2, H -> 4, W -> 9, K -> 
                                                  //| 5, R -> 7, O -> 6, D -> 3, Z -> 9, S -> 7)

  def from(n:Int):Stream[Int] = n #:: from(n+1)   //> from: (n: Int)Stream[Int]
  val nats = from(0)                              //> nats  : Stream[Int] = Stream(0, ?)
  val m4s = nats map (_*4)                        //> m4s  : scala.collection.immutable.Stream[Int] = Stream(0, ?)
  m4s take 100 toList                             //> res13: List[Int] = List(0, 4, 8, 12, 16, 20, 24, 28, 32, 36, 40, 44, 48, 52
                                                  //| , 56, 60, 64, 68, 72, 76, 80, 84, 88, 92, 96, 100, 104, 108, 112, 116, 120,
                                                  //|  124, 128, 132, 136, 140, 144, 148, 152, 156, 160, 164, 168, 172, 176, 180,
                                                  //|  184, 188, 192, 196, 200, 204, 208, 212, 216, 220, 224, 228, 232, 236, 240,
                                                  //|  244, 248, 252, 256, 260, 264, 268, 272, 276, 280, 284, 288, 292, 296, 300,
                                                  //|  304, 308, 312, 316, 320, 324, 328, 332, 336, 340, 344, 348, 352, 356, 360,
                                                  //|  364, 368, 372, 376, 380, 384, 388, 392, 396)
  def sieve( s:Stream[Int]):Stream[Int] = s.head #:: ( s.tail filter (_ % s.head != 0))
                                                  //> sieve: (s: Stream[Int])Stream[Int]
  sieve(from(2)) take 100 toList                  //> res14: List[Int] = List(2, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 
                                                  //| 29, 31, 33, 35, 37, 39, 41, 43, 45, 47, 49, 51, 53, 55, 57, 59, 61, 63, 65,
                                                  //|  67, 69, 71, 73, 75, 77, 79, 81, 83, 85, 87, 89, 91, 93, 95, 97, 99, 101, 1
                                                  //| 03, 105, 107, 109, 111, 113, 115, 117, 119, 121, 123, 125, 127, 129, 131, 1
                                                  //| 33, 135, 137, 139, 141, 143, 145, 147, 149, 151, 153, 155, 157, 159, 161, 1
                                                  //| 63, 165, 167, 169, 171, 173, 175, 177, 179, 181, 183, 185, 187, 189, 191, 1
                                                  //| 93, 195, 197, 199)
                                                  
}