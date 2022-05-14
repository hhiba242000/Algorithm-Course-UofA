import jdk.jshell.execution.Util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Graph {
    private Object[] p;
    public Map<Utils.Node,LinkedList<Utils.Edge> >adjLists;
    public Map<Utils.Node,Boolean> map;

    Graph(char[] points) {
        p= new Object[points.length];
        int j =0;
        adjLists = new HashMap<>();
        for (char i :points) {
            LinkedList<Utils.Edge> temp1 = new LinkedList<Utils.Edge>();
            p[j] = new Utils.Node(i,Integer.MAX_VALUE);
            adjLists.put((Utils.Node) p[j], temp1);
            j++;
        }

        System.out.println(Arrays.toString(Arrays.stream(p).toArray()));
    }

    void addEdge(int src, int dest,int weight) {
        Utils.Node srcNode = (Utils.Node) p[src];
        LinkedList<Utils.Edge> temp2 = adjLists.get(srcNode);
        if(temp2 == null) {
            System.out.println("list is empty for "+srcNode.character);
        temp2 = new LinkedList<Utils.Edge>();
        }
        temp2.add(new Utils.Edge((Utils.Node) p[dest], weight));
        adjLists.replace((Utils.Node) p[src],temp2);
    }
}
