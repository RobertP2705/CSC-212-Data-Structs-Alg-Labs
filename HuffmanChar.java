public class HuffmanChar implements Comparable<HuffmanChar> {
    private String name;
    public Integer priority;
  
    HuffmanChar(String name, Integer priority) {
      this.name = name;
      this.priority = priority;
    }
  
    void execute() {
      System.out.println("Running the job with name " + this.name + " and priority " + this.priority);
    }
  
    String getName() {
      return this.name;
    }
  
    int getPriority() {
      return this.priority;
    }

    @Override
    public int compareTo(HuffmanChar key2){
      return this.priority.compareTo(key2.priority);
    }
}
