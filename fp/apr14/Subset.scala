import java.util.Scanner

object Subset{
  
  def main(args:Array[String]){
    val sc = new Scanner(System.in)
    sc.nextLine() // skip
    val sums = sc.nextLine().split(" ").map(x=>x.toLong).sorted.reverse.scan(0L)(_+_)
    println((1 to sc.nextInt()).map( x => { 
      val v = sc.nextLong()
          val sidx = binarySearch(sums, v)
          val idx = if(sidx < 0) -sidx -1 else sidx
          if( idx == 0 || idx == sums.length) -1 else idx
    }).mkString("\n"))
  }
  
  def binarySearch(list:Array[Long], key:Long):Int={
      var low = 0;
      var high = list.length-1;
      while (low <= high) {
        val mid = (low + high) >>> 1;
          val midVal = list(mid)
              val cmp = midVal - key
              if (cmp < 0)
                low = mid + 1;
              else if (cmp > 0)
                high = mid - 1;
              else
                return mid; // key found
      }
      return -(low + 1);  // key not found
  }
}