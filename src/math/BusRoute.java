import java.util.*;

public class BusRoute {
  public static void main(String[] args) {
    // scanner to read input from stdin
    Scanner sc = new Scanner(System.in);
    // total number of test cases
    int tc = sc.nextInt();
    Route[] routes = new Route[tc];
    for(int i=0; i<tc; i++){
        // initialize routes
        routes[i] = new Route(sc.nextInt(), sc.nextInt());
    }
    Set<Route> finalRoutes = new HashSet();
    for(int i=0; i<tc; i++){
      Route route = routes[i];
      for(int j=1; j<tc; j++){
          if(overlap(route, routes[j])){
              route = merge(routes[i], routes[j]);
          }
      }
      finalRoutes.add(route);
    }
    System.out.println(finalRoutes.size());
    sc.close();
  }

  private static class Route {
      int a;
      int b;
      public Route(int a, int b){
          this.a = a;
          this.b = b;
      }

      @Override
      public boolean equals(Object o) {
          if (this == o) return true;
          if (o == null || getClass() != o.getClass()) return false;
          Route point = (Route) o;
          return a == point.a &&
                  b == point.b;
      }

      @Override
      public int hashCode() {
          return Objects.hash(a, b);
      }
  }

  // check whether two routes overlap or not
  private static boolean overlap(Route p1, Route p2) {
      // two routes A1(a, b) and A2(c, d) overlap iff b>=c && d >= a
      //   i) a-----------b
      //             c--------d
      //   ii) a-----------b
      //         c-----d
      //   iii)     a-----------b
      //         c-----d
      if(p1.b >= p2.a && p2.b >= p1.a){
          return true;
      } else  return false;
  }

  private static Route merge(Route p1, Route p2){
      return new Route(Math.max(p1.a, p2.a), Math.max(p1.b, p2.b));
  }
}
