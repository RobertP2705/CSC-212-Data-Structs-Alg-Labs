//inspo from https://gist.github.com/syphh/50adc4e9c7e6efc3c5b4555018e47ddd

public class PriorityQueue <T extends Comparable<T>> {
    public MaxHeap<T> queue;
    public PriorityQueue(){
        MaxHeap<T> queue = new MaxHeap<>();
    }

    public void enqueue(T elt){
        queue.insert(elt);
    }

    public T peek(){
        return queue.getMax();
    }
    
    public T dequeue(){
        return queue.extractMax();
    }

    public boolean is_empty(){
        return queue.heap.isEmpty();
    }

    public void change_priority_by_index(int i, T new2){
        queue.update_by_index(i, new2);
    }

    public void change_priority(T old2,T new2){
        queue.update(old2,new2);
    }
}
