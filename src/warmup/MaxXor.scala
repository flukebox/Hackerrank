package warmup

object MaxXor {
  def main(args:Array[String]){
  	val l = readLine.toInt
  	val r = readLine.toInt

  	if(l==r) {
  		println(0) 
  	}else{
    	var m1 = 1 << 12
    	var m2 = 1 << 12
    	
    	while( (m1&l) == 0 ) m1 = m1>>1
      while( (m2&r) == 0 ) m2 = m2>>1
     
      println(
      		(m1, m2) match {
      			case (m, m_) if m == m_ => {
      			      while( ((m1&l)^(m1&r)) == 0){
      			        m1 = m1 >> 1;
      			      }
      			      (m1<<1)-1
      			  }
      			case (m, m_) if m > m_ => {
      			  (m1<<1)-1
      			}
      			case _ => {
      			  (m2<<1)-1
      			}
      		}
        )
  	}
  }
}
