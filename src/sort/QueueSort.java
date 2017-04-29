package sort;

import utils.comparator.Comparator;

import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Karol on 2017-04-29.
 */
public class QueueSort<T> implements ListSorter<T> {
    private Comparator<T> comparator;
    private PriorityQueue<T> priorityQueue;

    public QueueSort(Comparator<T> comparator, PriorityQueue<T> priorityQueue){
        this.comparator = comparator;
        this.priorityQueue = priorityQueue;
    }

    @Override
    public List<T> sort(List<T> list) {
        while (!list.isEmpty()) this.priorityQueue.add(list.remove(0));
        while (!this.priorityQueue.isEmpty()) list.add(0, this.priorityQueue.remove());
        return list;
    }

    @Override
    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void setPriorityQueue(PriorityQueue<T> priorityQueue) {
        this.priorityQueue = priorityQueue;
    }

    @Override
    public String getName() {
        return "Queue Sort (" + this.priorityQueue.getClass().getSimpleName() + ")";
    }
}
