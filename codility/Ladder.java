public class Ladder {

   public int[] solution(int[] A, int[] B){
       int N = A.length;
       int[] out = new int[N];
       for(int i = 0; i < N; i++){
          out[i] = getFn(A[i]+1, 1 << B[i]);
       }
       return out;
   }

   public long[] multiply(long[] m1, long[] m2, int m){
       long [] matrix = new long[4];
       matrix[0] = (m1[0]*m2[0] + m1[1]*m2[2])%m;
       matrix[1] = (m1[0]*m2[1] + m1[1]*m2[3])%m;
       matrix[2] = (m1[2]*m2[0] + m1[3]*m2[2])%m;
       matrix[3] = (m1[2]*m2[1] + m1[3]*m2[3])%m;
       return matrix;
   }

   public int getFn(int n, int m){
       if (n == 0) return 1;
       long [] result = {1, 0, 0, 1}; // Identify matrix
       long [] matrix = {1, 1, 1, 0};
       while (n > 0){
           if ((n & 1) == 1){
               result = multiply(result, matrix, m);
           }
           n >>= 1;
           matrix = multiply(matrix, matrix, m);
       }
       return (int) result[1];
   }
}
