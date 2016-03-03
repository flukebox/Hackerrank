import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HTMLDetect {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    HTMLDetect hd = new HTMLDetect();
    int n = sc.nextInt();
    while(n-->0){
      hd.extractTags(sc.next());
    }
    sc.close();
  }
  
  TreeMap<String, TreeSet<String>> tags = new TreeMap<String, TreeSet<String>>();
  Pattern pattern = Pattern.compile("<[^/>]*>");
  public void extractTags(String line){
    Matcher matcher = pattern.matcher(line);
    while(matcher.find()){
      System.out.println(matcher.group());
    }
  }

  public void printTags(){
    for(Entry<String, TreeSet<String>> entry:tags.entrySet()){
      System.out.print(entry.getKey()+":");
      Iterator<String> iterator = entry.getValue().iterator();
      while(iterator.hasNext())
        System.out.print(iterator.next()+(iterator.hasNext()?",":""));
    }
  }
}