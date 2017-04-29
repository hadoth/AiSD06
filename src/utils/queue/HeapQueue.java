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
        return this.internalList.add(t);
        this.swim(this.internalList.size());
    }

    @Override
    public T remove() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }
}
