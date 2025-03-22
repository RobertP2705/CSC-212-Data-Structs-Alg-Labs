import java.util.ArrayList;

public final class MaxHeap<T extends Comparable<T>> {
    public ArrayList<T> heap;
    
    public MaxHeap(ArrayList<T> arr) {
        this.heap = arr;

        for (int i = 0; i < this.heap.size(); i++) {
            siftDown(i);
        }
    }
    public MaxHeap() {}
    public void siftDown(int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        while ((left < this.heap.size() && this.heap.get(i).compareTo(this.heap.get(left)) < 0) || 
               (right < this.heap.size() && this.heap.get(i).compareTo(this.heap.get(right)) < 0)) {
            
            int biggest = -1;
            if (right >= this.heap.size() || this.heap.get(left).compareTo(this.heap.get(right)) > 0) {
                biggest = left;
            } else {
                biggest = right;
            }
            
            T temp = this.heap.get(i);
            this.heap.set(i, this.heap.get(biggest));
            this.heap.set(biggest, temp);
            
            i = biggest;
            left = 2 * i + 1;
            right = 2 * i + 2;
        }
    }

    public void siftUp(int i) {
        int parent = (i - 1) / 2;
        while (i > 0 && this.heap.get(i).compareTo(this.heap.get(parent)) > 0) {
            T temp = this.heap.get(i);
            this.heap.set(i, this.heap.get(parent));
            this.heap.set(parent, temp);
            
            i = parent;
            parent = (i - 1) / 2;
        }
    }

    public void insert(T elt) {
        this.heap.add(elt);
        siftUp(this.heap.size() - 1);
    }

    public T getMax(){
        return this.heap.get(0);
    }

    public T extractMax(){
        if(this.heap.isEmpty()){
            return null;
        }
        T max = this.heap.get(0);
        T temp = this.heap.get(0);
        this.heap.set(0, this.heap.get(-1));
        this.heap.set(-1, temp);
        this.heap.remove(-1);
        siftDown(0);
        return max;
    }

    public void update_by_index(int i, T new2){
        T old = this.heap.get(i);
        this.heap.set(i, new2);
        if(new2.compareTo(old) > 0){
            siftUp(i);
        }
        else{
            siftDown(i);
        }
    }

    public void update(T old2, T new2){
        if(this.heap.contains(old2)){
            this.update_by_index(this.heap.indexOf(old2), new2);
        }
    }
}