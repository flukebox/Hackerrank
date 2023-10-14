package Onemonth.week1;
public class FlippingBits {
    public static long flippingBits(long n) {
        // Write your code here
        long xor = 1;
        for(int i = 0; i < 32; i++){
            n = n^xor;
            xor =  xor << 1;
        }
        return n;
    }
}
