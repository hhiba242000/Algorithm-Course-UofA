import java.util.*;

//given array of characters and their frequencies, construct their huffman codes
public class HuffmanCodes {

    private static class Node{
        int frequency;
        char character;
        Node left;
        Node right;

        Node(char c,int f){
            frequency=f;
            character=c;
            left=null;
            right=null;
        }

    }

    private static class NodeComparator implements Comparator<Node>{

        @Override
        public int compare(Node o1, Node o2) {
            return o1.frequency - o2.frequency;
        }
    }
    public static void main(String[] args) {
        Node[] arr = new Node[8];
        arr[0]= new Node(' ', 2);
        arr[1]= new Node('o', 3);
        arr[2]= new Node('e', 1);
        arr[3]= new Node('h', 1);
        arr[4]= new Node('p', 1);
        arr[5]= new Node('r', 1);
        arr[7] =new Node('s', 1);
        arr[6]= new Node('g', 3);



        Node ans = HuffmanCompression(arr);
        HashMap<Character,String> map = new HashMap<>();
        TraversTree(ans,map,"");
        System.out.println(map);

        String input = Compress("go go gophers",map);
        System.out.println("decoded data is : "+HuffmanDecompression(input,ans));


    }

    private static String Compress(String data, HashMap<Character, String> map) {
        String s="";
        for(int i=0;i<data.length();i++){
            s+=map.get(data.charAt(i));
        }
        return s;
    }

    private static Node HuffmanCompression(Node[] arr){
        PriorityQueue<Node> q = new PriorityQueue<>(arr.length,new NodeComparator());
        for (Node x: arr)
            q.add(x);


        while(q.size() >1){
            Node l = q.poll();
            System.out.println(l.character + " l is  "+l.frequency);
            Node r = q.poll();
            System.out.println(r.character + " r is  "+r.frequency);


            //chose any arbitrary character that is not in the input
            Node n = new Node('*',l.frequency+r.frequency);
            n.left = l;
            n.right = r;

            q.add(n);
        }

        return q.poll();
    }



    private static void TraversTree(Node root, HashMap<Character, String> map,String s) {
        if(root.left == null && root.right==null ){
            map.put(root.character, s);
            return ;
        }
        assert root.left != null;
        TraversTree(root.left,map,s+"0");
        TraversTree(root.right,map,s+"1");
    }

    private static String HuffmanDecompression(String encodedData, Node huffmanTree){
        int size = encodedData.length();
        String data = "";
        Node temp = huffmanTree;
        for(int i =0;i<size;i++) {
            char c = encodedData.charAt(i);
                if (c == '1') {
                    temp = temp.right;
                } else
                    temp = temp.left;

                if(temp.left == null && temp.right==null) {
                    data = data + temp.character;
                    temp = huffmanTree;
                }
        }
        return data;
    }
}
