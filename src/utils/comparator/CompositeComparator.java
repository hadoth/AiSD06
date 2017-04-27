package utils.comparator;

import java.util.List;

/**
 * Created by Karol Pokomeda on 2017-04-21.
 */
public class CompositeComparator<T> implements Comparator<T> {
    private List<Comparator<T>> internalComparators;

    public CompositeComparator(List<Comparator<T>> internalComparators){
        this.internalComparators = internalComparators;
    }

    @Override
    public int compare(T left, T right) {
        int result;
        for (Comparator<T> singleComparator : this.internalComparators) {
            if ((result = singleComparator.compare(left, right)) != 0) return result;
        }
        return 0;
    }
}
