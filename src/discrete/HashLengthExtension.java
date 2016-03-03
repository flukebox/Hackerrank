package discrete;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class HashLengthExtension {
  public static void main(String[] args) throws Exception{
    Scanner sc = new Scanner(System.in);
    int tc = sc.nextInt();
    while(tc-->0){
      int sl = sc.nextInt()%64;
      String msg = sc.next();
      String digest = sc.next();
      String attack = sc.next();
      int blockTodo = ((sl+msg.length()+8)>>>6)+1;
      int msglen = (blockTodo<<6)+attack.length();
      byte[] attackBytes = attack.getBytes();
      ByteBuffer bb = ByteBuffer.allocate(msglen);
      int offset = blockTodo<<6;
      for(int i=0; i<attackBytes.length; i++){
        bb.put(offset+i, attackBytes[i]);
      }
      System.out.println((msg+attack)+" "+MD5.toHexString(MD5.computeMD5(bb.array(), digest, blockTodo)));
    }
    sc.close();
  }
}

class MD5{
  private static final int INIT_A = 0x67452301;
  private static final int INIT_B = (int)0xEFCDAB89L;
  private static final int INIT_C = (int)0x98BADCFEL;
  private static final int INIT_D = 0x10325476;
  
  private static final int[] SHIFT_AMTS = {
    7, 12, 17, 22,
    5,  9, 14, 20,
    4, 11, 16, 23,
    6, 10, 15, 21
  };
  
  private static final int[] TABLE_T = new int[64];
  static{
    for (int i = 0; i < 64; i++)
      TABLE_T[i] = (int)(long)((1L << 32) * Math.abs(Math.sin(i + 1)));
  }
  
  public static byte[] computeMD5(byte[] message, String digest, int skipToBlock){
    int messageLenBytes = message.length;
    int numBlocks = ((messageLenBytes + 8) >>> 6) + 1;
    int totalLen = numBlocks << 6;
    byte[] paddingBytes = new byte[totalLen - messageLenBytes];
    paddingBytes[0] = (byte)0x80;
    
    long messageLenBits = (long)messageLenBytes << 3;
    for (int i = 0; i < 8; i++){
      paddingBytes[paddingBytes.length - 8 + i] = (byte)messageLenBits;
      messageLenBits >>>= 8;
    }
    int A=fromHexString(digest.substring(0,8)),
        B=fromHexString(digest.substring(8,16)),
        C=fromHexString(digest.substring(16,24)),
        D=fromHexString(digest.substring(24,32));
    
    int a = INIT_A;
    int b = INIT_B;
    int c = INIT_C;
    int d = INIT_D;
    int[] buffer = new int[16];
    for (int i = 0; i < numBlocks; i ++){
      int index = i << 6;
      for (int j = 0; j < 64; j++, index++)
        buffer[j >>> 2] = ((int)((index < messageLenBytes) ? message[index] : paddingBytes[index - messageLenBytes]) << 24) | (buffer[j >>> 2] >>> 8);
      if(i==skipToBlock){a=A;b=B;c=C;d=D;}
      int originalA = a;
      int originalB = b;
      int originalC = c;
      int originalD = d;
      for (int j = 0; j < 64; j++){
        int div16 = j >>> 4;
        int f = 0;
        int bufferIndex = j;
        switch (div16){
          case 0:
            f = (b & c) | (~b & d);
            break;
            
          case 1:
            f = (b & d) | (c & ~d);
            bufferIndex = (bufferIndex * 5 + 1) & 0x0F;
            break;
            
          case 2:
            f = b ^ c ^ d;
            bufferIndex = (bufferIndex * 3 + 5) & 0x0F;
            break;
            
          case 3:
            f = c ^ (b | ~d);
            bufferIndex = (bufferIndex * 7) & 0x0F;
            break;
        }
        int temp = b + Integer.rotateLeft(a + f + buffer[bufferIndex] + TABLE_T[j], SHIFT_AMTS[(div16 << 2) | (j & 3)]);
        a = d;
        d = c;
        c = b;
        b = temp;
      }
      a += originalA;
      b += originalB;
      c += originalC;
      d += originalD;
    }
    
    byte[] md5 = new byte[16];
    int count = 0;
    for (int i = 0; i < 4; i++){
      int n = (i == 0) ? a : ((i == 1) ? b : ((i == 2) ? c : d));
      for (int j = 0; j < 4; j++){
        md5[count++] = (byte)n;
        n >>>= 8;
      }
    }
    return md5;
  }

  public static String toHexString(byte[] b){
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < b.length; i++){
      sb.append(String.format("%02x", b[i] & 0xFF));
    }
    return sb.toString();
  }
  

  public static int fromHexString(String hex){
    int n = 0;
    int offset = 0;
    for(int i = 0; i < hex.length(); i+=2){
      n |= (Integer.parseInt(hex.substring(i,i+2),16)<<(offset++)*8);
    }
    return n;
  }
}
