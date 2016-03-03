package jan14;
import java.util.Scanner;

public class CubeSummation {
  private enum QUERY_TYPE { UPDATE, QUERY};
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int tc = sc.nextInt();
    while(tc-->0){
      int n = sc.nextInt();
      int m = sc.nextInt();
      long[][][] aux = new long[n+1][n+1][n+1];
      for(int i=0; i<m; i++){
        switch(QUERY_TYPE.valueOf(sc.next())){
          case QUERY: {
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int z1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();
            int z2 = sc.nextInt();
            System.out.println(get(aux, x1-1, y1-1, z1-1, x2, y2, z2));
            break;
          }
          case UPDATE:{
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();
            int w = sc.nextInt();
            long v = get(aux, x-1, y-1, z-1, x, y, z);
            update(aux, new int[]{x,y,z}, (int)(w-v), n);
            break;
          }
        }
      }
    }
    sc.close();
   }


   // point update
   static void update(long[][][] aux, int[] index, int value, int size){
     int k1 = index[0];
     while(k1<=size){
       int k2 = index[1];
       while(k2<=size){
         int k3 = index[2];
         while(k3<=size){
           aux[k1][k2][k3]+=value;
           k3 += (k3 & -k3);
         }
         k2 += (k2 & -k2);
       }
       k1 += (k1 & -k1);
     }
   }

   // point query -- till index
   static long get(long[][][] aux, int[] index){
     long ret = 0;
     int k1 = index[0];
     while(k1>0){
       int k2 = index[1];
       while(k2>0){
         int k3 = index[2];
         while(k3>0){
           ret += aux[k1][k2][k3];
           k3 -= (k3 & -k3);
         }
         k2 -= (k2 & -k2);
       }
       k1 -=(k1 & -k1);
     }
     return ret;
   }
   
   // range query -- from -- to
   static long get(long[][][] aux, int x1, int y1, int z1, int x2, int y2, int z2){
     return ( get(aux, new int[]{x2, y2, z2})
             -get(aux, new int[]{x1, y2, z2})
             -get(aux, new int[]{x2, y1, z2})
             -get(aux, new int[]{x2, y2, z1})
             +get(aux, new int[]{x1, y1, z2})
             +get(aux, new int[]{x1, y2, z1})
             +get(aux, new int[]{x2, y1, z1})
             -get(aux, new int[]{x1, y1, z1}));
   }
}

