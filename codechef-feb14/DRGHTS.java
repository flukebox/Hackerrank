import java.util.*;
import java.util.Map.Entry;

class DRGHTS {
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();
    sc.nextLine();
    byte[] windows = new byte[n];
    String[] str = sc.nextLine().split(" "); 
    for(int i=0; i<n; i++){
      windows[i]= Byte.parseByte(str[i]);
    }
    HashMap<Integer,HashSet<Integer>> links = new HashMap<Integer,HashSet<Integer>>();
    for(int i=0; i<m; i++){
      int fr = sc.nextInt()-1;
      int to = sc.nextInt()-1;
      // add to links
      if(links.containsKey(to)){
        links.get(to).add(fr);
      }else{
        HashSet<Integer> set = new HashSet<Integer>();
        set.add(fr);
        links.put(to, set);
      }
      // add to links
      if(links.containsKey(fr)){
        links.get(fr).add(to);
      }else{
        HashSet<Integer> set = new HashSet<Integer>();
        set.add(to);
        links.put(fr, set);
      }
    }
    
    long totalpair=0, totalnodes=0;
    byte[] visited = new byte[n];
    for(Entry<Integer, HashSet<Integer>> entry:links.entrySet()){
      long currnodes=0;
      if(visited[entry.getKey()]==0){
        visited[entry.getKey()]=1;
        if(windows[entry.getKey()]==1){
          currnodes++;
        }
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.addAll(entry.getValue());
        while(!queue.isEmpty()){
          int link=queue.pop();
          if(visited[link]==0){
            visited[link]=1;
            if(windows[link]==1){
              currnodes++;
            }
            queue.addAll(links.get(link));
          }
        }
        
        
        if(currnodes>=2){
          totalnodes+=currnodes;
          totalpair+=(currnodes*(currnodes-1))/2;
        }
      }
    }
    System.out.println(totalpair+" "+totalnodes);
    sc.close();
  }
  
}
