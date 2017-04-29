package utils.queue;

import java.util.Queue;

/**
 * Created by Karol Pokomeda on 2017-04-28.
 */
public interface PriorityQueue<T>{
    int size();
    boolean isEmpty();
    boolean add(T t);
    T remove();
    T peek();
}
