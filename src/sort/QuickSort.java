package sort;

import utils.comparator.Comparator;

import java.util.List;

/**
 * Created by Karol Pokomeda on 2017-04-27.
 */
public class QuickSort<T> implements ListSorter<T> {
    private Comparator<T> comparator;

    public QuickSort(Comparator<T> comparator){
        this.comparator = comparator;
    }

    @Override
    public List<T> sort(List<T> list) {
        return null;
    }

    @Override
    public void setComparator(Comparator<T> comparator) {

    }

    @Override
    public String getName() {
        return null;
    }
}
