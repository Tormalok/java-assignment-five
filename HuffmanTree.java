import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

class Node {
    char ch;
    int freq;
    Node left = null, right = null;

    Node(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
    }

    Node(char ch, int freq, Node left, Node right) {
        this.ch = ch;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }
}

public class HuffmanTree {
    public static Comparator<Node> nodeComparator = new Comparator<Node>() {
        @Override
        public int compare(Node n1, Node n2) {
            return n1.freq - n2.freq;
        }
    };

    public static Node buildHuffmanTree(char[] charArray, int[] charFreq) {
        PriorityQueue<Node> pq = new PriorityQueue<>(charArray.length, nodeComparator);

        for (int i = 0; i < charArray.length; i++) {
            pq.add(new Node(charArray[i], charFreq[i]));
        }

        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            int sum = left.freq + right.freq;
            pq.add(new Node('\0', sum, left, right));
        }

        return pq.poll();
    }

    public static void encode(Node root, String str, Map<Character, String> huffmanCode) {
        if (root == null)
            return;

        if (root.left == null && root.right == null) {
            huffmanCode.put(root.ch, str);
        }

        encode(root.left, str + "0", huffmanCode);
        encode(root.right, str + "1", huffmanCode);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the characters:");
        String characters = sc.nextLine();
        char[] charArray = characters.toCharArray();

        int[] charFreq = new int[charArray.length];
        System.out.println("Enter the frequencies:");
        for (int i = 0; i < charArray.length; i++) {
            charFreq[i] = sc.nextInt();
        }

        Node root = buildHuffmanTree(charArray, charFreq);

        Map<Character, String> huffmanCode = new HashMap<>();
        encode(root, "", huffmanCode);

        System.out.println("Huffman Codes are: ");
        for (Map.Entry<Character, String> entry : huffmanCode.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        sc.close();
    }
}
