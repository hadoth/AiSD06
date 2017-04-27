package sort;

import utils.comparator.Comparator;

import java.util.List;

/**
 * Created by Karol Pokomeda on 2017-04-21.
 */
public class BubbleSort<T> implements ListSorter<T> {
    private Comparator<T> comparator;

    public BubbleSort(Comparator<T> comparator){
        this.comparator = comparator;
    }

    @Override
    public List<T> sort(List<T> list) {
        boolean isSorted  = false;
        int j = 1;
        while (!isSorted){
            isSorted = true;
            for (int i = 0; i < list.size()-j; i++){
                if (this.comparator.compare(list.get(i), list.get(i+1)) > 0) {
                    swap(list, i, i+1);
                    isSorted = false;
                }
            }
            j++;
        }
        return list;
    }

    @Override
    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public String getName() {
        return "Bubble Sort";
    }

    private static <T> void swap(List<T> list, int left, int right){
        T leftElem = list.get(left);
        list.set(left, list.get(right));
        list.set(right, leftElem);
    }
}