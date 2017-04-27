package utils.observer;

/**
 * Created by Karol Pokomeda on 2017-04-20.
 */
public interface Observable {
    boolean addObserver(Observer observer);
    boolean deleteObserver (Observer observer);
    void notifyObservers(SortingEvent event);

    public enum SortingEvent {
        COMPARE,
        ADD,
        REMOVE,
        SET;
    }
}
