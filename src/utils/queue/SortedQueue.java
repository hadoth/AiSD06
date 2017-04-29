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
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean add(T t) {
        return false;
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
