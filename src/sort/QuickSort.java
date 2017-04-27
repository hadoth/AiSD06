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
        this.quickSort(list, 0, list.size()-1);
        return list;
    }

    @Override
    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public String getName() {
        return "Quick Sort";
    }

    private void quickSort(List<T> list, int startIndex, int endIndex){
        if (startIndex < endIndex){
            int partitionIndex = this.partition(list, startIndex, endIndex);
            this.quickSort(list, startIndex, partitionIndex-1);
            this.quickSort(list, partitionIndex+1, endIndex);
        }
    }

    private int partition(List<T> list, int startIndex, int endIndex){
        T elementToSort = list.get(endIndex - 1);
        int start = startIndex;
        int end = endIndex-1;
        while (start < end){
            if (comparator.compare(list.get(start), elementToSort) < 0) {
                start++;
                continue;
            }
            if (comparator.compare(elementToSort, list.get(end)) >= 0){
                end++;
                continue;
            }
            this.swap(list, start, end);
        }
        this.swap(list, endIndex, start);
        return start;
    }

    private static <T> void swap(List<T> list, int left, int right){
        T leftElem = list.get(left);
        list.set(left, list.get(right));
        list.set(right, leftElem);
    }
}
