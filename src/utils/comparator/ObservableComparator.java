package utils.comparator;

import utils.observer.Observable;
import utils.observer.Observer;

import java.util.ArrayList;

/**
 * Created by Karol Pokomeda on 2017-04-20.
 */
public class ObservableComparator<T> implements Comparator<T>, Observable {
    private Comparator<T> internalComparator;
    private ArrayList<Observer> observers;

    public ObservableComparator(Comparator<T> internalComparator){
        this.internalComparator = internalComparator;
        this.observers = new ArrayList<>();
    }

    @Override
    public int compare(T tLeft, T tRight) {
        int result = this.internalComparator.compare(tLeft, tRight);
        this.notifyObservers(SortingEvent.COMPARE);
        return result;
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