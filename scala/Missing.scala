import java.util.Scanner
import java.util.Hashtable

object Missing{
	def main(args:Array[String]){
    val sc = new Scanner(System.in);
    val A = new Hashtable[Integer, Integer]();
    var i = 0;
    for(i <- 0 to sc.nextInt()-1){
      val a = sc.nextInt();
      if(A.containsKey(a)){
        A.put(a, A.get(a)+1);
      }else{
        A.put(a, 1);
      }
    }

    i = 0;
    for(i <- 0 to sc.nextInt()-1){
      val a = sc.nextInt();
      if(A.containsKey(a)){
        A.put(a, A.get(a)-1);
      }else{
        A.put(a, -1);
      }
    }
    val keys = A.keySet().toArray(new Array[Integer](0)).sorted;
    i = 0;
    for(i <- 0 to keys.length-1){
      if(A.get(keys(i))<0){
        System.out.print(keys(i)+" ");
      }
    }
    sc.close();
  }
}