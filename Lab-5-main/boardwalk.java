
public class boardwalk {
    public static void main(String []args) {
        CircularLinkedList<String> monopolyBoard = new CircularLinkedList<>();
 
        monopolyBoard.append("Go");
        monopolyBoard.append("Mediteranean Avenue");
        monopolyBoard.append("Community Chest");
        monopolyBoard.append("Baltic Avenue");
        monopolyBoard.append("Income Tax");
        // And so on for all of the spaces on the board...
        
        System.out.println(monopolyBoard.getCurrentNode()); // This should print "Go" to the console
        
        monopolyBoard.step();
        
        System.out.println(monopolyBoard.getCurrentNode()); // This should print "Mediteranean Avenue" to the console
        
        monopolyBoard.step();
        monopolyBoard.step();
        monopolyBoard.step();
        
        System.out.println(monopolyBoard.getCurrentNode()); 
        
        for(int i = 0; i < 37; i++) {
            monopolyBoard.step();
        }
        
        System.out.println(monopolyBoard.getCurrentNode()); // This should print "Mediteranean Avenue" to the console since we have looped back around
        }
}
