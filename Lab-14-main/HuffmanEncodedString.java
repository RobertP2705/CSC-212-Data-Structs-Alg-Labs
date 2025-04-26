import java.util.HashMap;
import java.util.Map;

class HuffmanNode implements Comparable<HuffmanNode> {
    String name;
    int frequency;
    HuffmanNode left;
    HuffmanNode right;
    
    public HuffmanNode(String name, int frequency) {
        this.name = name;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }
    
    public HuffmanNode(HuffmanNode left, HuffmanNode right) {
        this.name = left.name + right.name;
        this.frequency = left.frequency + right.frequency;
        this.left = left;
        this.right = right;
    }
    
    @Override
    public int compareTo(HuffmanNode other) {
        return other.frequency - this.frequency;
    }
}

public class HuffmanEncodedString {
    private static HashMap<Character, String> encodingTable = new HashMap<>();
    
    public static HashMap<Character, Integer> tabulate(String text) {
        HashMap<Character, Integer> charCountMap = new HashMap<>();
        
        if (text == null || text.isEmpty()) {
            return charCountMap;
        }
        
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }
        
        return charCountMap;
    }
    
    private static void generateCodes(HuffmanNode root, String code, HashMap<Character, String> codeMap) {
        if (root == null) {
            return;
        }
        
        if (root.left == null && root.right == null && root.name.length() == 1) {
            codeMap.put(root.name.charAt(0), code.isEmpty() ? "0" : code);
            return;
        }
        
        generateCodes(root.left, code + "0", codeMap);
        generateCodes(root.right, code + "1", codeMap);
    }
    
    public static String serialize(String text) {
        HashMap<Character, Integer> freqTable = tabulate(text);
        
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : freqTable.entrySet()) {
            char key = entry.getKey();
            int value = entry.getValue();
            HuffmanNode node = new HuffmanNode(Character.toString(key), value);
            pq.enqueue(node);
        }
        
        while (pq.queue.heap.size() > 1) {
            HuffmanNode left = pq.dequeue();
            HuffmanNode right = pq.dequeue();
            
            HuffmanNode newNode = new HuffmanNode(left, right);
            
            pq.enqueue(newNode);
        }
        
        HuffmanNode root = pq.dequeue();
        
        HashMap<Character, String> codeMap = new HashMap<>();
        if (freqTable.size() == 1) {
            codeMap.put(text.charAt(0), "0");
        } else {
            generateCodes(root, "", codeMap);
        }
        
        StringBuilder encodedText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            encodedText.append(codeMap.get(text.charAt(i)));
        }
        
        StringBuilder tableStr = new StringBuilder();
        tableStr.append("{");
        boolean first = true;
        for (Map.Entry<Character, String> entry : codeMap.entrySet()) {
            if (!first) {
                tableStr.append(",");
            }
            first = false;
            tableStr.append("'").append(entry.getKey()).append("':'").append(entry.getValue()).append("'");
        }
        tableStr.append("}");
        
        encodingTable = codeMap;
        
        System.out.println("encoidng table: " + tableStr.toString());
        return encodedText.toString();
    }
    
    public static String deserialize(String binary, HashMap<Character, String> codeMap) {
        HashMap<String, Character> reverseMap = new HashMap<>();
        for (Map.Entry<Character, String> entry : codeMap.entrySet()) {
            reverseMap.put(entry.getValue(), entry.getKey());
        }
        
        StringBuilder decodedText = new StringBuilder();
        StringBuilder currentCode = new StringBuilder();
        
        for (int i = 0; i < binary.length(); i++) {
            currentCode.append(binary.charAt(i));
            
            if (reverseMap.containsKey(currentCode.toString())) {
                decodedText.append(reverseMap.get(currentCode.toString()));
                currentCode = new StringBuilder();
            }
        }
        
        return decodedText.toString();
    }
    
    public static String deserialize(String binary) {
        return deserialize(binary, encodingTable);
    }
    
    public static void main(String[] args) {
        String text = "Hello World!!!!";
        System.out.println("text: " + text);
        
        String binary = serialize(text);
        System.out.println("binary: " + binary);
        
        String decoded = deserialize(binary);
        System.out.println("decoded: " + decoded);
    }
}