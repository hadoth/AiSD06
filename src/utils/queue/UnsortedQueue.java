package utils.queue;

import utils.comparator.Comparator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karol Pokomeda on 2017-04-29.
 */
public class UnsortedQueue<T> implements PriorityQueue<T> {
    private List<T> internalList;
    private Comparator<T> comparator;

    public UnsortedQueue(Comparator<T> comparator){
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
    }

    @Override
    public T remove() {
        return this.internalList.remove(this.findMax());
    }

    @Override
    public T peek() {
        return this.internalList.get(this.findMax());
    }

    private int findMax(){
        int result = 0;
        T max = this.internalList.get(0);
        T right;
        for (int i = 1; i < this.size(); i++){
            if (this.comparator.compare(max, right = this.internalList.get(i)) < 0) {
                result = i;
                max = right;
            }
        }
        return result;
    }
}
