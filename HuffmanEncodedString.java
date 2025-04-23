import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HuffmanEncodedString {
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
    public static String serialize(String text){
        HashMap<Character, Integer> tab = tabulate(text);
        PriorityQueue<HuffmanChar> pq = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : tab.entrySet()) {
            Character key = entry.getKey();
            Integer value = entry.getValue();
            HuffmanChar insertChar = new HuffmanChar(key.toString(),value);
            pq.enqueue(insertChar);
        }
        HashMap<Character,String> serialTable = pq.getSerialTable();
    }
    public static String deserialize(String binary, HashMap<Character,String> serialTable){

    }
    public static void main(String[] args) {
        String serial = serialize("Hello World!");
        System.out.println(serial);
        System.out.println(deserialize(serial));
    }
}
