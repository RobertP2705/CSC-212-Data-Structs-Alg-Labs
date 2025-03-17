public class driver {
    public static void main(String[] args) {
        TreeMap map = new TreeMap();
        map.insert("Z","x2");
        map.insert("B", "x");
        map.insert("D", "x");
        map.insert("E", "x3");

        map.printTree();
        System.out.println(map.delete("E"));
        map.printTree();

        System.out.println(map.get("B"));

      }
}
