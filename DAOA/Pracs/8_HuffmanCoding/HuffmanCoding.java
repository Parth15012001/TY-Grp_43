import java.io.*;
import java.util.*;
import java.lang.*;

class Node{
    int freq;
    char symbol;
    Node right, left;

    Node(char symbol, int freq){
        this.symbol = symbol;
        this.freq = freq;
        this.left = null;
        this.right = null;
    }
    Node(){
        this.left = null;
        this.right = null;
    }
}

class CustomComparator implements Comparator<Node> {
    public int compare(Node a1, Node a2){ 
        return a1.freq - a2.freq;
    }
}

public class HuffmanCoding {
    static PriorityQueue<Node> minHeap;
    static HashMap<Character, String> Code = new HashMap<Character, String>();
    public static void main(String[] args) {
        char[] symbolArray = {'a','b','c','d','e','f'};
        int[] symbolFreq = {5,9,12,13,16,45 };
        minHeap = new PriorityQueue<Node>(symbolArray.length, new CustomComparator());

        for(int i = 0;i<symbolArray.length; i++){
            Node temp = new Node(symbolArray[i], symbolFreq[i]);
            minHeap.add(temp);
        }


        Node HuffmanTree = null;

        while(minHeap.size()>1){
            Node left = minHeap.poll();
            Node right = minHeap.poll();
            Node parent = new Node('*',left.freq+right.freq);
            parent.left = left;
            parent.right = right;
            HuffmanTree = parent;
            minHeap.add(parent);
        }
        getHuffmanCodes(HuffmanTree, "");
        System.out.println(Code);

    }

    public static void getHuffmanCodes(Node root, String code){
        if(root.right == null && root.left == null && Character.isLetter(root.symbol) )
            Code.put(root.symbol, code);
        else{
            if(root.right!=null)
                getHuffmanCodes(root.right, code+"1");
            if(root.left!=null)
                getHuffmanCodes(root.left, code+"0");
        }
    }

}
