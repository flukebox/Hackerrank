  import scala._

import scala.collection.immutable.List
import scala.collection.immutable.Map
import scala.math.BigInt.int2bigInt
import scala.math.BigInt.long2bigInt

class Rational(n: BigInt, d: BigInt) {
	require(d != 0)
  private val g = gcd(n.abs, d.abs)
  val numer = n / g
  val denom = d / g
  
  def this(n: BigInt) = this(n, 1)
  
  def + (that: Rational): Rational =
  new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom
      )
  
  def + (i: BigInt): Rational =
  new Rational(numer + i * denom, denom)
  
  def - (that: Rational): Rational =
  new Rational(
      numer * that.denom - that.numer * denom,
      denom * that.denom
      )
  
  def - (i: BigInt): Rational =
  new Rational(numer - i * denom, denom)
  
  def * (that: Rational): Rational =
  new Rational(numer * that.numer, denom * that.denom)
  
  def * (i: BigInt): Rational =
  new Rational(numer * i, denom)
  
  def / (that: Rational): Rational =
  new Rational(numer * that.denom, denom * that.numer)
  
  def / (i: BigInt): Rational =
  new Rational(numer, denom * i)
  
  override def toString = "("+numer +"/"+ denom+")"

  private def gcd(a: BigInt, b: BigInt): BigInt = 
  if (b == 0) a else gcd(b, a % b)
}

class Poly(params:List[Rational]){
	val degree = params.length;
  var vals = new Array[Rational](degree);	
  
  /** Copy params **/
  for(i<-0 to params.length-1){
    vals(i)=params(i);
  }

  override def toString = {
  	var str = "";
      for(i<-0 to degree-1){ 
        str+=vals(i)+" n^"+i+(if(i!=degree-1)"+" else "");
      }
    str;
  }
  
  def * (that: Poly): Poly = {
    val len = this.degree+that.degree-1;
    val params = new Array[Rational](len);
    for(i <- 0 to degree-1){
      for(j <- 0 to that.degree-1){
        if(params(i+j)==null){
          params(i+j)=(vals(i)*that.vals(j));
        }else{
          params(i+j)+=(vals(i)*that.vals(j));
        }
      }
    }
    return new Poly(params.toList);
  }
  
  def * (that: Rational): Poly = {
    val len = this.degree;
    val params = new Array[Rational](len);
    for(i <- 0 to degree-1){
      params(i)=(vals(i)*that);
    }
    return new Poly(params.toList);
  }

  def + (that: Poly): Poly = {
    val len = math.max(this.degree, that.degree);
    val params = new Array[Rational](len);
    for(i <- 0 to len-1){
    	  if(i>=this.degree){
          params(i)=that.vals(i);
    	  }else if(i>=that.degree){
    	    params(i)=vals(i);
    	  }else{
    	    params(i)=(vals(i)+that.vals(i));
    	  }
    }
    return new Poly(params.toList);
  }
  
  def + (that: Rational): Poly = {
    val len = this.degree;
    val params = new Array[Rational](len);
    for(i <- 0 to degree-1){
      params(i)=vals(i);
    }
    params(0)+=that;
    return new Poly(params.toList);
  }

  def - (that: Poly): Poly = {
    val len = math.max(this.degree, that.degree);
    val params = new Array[Rational](len);
    for(i <- 0 to len-1){
        if(i>=this.degree){
          params(i)=that.vals(i);
        }else if(i>=that.degree){
          params(i)=vals(i);
        }else{
          params(i)=(vals(i)-that.vals(i));
        }
    }
    return new Poly(params.toList);
  }
  
  def - (that: Rational): Poly = {
    val len = this.degree;
    val params = new Array[Rational](len);
    for(i <- 0 to degree-1){
      params(i)=vals(i);
    }
    params(0)-=that;
    return new Poly(params.toList);
  }
  
  def / (that: Rational): Poly = {
    val len = this.degree;
    val params = new Array[Rational](len);
    for(i <- 0 to degree-1){
      params(i)=vals(i)/that;
    }
    return new Poly(params.toList);
  }
  def / (that: BigInt): Poly = this/new Rational(that);
}


object Expectation{
  def main(args:Array[String]){
    implicit def intToPoly(x: Int) = new Poly(List(new Rational(x)));
    val n = new Poly(List(new Rational(0),new Rational(1)));
    val n2 = n*n;
    val n3 = n*n2;
    val n4 = n2*n2;
    val n5 = n4*n
    val u1 = n*2/3;
    val u2 = u1*u1;
    val u3 = u2*u1;
    val u4 = u3*u1;
    val u5 = u4*u1;
    val x2 = n*2/3 + 2*( (n-1)*5/12 + (n-2)*9/20 + (n*(n-1)/2 -(2*n-3))*4/9);
    val x3 = n*2/3 + 6*((n-1)*5/12 + (n-2)*9/20 + (n*(n-1)/2 -(2*n-3))*4/9) + 6*((n-2)*4/15 + (n-3)*(n-4)*5/18 +(n-4)*(n-5)*3/10 +2*(n-3)*101/360+(n-4)*383/1260 +(n*(n-1)*(n-2)/6 - ((n-2) + (n-3)*(n-4) + (n-4)*(n-5) + 2*(n-3) + (n-4)))*8/27);
    val x4 = n*2/3 + 14*((n-1)*5/12 + (n-2)*9/20 + (n*(n-1)/2 -(2*n-3))*4/9) + 36*((n-2)*4/15 + (n-3)*(n-4)*5/18 +(n-4)*(n-5)*3/10 +2*(n-3)*101/360+(n-4)*383/1260 +(n*(n-1)*(n-2)/6 -((n-2) + (n-3)*(n-4) + (n-4)*(n-5) + 2*(n-3) + (n-4)))*8/27) + 24*(n*(n-1)*(n-2)*(n-3)/24 -((n-3)+3*(n-4)+ 3*(n-5) +(n-6)+ (n-4)*(n-5)*3/2 + (n-5)*(n-6)*6/2+ (n-5)*(n-6)*(n-7)*3/6+(n-6)*(n-7)*(n-8)*3/6 +(n-6)*(n-7)*3/2))*16/81 + 24*((n-3)*61/360 + 2*(n-4)*151/840+(n-4)*7/40+2*(n-5)*191/1008+(n-5)*17/90+(n-6)*4657/22680+(n-4)*(n-5)*8/45+(n-4)*(n-5)*25/288+(n-5)*(n-6)*101/270+(n-5)*(n-6)*3/16+(n-6)*(n-7)*383/1890+(n-6)*(n-7)*81/800+(n-6)*(n-7)*(n-8)/10+(n-5)*(n-6)*(n-7)*5/54);
    val x51 = n*2/3 + 30*((n-1)*5/12 + (n-2)*9/20 + (n*(n-1)/2 -(2*n-3))*4/9) + 150*((n-2)*4/15 + (n-3)*(n-4)*5/18 +(n-4)*(n-5)*3/10 +2*(n-3)*101/360+(n-4)*383/1260 +(n*(n-1)*(n-2)/6 -((n-2) + (n-3)*(n-4) + (n-4)*(n-5) + 2*(n-3) + (n-4)))*8/27) + 240*(n*(n-1)*(n-2)*(n-3)/24 -((n-3)+3*(n-4)+ 3*(n-5) +(n-6)+ (n-4)*(n-5)*3/2 + (n-5)*(n-6)*6/2+ (n-5)*(n-6)*(n-7)*3/6+(n-6)*(n-7)*(n-8)*3/6 +(n-6)*(n-7)*3/2))*16/81 + 240*((n-3)*61/360 + 2*(n-4)*151/840+(n-4)*7/40+2*(n-5)*191/1008+(n-5)*17/90+(n-6)*4657/22680+(n-4)*(n-5)*8/45+(n-4)*(n-5)*25/288+(n-5)*(n-6)*101/270+(n-5)*(n-6)*3/16+(n-6)*(n-7)*383/1890+(n-6)*(n-7)*81/800+(n-6)*(n-7)*(n-8)/10+(n-5)*(n-6)*(n-7)*5/54);
    val x52 = 120*((n*(n-1)*(n-2)*(n-3)*(n-4)/120 - ((n-4) + 4*(n-5) + 6*(n-6)+4*(n-7)+(n-8) +2*(n-5)*(n-6)+6*(n-6)*(n-7)+6*(n-7)*(n-8)+2*(n-8)*(n-9) +(n-6)*(n-7)*(n-8)+2*(n-7)*(n-8)*(n-9)+(n-8)*(n-9)*(n-10)+(n-7)*(n-8)*(n-9)*(n-10)/6 + (n-8)*(n-9)*(n-10)*(n-11)/6)))*32/243;
    var x53 = 120*((n-4)*34/315 + 2*(n-5)*1151/10080+ 2*(n-5)*113/1008+2*(n-6)*2203/18144+2*(n-6)*509/4320+(n-6)*5497/45360+(n-6)*893/7560 + 2*(n-7)*19289/151200 + 2*(n-7)*2903/22680
    		+(n-8)*115349/831600 +(n-5)*(n-6)*61/540+(n-5)*(n-6)*1/9
    		+3*(n-6)*(n-7)*151/1260+(n-6)*(n-7)*7/60+(n-6)*(n-7)*101/864+(n-6)*(n-7)*3/25
    		+2*(n-7)*(n-8)*191/1512+(n-7)*(n-8)*101/400+(n-7)*(n-8)*383/3024+(n-7)*(n-8)*17/135
    		+(n-8)*(n-9)*4657/34020+(n-8)*(n-9)*383/2800
    		+(n-6)*(n-7)*(n-8)*8/135+(n-6)*(n-7)*(n-8)*25/432
    		+(n-7)*(n-8)*(n-9)/8+(n-7)*(n-8)*(n-9)*101/810
    		+(n-8)*(n-9)*(n-10)*383/5670+(n-8)*(n-9)*(n-10)*27/400
    		+(n-7)*(n-8)*(n-9)*(n-10)*5/243 
    		+(n-8)*(n-9)*(n-10)*(n-11)/45);

    
    var initialMap = Map(
        ("0", new Rational(2,3)), 
        
        ("01", new Rational(5,12)),
        ("02", new Rational(9,20)),
        ("03", new Rational(4,9)),
        
        ("012", new Rational(4,15)),
        ("013", new Rational(101,360)),
        ("023", new Rational(101,360)),
        ("014", new Rational(5,18)),
        ("025", new Rational(3,10)),
        ("036", new Rational(8,27)),
        ("024", new Rational(383,1260)),
        ("035", new Rational(3,10)),
        ("034", new Rational(5,8)));
    
    var condMap = Map[String, Rational]();
    var X4 = Map[String, Rational]();
    var X5 = Map[String, Rational]();
    System.out.println("\nComputing Conditional Probabities");
    for((k,v) <- initialMap){
      if(k.length()==3){
        val subkey = k.substring(0, 2);
        val key = k.last+"|"+subkey;
        condMap  = condMap + ( key -> v/initialMap(subkey));
        System.out.println(key+"=>"+ condMap(key));
      }
    }
    
    System.out.println("\nComputing X4");
    val i = 0
        var SumX4 = new Rational(0);
    for(j <- i+1 to i+3){
      for(k <- j+1 to j+3){
        for(l <- k+1 to k+3){
          val min = List(j,k,l).min;
          val key = i+""+j+""+k+""+l; 
          val value = initialMap(key.substring(0,3))*condMap((l-min)+"|"+(j-min)+""+(k-min));
          SumX4+=value;
          System.out.println(key+"=>"+value);
          X4 = X4 + (key -> value);
        }
      }
    }
    System.out.println("\nSumX4="+SumX4);
    
    System.out.println("\nComputing X5");
    var SumX5 = new Rational(0);
    for(j <- i+1 to i+3){
      for(k <- j+1 to j+3){
        for(l <- k+1 to k+3){
          for(m <- l+1 to l+3){
            val min = List(m,k,l).min;
            val key = i+":"+j+":"+k+":"+l+":"+m; 
            val value = X4(i+""+j+""+k+""+l)*condMap((m-min)+"|"+(k-min)+""+(l-min));
            System.out.println(key+"=>"+value);
            X5 = X5 + (key -> value);
            SumX5+=value;
          }
        }
      }
    }
    System.out.println("\nSumX5="+SumX5);
    println(initialMap);
    println(condMap);
    println(X4);
    println(X5);
  }
  
  def getX(list:List[Int]):List[List[Int]]={
      var X = List[List[Int]]();
      for(p <- list.permutations){
        var x = List[Int](); 
        for( i <- 1 to p.length-2){
          if ((p(i-1) < p(i) && p(i) > p(i+1)) || (p(i-1) > p(i) && p(i) < p(i+1))){
            x = x :+ 1;
          }else{
            x = x :+ 0;
          } 
        }
        X = X :+ x
      }
      return X
  }
  
  def myfilter(p:List[Int], idx:List[Int]):Boolean={
      for ( i<- idx){
        if (!((p(i-1) < p(i) && p(i) > p(i+1)) || (p(i-1) > p(i) && p(i) < p(i+1))))  return false;
      }
      return true;
  }
  
  
  def solve(n:Int){
    var X = List[List[Int]]();
    val list = Range(1,n).toList;
    val hits = new Array[Int](n)
        System.out.println("Getting the permutations X");
    var totalOnes = 0;
    var totalIterations = 0;
    for(p <- list.permutations){
      var x = List[Int](); 
      for( i <- 1 to p.length-2){
        if ((p(i-1) < p(i) && p(i) > p(i+1)) || (p(i-1) > p(i) && p(i) < p(i+1))){
          x = x :+ 1;
        }else{
          x = x :+ 0;
        } 
      }
      hits(x.count(_ == 1))+=1;
      totalOnes += x.count(_ == 1);
      totalIterations+=1;
      X = X :+ x
    }
    System.out.println("totalOnes="+totalOnes+", totalIterations="+totalIterations);
    System.out.println("Done with the permutations..");
    System.out.println("Getting X^2 ");
    var X1 = 0L;
    var X2 = 0L;
    var X3 = 0L;
    var X4 = 0L;
    var X5 = 0L;
    var Net = 0L
    for( i <- 0 to hits.length-1){
      System.out.println("i="+i+", hits("+i+")="+hits(i));
      X1 +=i*hits(i)
      X2 +=i*i*hits(i)
      X3 +=i*i*i*hits(i)
      X4 +=i*i*i*i*hits(i)
      X5 +=i*i*i*i*i*hits(i)
      Net += hits(i)
    }
    System.out.println("X1="+X1+", Net="+Net+" M1="+new Rational(X1,1)/Net);
    System.out.println("X2="+X2+", Net="+Net+" M2="+new Rational(X2,1)/Net);
    System.out.println("X3="+X3+", Net="+Net+" M3="+new Rational(X3,1)/Net);
    System.out.println("X4="+X4+", Net="+Net+" M4="+new Rational(X4,1)/Net);
    System.out.println("X5="+X5+", Net="+Net+" M5="+new Rational(X5,1)/Net);
  }
}