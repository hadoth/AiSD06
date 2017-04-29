package utils.queue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Karol Pokomeda on 2017-04-29.
 */
public class HeapQueue<T> implements PriorityQueue<T> {
    private List<T> internalList;
    private Comparator<T> comparator;

    public HeapQueue(Comparator<T> comparator){
        this.comparator = comparator;
        this.internalList = new ArrayList<>();
    }

    @Override
    public int size() {
        return this.internalList.size();
    }

    @Override
    public boolean isEmpty() {
        return this.internalList.isEmpty();
    }

    @Override
    public boolean add(T t) {
        boolean result =  this.internalList.add(t);
        this.swim(this.internalList.size());
        return result;
    }

    @Override
    public T remove() {
        swap(this.internalList, 0, this.size() - 1);
        T result = this.internalList.get(0);
        this.internalList.remove(0);
        this.sink(0);
        return result;
    }

    @Override
    public T peek() {
        return this.internalList.get(this.size() - 1);
    }

    private void swim(int indexToSwim){
        int parentIndex = (indexToSwim-1)/2;
        if (parentIndex < 0) return;
        if (this.comparator.compare(this.internalList.get(indexToSwim), this.internalList.get(parentIndex)) > 0)
            swap(this.internalList, indexToSwim, parentIndex);
        this.swim(parentIndex);
    }

    private void sink(int indexToSink){

    }

    private static <T> void swap(List<T> list, int left, int right){
        T leftElem = list.get(left);
        list.set(left, list.get(right));
        list.set(right, leftElem);
    }
}
