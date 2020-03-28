package design.string;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanEncoding {

    class Node {
        Node left; // 1
        Node right; // 0
        Character ch;
        int weight;

        public Node(Character ch, int weight) {
            this.ch = ch;
            this.weight = weight;
        }

        public Node(Character ch, int weight, Node left, Node right) {
            this(ch, weight);
            this.left = left;
            this.right = right;
        }
    }

    public Map<Character, String> makeDictionary(String str) {
        Map<Character, Integer> count = new HashMap<>();
        for (char ch : str.toCharArray()) {
            count.put(ch, count.getOrDefault(ch, 0) + 1);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        for (Map.Entry<Character, Integer> entry : count.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }

        Node root = null;
        while (pq.size() > 0) {
            root = pq.poll();
            if (pq.size() > 0) {
                Node second = pq.poll();
                pq.add(new Node(null, root.weight + second.weight, root, second));
            }
        }

        Map<Character, String> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        traverseTree(root, sb, map);

        return map;
    }

    public Map<String, Character> makeReverseDictionary(String str) {
        Map<String, Character> result = new HashMap<>();
        Map<Character, String> map = makeDictionary(str);

        for (Map.Entry<Character, String> entry : map.entrySet()) {
            result.put(entry.getValue(), entry.getKey());
        }

        return result;
    }

    private void traverseTree(Node node, StringBuilder sb, Map<Character, String> map) {
        if (node.ch != null) {
            map.put(node.ch, sb.toString());
        } else {
            sb.append("1");
            traverseTree(node.left, sb, map);
            sb.deleteCharAt(sb.length() - 1);
            sb.append("0");
            traverseTree(node.right, sb, map);
            sb.deleteCharAt(sb.length() - 1);
        }
    }


    public String encode(String str) {
        StringBuilder result = new StringBuilder();
        Map<Character, String> dict = makeDictionary(str);
        for (char ch : str.toCharArray()) {
            result.append(dict.get(ch));
        }
        return result.toString();
    }

    public String decode(String str, Map<String, Character> map) {
        StringBuilder result = new StringBuilder();
        StringBuilder buffer = new StringBuilder();
        for (char ch : str.toCharArray()) {
            buffer.append(ch);
            if (map.containsKey(buffer.toString())) {
                result.append(map.get(buffer.toString()));
                buffer.delete(0, buffer.length());
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        HuffmanEncoding huffman = new HuffmanEncoding();
        System.out.println("ABBCBABBABDD : Dict (" + huffman.makeDictionary("ABBCBABBABDD").toString() + ")");
        System.out.println("ABBCBABBABDD : Reverse dict (" + huffman.makeReverseDictionary("ABBCBABBABDD").toString() + ")");
        System.out.println("ABBCBABBABDD : Encode (" + huffman.encode("ABBCBABBABDD") + ")");
        System.out.println("ABBCBABBABDD : Decode (" + huffman.decode("011100110111011000000",
                huffman.makeReverseDictionary("ABBCBABBABDD")) + ")");
    }



}
