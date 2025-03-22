public class JobQueue {
    PriorityQueue<Job> jq = new PriorityQueue<>();
    public JobQueue(){}
    public void insert(Job j1){
        jq.enqueue(j1);
    }
    public void runHighestPriority(){
        Job first = jq.dequeue();
        first.execute();
    }
}
