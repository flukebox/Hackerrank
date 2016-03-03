package utilities;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

class Writer {
  static BufferedWriter writer;

  static void init(OutputStream output){
    writer = new BufferedWriter(new OutputStreamWriter(output));
  }
  
  static void writeInt(int n) throws IOException{
    writer.write(""+n+"\n");
  }

  static void writeLong(long n) throws IOException{
    writer.write(""+n+"\n");
  }

  static void writeDouble(double n) throws IOException{
    writer.write(""+n+"\n");
  }

  static void flush() throws IOException{
    writer.flush();
  }
}
