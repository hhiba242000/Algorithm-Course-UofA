import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Graph {
    public Map<Integer,LinkedList<Integer> >adjLists;
    public Map<Integer,Boolean> map;
    LinkedList<Integer> temp1;

    Graph(int[] points) {
        adjLists = new HashMap<Integer,LinkedList<Integer>>();
        map = new HashMap<Integer,Boolean>();

        for (int i :points) {
            temp1 = new LinkedList<Integer>();
            adjLists.put(i, temp1);
            map.put(i,false) ;
        }
    }

    void addEdge(int src, int dest) {
        LinkedList<Integer> temp2 = adjLists.get(src);
        temp2.add(dest);
        adjLists.put(src,temp2);
    }
}
