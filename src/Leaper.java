import java.util.*;

public class Leaper {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int numOfTestCase = sc.nextInt();
    for(int i=0; i<numOfTestCase; i++){
      int n = sc.nextInt();
      int m = sc.nextInt();
      int s = sc.nextInt();
      int sX = 0;
      int sY = 0;
      HashMap<String, String> board = new HashMap<String, String>();
      for(int j=0; j<n; j++){
        String line = sc.next();
        for(int k=0; k<n; k++){
          int currChar = line.charAt(k);
          if(currChar == '.') continue; 
          else if(currChar =='L'){
            sX = j; sY = k;
          }else if (currChar == 'P'){
            board.put(j+":"+k, "P");
          }
        }
      }
      System.out.println(totalMoves(sX, sY, n, m, s, board));
    }
    sc.close();
  }
  
  
  static int totalMoves(int sX, int sY,  int n, int m, int s, HashMap<String, String> board){
    int moves = 0;
    for(int i=0; i<=s; i++){
      for(int x=0, y=i-x; x<=i; x++, y=i-x){
        if(x==0 && y==0){
          moves = (m==1)?((moves+1)%1000000007):((moves+totalMoves(sX, sY, n, m-1, s, board))%1000000007);
        }else if(x==0){
          int cX=sX, cY=sY-y;
          if(cY>=0 && !board.containsKey(cX+":"+cY)){
            moves = (m==1)?((moves+1)%1000000007):((moves+totalMoves(cX, cY, n, m-1, s, board))%1000000007);
          }
          cY=sY+y;
          if(cY<n && !board.containsKey(cX+":"+cY)){
            moves = (m==1)?((moves+1)%1000000007):((moves+totalMoves(cX, cY, n, m-1, s, board))%1000000007);
          }

        }else if(y==0){
          int cX=sX-x, cY=sY;
          if(cX>=0 && !board.containsKey(cX+":"+cY)){
            moves = (m==1)?((moves+1)%1000000007):((moves+totalMoves(cX, cY, n, m-1, s, board))%1000000007);
          }
          
          cX=sX+x;
          if(cX<n && !board.containsKey(cX+":"+cY)){
            moves = (m==1)?((moves+1)%1000000007):((moves+totalMoves(cX, cY, n, m-1, s, board))%1000000007);
          }
        }else{
          int cX=sX-x, cY=sY-y;
          if((cX>=0) && (cY>=0) && !board.containsKey(cX+":"+cY)){
            moves = (m==1)?((moves+1)%1000000007):((moves+totalMoves(cX, cY, n, m-1, s, board))%1000000007);
          }
          
          cX=sX-x; cY=sY+y;
          if((cX>=0) && (cY<n) && !board.containsKey(cX+":"+cY)){
            moves = (m==1)?((moves+1)%1000000007):((moves+totalMoves(cX, cY, n, m-1, s, board))%1000000007);
          }

          cX=sX+x; cY=sY-y;
          if((cX<n) && (cY>=0) && !board.containsKey(cX+":"+cY)){
            moves = (m==1)?((moves+1)%1000000007):((moves+totalMoves(cX, cY, n, m-1, s, board))%1000000007);
          }
          
          cX=sX+x; cY=sY+y;
          if((cX<n) && (cY<n) && !board.containsKey(cX+":"+cY)){
            moves = (m==1)?((moves+1)%1000000007):((moves+totalMoves(cX, cY, n, m-1, s, board))%1000000007);
          }
        }
      }
    }
    return moves%1000000007;
  }
  
}