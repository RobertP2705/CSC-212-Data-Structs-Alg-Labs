import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class reader{
    public static String readFile(){
        String line = "";
        try {
            File file = new File("data.txt");
            Scanner input = new Scanner(file);
            while (input.hasNextLine()) {
                line = line +" "+ input.nextLine();
            }
            input.close();
        } catch (Exception e) {
           System.out.println("NOOOOOOOO");
        }
        return line;
    }
    public static void main(String[] args) {
        String line = readFile().trim();
        String[] lineArr = line.split("\\s+");
        HashMap<String, ArrayList<Integer>> map = new HashMap<>();
        for (int i =0; i < lineArr.length; i++) {
            ArrayList<Integer> append = map.get(lineArr[i]) == null ? new ArrayList<>() : map.get(lineArr[i]);
            append.add(i+1);    
            map.put(lineArr[i],append);
        }
        System.out.println(map);
    }
}