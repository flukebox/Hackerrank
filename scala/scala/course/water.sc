package scala.course.week1



object water {
  class Pouring( capacity : Vector[ Int ] ) {
    type State  = Vector[Int]
    val initialState = capacity map ( _ => 0)
    
    trait Move {
      def change(state:State):State
    }
    case class Empty(glass:Int) extends Move {
      def change(state:State):State = state updated (glass, 0)
    }
    case class Fill(glass:Int) extends Move{
      def change(state:State):State = state updated (glass, capacity(glass))
    }
    case class Pour(from:Int, to:Int) extends Move{
      def change(state:State):State = {
        val amount = state(from) min capacity(to) - state(to)
        state updated (from, state(from) - amount) updated ( to , state(to) + amount)
      }
    }
    
    val glasses = 0 until capacity.length
    
    val moves =
      ( for (g <- glasses) yield Empty(g)) ++
      ( for (g <- glasses) yield Fill(g))  ++
      ( for (from <- glasses; to <- glasses if from != to) yield Pour(from ,to))
      
    class Path(history:List[Move], val endState:State){
      def extend(move:Move) = new Path( move :: history , move change endState)
      override def toString = (history.reverse mkString " ") + " --> " + endState
    }
    
    val initialPath = new Path(Nil, initialState)
    
    def from(paths:Set[Path], explored:Set[State]):Stream[Set[Path]] =
      if (paths.isEmpty) Stream.empty
      else {
        val more = for {
          path <- paths
          next <- moves map path.extend
          if !(explored contains next.endState)
        } yield next
        paths #:: from(more, explored ++ ( more map (_.endState)))
      }
      
    val pathSets = from(Set(initialPath), Set(initialState))
      
    def solution(target:Int):Stream[Path] = {
      for {
        pathSet <- pathSets
        path <- pathSet
        if path.endState contains target
      }  yield path
    }
  }
  
  val problem = new Pouring(Vector(4, 9, 19, 11)) //> problem  : scala.course.week1.water.Pouring = scala.course.week1.water$Pour
                                                  //| ing@4de8b406
  problem.moves                                   //> res0: scala.collection.immutable.IndexedSeq[Product with Serializable with 
                                                  //| scala.course.week1.water.problem.Move] = Vector(Empty(0), Empty(1), Empty(2
                                                  //| ), Empty(3), Fill(0), Fill(1), Fill(2), Fill(3), Pour(0,1), Pour(0,2), Pour
                                                  //| (0,3), Pour(1,0), Pour(1,2), Pour(1,3), Pour(2,0), Pour(2,1), Pour(2,3), Po
                                                  //| ur(3,0), Pour(3,1), Pour(3,2))
  problem.pathSets.take(1).toList                 //> res1: List[Set[scala.course.week1.water.problem.Path]] = List(Set( --> Vect
                                                  //| or(0, 0, 0, 0)))
  problem.solution(12)                            //> res2: Stream[scala.course.week1.water.problem.Path] = Stream(Fill(2) Fill(0
                                                  //| ) Pour(0,3) Pour(2,3) --> Vector(0, 0, 12, 11), ?)
                              
}