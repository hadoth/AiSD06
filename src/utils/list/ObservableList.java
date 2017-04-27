package utils.list;

import utils.observer.Observable;
import utils.observer.Observer;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by Karol Pokomeda on 2017-04-21.
 */
public class ObservableList<T> implements Observable, List<T> {
    private ArrayList<Observer> observerList;
    private List<T> internalList;

    public ObservableList(List<T> internalList) {
        this.observerList = new ArrayList<>();
        this.internalList = internalList;
    }
    public ObservableList(ObservableList<T> listToCopy){
        this.observerList = listToCopy.observerList;
        this.internalList = listToCopy.internalList.stream().collect(Collectors.toList());
    }

    @Override
    public boolean addObserver(Observer observer) {
        return this.observerList.add(observer);
    }

    @Override
    public boolean deleteObserver(Observer observer) {
        this.observerList.remove(this.observerList.indexOf(observer));
        return true;
    }

    @Override
    public void notifyObservers(SortingEvent event) {
        for (Observer observer : this.observerList) observer.update(event);
    }

    @Override
    public Object[] toArray() {
        return this.internalList.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return this.internalList.toArray(a);
    }

    @Override
    public boolean add(T t) {
        boolean result = this.internalList.add(t);
        if (result) this.notifyObservers(SortingEvent.ADD);
        return result;
    }

    @Override
    public int size() {
        return this.internalList.size();
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        return this.internalList.get(index);
    }

    @Override
    public T set(int index, T element) {
        T result = this.internalList.set(index, element);
        this.notifyObservers(SortingEvent.SET);
        return result;
    }

    @Override
    public void add(int index, T element) {
        this.internalList.add(index, element);
        this.notifyObservers(SortingEvent.ADD);
    }

    @Override
    public T remove(int index) {
        T result = this.internalList.remove(index);
        this.notifyObservers(SortingEvent.REMOVE);
        return result;
    }

    @Override
    public int indexOf(Object o) {
        return this.internalList.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return this.internalList.lastIndexOf(o);
    }

    @Override
    public ListIterator<T> listIterator() {
        return this.internalList.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return this.internalList.listIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        ObservableList<T> result = new ObservableList<T>(this.internalList.subList(fromIndex, toIndex));
        for (Observer observer : this.observerList) result.addObserver(observer);
        return result;
    }

    @Override
    public boolean remove(Object o) {
        boolean result = this.internalList.remove(o);
        if (result) this.notifyObservers(SortingEvent.REMOVE);
        return result;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return this.internalList.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return this.internalList.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return this.internalList.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return this.internalList.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return this.internalList.retainAll(c);
    }

    @Override
    public boolean isEmpty() {
        return this.internalList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.internalList.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return this.internalList.iterator();
    }

    @Override
    public void clear() {
        this.internalList.clear();
    }
}