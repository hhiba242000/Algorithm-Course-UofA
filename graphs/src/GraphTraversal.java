import java.util.*;

public class GraphTraversal {
    public static void demo(String[] args) {
        int[] points = {2,1,9,10,11,4,8,5,6,7};
        Graph g = new Graph(points);

        g.addEdge(2, 9);
        g.addEdge(2, 10);
        g.addEdge(2, 5);
        g.addEdge(5, 6);
        g.addEdge(5, 7);
        g.addEdge(5, 8);
        g.addEdge(9, 11);
        g.addEdge(11, 10);

        System.out.println("Following is Depth First Traversal");
        DepthFirst(g);

    }

    static void DepthFirst(Graph g){
        Set<Integer> points =  g.map.keySet();
        Stack<Integer> s = new Stack<Integer>();
       for(int i : points){
           if(!g.map.get(i)){
               System.out.println("value:"+ i);
               g.map.put(i,true);
               s.push(i);
               while(!s.isEmpty()){
                   int t = s.pop();
                   s.push(t);
                   boolean flag = false;
                   for(int j : g.adjLists.get(t)){
                       if(!g.map.get(j)){
                           flag = true;
                           System.out.println(j);
                           g.map.put(j,true);
                           s.push(j);
                           break;
                       }
                   }
                   if(!flag)
                       s.pop();
               }

           }
       }
    }

    static void BreadthFirst(Graph g){
        
    }
}
