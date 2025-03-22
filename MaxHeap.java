import java.util.ArrayList;

public final class MaxHeap<T extends Comparable<T>> {
    public ArrayList<T> heap;
    
    public MaxHeap(ArrayList<T> arr) {
        heap = arr;

        for (int i = 0; i < heap.size(); i++) {
            siftDown(i);
        }
    }
    public MaxHeap() {}
    public void siftDown(int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        while ((left < heap.size() && heap.get(i).compareTo(heap.get(left)) < 0) || 
               (right < heap.size() && heap.get(i).compareTo(heap.get(right)) < 0)) {
            
            int biggest;
            if (right >= heap.size() || heap.get(left).compareTo(heap.get(right)) > 0) {
                biggest = left;
            } else {
                biggest = right;
            }
            
            T temp = heap.get(i);
            heap.set(i, heap.get(biggest));
            heap.set(biggest, temp);
            
            i = biggest;
            left = 2 * i + 1;
            right = 2 * i + 2;
        }
    }

    public void siftUp(int i) {
        int parent = (i - 1) / 2;
        while (i > 0 && heap.get(i).compareTo(heap.get(parent)) > 0) {
            T temp = heap.get(i);
            heap.set(i, heap.get(parent));
            heap.set(parent, temp);
            
            i = parent;
            parent = (i - 1) / 2;
        }
    }

    public void insert(T elt) {
        heap.add(elt);
        siftUp(heap.size() - 1);
    }

    public T getMax(){
        return heap.get(0);
    }

    public T extractMax(){
        if(heap.isEmpty()){
            return null;
        }
        T max = heap.get(0);
        T temp = heap.get(0);
        heap.set(0, heap.get(-1));
        heap.set(-1, temp);
        heap.remove(-1);
        siftDown(0);
        return max;
    }

    public void update_by_index(int i, T new2){
        T old = heap.get(i);
        heap.set(i, new2);
        if(new2.compareTo(old) > 0){
            siftUp(i);
        }
        else{
            siftDown(i);
        }
    }

    public void update(T old2, T new2){
        if(heap.contains(old2)){
            update_by_index(heap.indexOf(old2), new2);
        }
    }
}