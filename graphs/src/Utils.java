import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Utils {
    static class Node {
        int key;
        char character;


        Node(char c, int k) {
            key = k;
            character = c;
        }

        public boolean equals(Node obj) {
            return this.character == obj.character ;
        }

        public void setKey(int key) {
            this.key = key;
        }
    }

    static class NodeComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.key - o2.key;
        }
    }

    static class Edge{
        Node end;
        int weight;

        Edge(Node e,int w){
            end=e;
            weight=w;
        }

        public Node getEnd() {
            return end;
        }

        public void setEnd(Node end) {
            this.end = end;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

    }

    static class PQ extends PriorityQueue<Utils.Node>{

        PQ(int size, Comparator<Node> comparator){
            super(size,comparator);
        }



        void IncreaseKey(Node x, int key){
            Iterator<Node> iter = this.iterator();
            Node element;
            int i=0;
            while(iter.hasNext()){
                element = iter.next();
                i++;
                if(element.equals(x)){
                    this.remove(element);
                    break;
                }
            }
            x.setKey(key);
            this.offer(x);
        }
    }

    static class Tuple{
        int x;
        int y;
    }
    //implement priority queue that allows increase/decrease key

}
