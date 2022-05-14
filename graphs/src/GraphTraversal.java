
import java.util.*;

public class GraphTraversal {


    public static void main(String[] args) {
        char[] points = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm'};
        Graph g = new Graph(points);

        g.addEdge(0, 9, 15);
        g.addEdge(1, 3, 5);
        g.addEdge(2, 9, 5);
        g.addEdge(2, 10, 7);
        g.addEdge(2, 5, 3);
        g.addEdge(5, 6, 2);
        g.addEdge(5, 7, 9);
        g.addEdge(5, 8, 1);
        g.addEdge(9, 11, 4);
        g.addEdge(11, 10, 8);

        System.out.println("keys= " + g.adjLists.keySet());
        for (Utils.Node x : g.adjLists.keySet()) {
            System.out.println("adj for " + x.character);
            System.out.println(g.adjLists.get(x));
        }
        System.out.println("PRIM'S MST ALGO:");
        System.out.println(PrimMST(g));

    }

//    static void DepthFirst(Graph g){
//        Set<Character> points =  g.map.keySet();
//        Stack<Character> s = new Stack<Character>();
//       for(char i : points){
//           if(!g.map.get(i)){
//               System.out.println("value:"+ i);
//               g.map.put(i,true);
//               s.push(i);
//               while(!s.isEmpty()){
//                   char t = s.pop();
//                   s.push(t);
//                   boolean flag = false;
//                   for(char j : g.adjLists.get(t)){
//                       if(!g.map.get(j)){
//                           flag = true;
//                           System.out.println(j);
//                           g.map.put(j,true);
//                           s.push(j);
//                           break;
//                       }
//                   }
//                   if(!flag)
//                       s.pop();
//               }
//
//           }
//       }
//    }

    static void BreadthFirst(Graph g) {

    }

    private static HashMap<Character, Character> PrimMST(Graph g) {
        Set<Utils.Node> vertices = g.adjLists.keySet();
        Utils.PQ q = new Utils.PQ(vertices.size(), new Utils.NodeComparator());
        HashMap<Character, Character> map = new HashMap<>();
        for (Utils.Node x : g.adjLists.keySet()) {
            map.put(x.character, x.character);
        }

        q.addAll(vertices);
        //randomly chosen statrting point
        Utils.Node t = q.poll();
        t.setKey(0);
        q.offer(t);

        while (q.size() > 0) {
            Utils.Node temp = q.poll();
            for (Utils.Edge x : g.adjLists.get(temp)) {
                t = x.getEnd();
                if (t.key > x.getWeight()) {
                    q.IncreaseKey(t, x.getWeight());
                    Character c = map.get(t.character);
                    map.replace(t.character, temp.character);
                }
            }
        }
        return map;
    }
}
