package utils.queue;

import utils.comparator.Comparator;
import utils.observer.Observable;
import utils.observer.Observer;

import java.util.ArrayList;

/**
 * Created by Karol on 2017-04-29.
 */
public class ObservablePriorityQueue<T> implements PriorityQueue<T>, Observable {
    private ArrayList<Observer> observers;
    private PriorityQueue<T> internalQueue;

    public ObservablePriorityQueue(PriorityQueue<T> internalQueue){
        this.internalQueue = internalQueue;
        this.observers = new ArrayList<>();
    }

    @Override
    public int size() {
        return this.internalQueue.size();
    }

    @Override
    public boolean isEmpty() {
        return this.internalQueue.isEmpty();
    }

    @Override
    public boolean add(T t) {
        return this.internalQueue.add(t);
    }

    @Override
    public T remove() {
        return this.internalQueue.remove();
    }

    @Override
    public T peek() {
        return this.internalQueue.peek();
    }

    @Override
    public void setComparator(Comparator<T> comparator) {
        this.internalQueue.setComparator(comparator);
    }

    @Override
    public boolean addObserver(Observer observer) {
        return this.observers.add(observer);
    }

    @Override
    public boolean deleteObserver(Observer observer) {
        return this.observers.remove(observer);
    }

    @Override
    public void notifyObservers(SortingEvent event) {
        for (Observer observer : this.observers) observer.update(event);
    }
}
