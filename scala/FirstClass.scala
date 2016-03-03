
import java.util.Scanner
import scala.Array.canBuildFrom

object Solution{
	def main(args:Array[String]){
		val sc = new Scanner(System.in);
		val n = sc.nextInt();
		val m = sc.nextInt();
		val k = Math.ceil(Math.log(n)/Math.log(2)).toInt;
		sc.nextLine();
		var data = sc.nextLine().split(" ").map(x=>Integer.parseInt(x));
		val table = Array.fill(n, k+1)(Integer.MAX_VALUE);
		var j = 0; var i = 0;
		for(j <- 0 to k){
			for( i <- 0 to n-1){
				if(j==0){
					table(i)(j) = data(i);
				}else{
					val ki = 1<<(j-1); 
          if((i+ki<n)) {
            table(i)(j)=Math.min(table(i)(j-1), table(i+ki)(j-1)); 
          }else{
            table(i)(j)=table(i)(j-1); 
          }
				}
			}
		}
		for( i <- 0 to m-1){
      val a = sc.nextInt();
      val b = sc.nextInt();;
      if(b==a){
        System.out.println(data(a));
      }else{
        val ki = Math.floor(Math.log(b-a)/Math.log(2)).toInt;
        val bi = (b-(1<<ki));
        System.out.println(Math.min(table(a)(ki), table(bi+1)(ki)));
      }
    }
	}
}