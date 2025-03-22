

public class Job implements Comparable<Job>{
    private String name;
    public Integer priority;
  
    Job(String name, Integer priority) {
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
    public int compareTo(Job key2){
      return this.priority.compareTo(key2.priority);
    }
}