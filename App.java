
public class App {
    public static void main(String[] args) {
      JobQueue jobQueue = new JobQueue();
      Job jobA = new Job("This is job a", 5);
      Job jobB = new Job("This is job b", 2);
      Job jobC = new Job("This is job c", 9);
      Job jobD = new Job("This is job d", 8);
      Job jobE = new Job("This is job e", 1);
      jobQueue.insert(jobA);
      jobQueue.insert(jobB);
      jobQueue.insert(jobC);
      jobQueue.insert(jobD);
      jobQueue.insert(jobE);
      
      jobQueue.runHighestPriority(); // Calls jobC.execute() since job C has the highest priority
      jobQueue.runHighestPriority(); // Calls jobD.execute() since job C has the highest priority
      jobQueue.runHighestPriority(); // Calls jobA.execute() since job C has the highest priority
      jobQueue.runHighestPriority(); // Calls jobB.execute() since job C has the highest priority
      jobQueue.runHighestPriority(); // Calls job1.execute() since job C has the highest priority
    }
  }