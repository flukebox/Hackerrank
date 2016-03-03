package week1

import java.util.Scanner

object Day1 {
	def main(args:Array[String]){
    val sc = new Scanner(System.in)
    var xor = (sc.nextInt()^sc.nextInt())
    var msb = 0;
    while(xor>0){
    	xor = xor >> 1;
    	msb +=1;
    }
    println((1<<msb)-1)
	}
}