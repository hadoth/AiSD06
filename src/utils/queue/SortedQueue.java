package utils.queue;

import java.util.*;

/**
 * Created by Karol Pokomeda on 2017-04-29.
 */
public class SortedQueue<T> implements PriorityQueue<T> {
    private List<T> internalList = new LinkedList<>();
    private Comparator<T> comparator;

    public SortedQueue(Comparator comparator){
        this.comparator = comparator;
        this.internalList = new LinkedList<>();
    }

    @Override
    public int size() {
        return internalList.size();
    }

    @Override
    public boolean isEmpty() {
        return this.internalList.isEmpty();
    }

    @Override
    public boolean add(T t) {
        int i = 0;
        while (i < this.size() && this.comparator.compare(t, this.internalList.get(i)) >=0) i++;
        internalList.add(i, t);
        return true;
    }

    @Override
    public T remove() {
        return this.internalList.remove(this.internalList.size()-1);
    }

    @Override
    public T peek() {
        return this.internalList.get(this.internalList.size()-1);
    }
}
