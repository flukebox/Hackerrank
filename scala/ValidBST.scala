import java.util.Scanner
import scala.collection.immutable.List
import scala.Array.canBuildFrom

object ValidBST{
  def main(args:Array[String]){
  	val pobst = List[Int]();
    val sc = new Scanner(System.in);
    var i = 0
    for(i <- 1 to sc.nextInt()){
      val n = sc.nextInt();
      sc.nextLine();
      var pot = sc.nextLine().split(" ").map(x=>Integer.parseInt(x)).toList;
      if(isValidBST(pot)){
        System.out.println("YES");
      }else{
        System.out.println("NO");
      }
    }
  }
  /**
   *  3 2 1 5 4 6
   */
  def isValidBST(po:List[Int]):Boolean={
    if(po.isEmpty) true;
    else{
      val idx = 1;
      var left = List[Int]();
      var right = List[Int]();
      for(idx <- 1 to po.length-1){
        if((po(0) > po(idx)) && right.isEmpty){
          // left child
        	left=left:+po(idx);
        }else if(po(0) < po(idx)){
          // right child
          right=right:+po(idx);
        }else return false;
      }
      isValidBST(left) && isValidBST(right);
    } 
  }
}