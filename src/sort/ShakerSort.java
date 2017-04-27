package sort;

import utils.comparator.Comparator;

import java.util.List;

/**
 * Created by Karol Pokomeda on 2017-04-23.
 */
public class ShakerSort<T> implements ListSorter<T> {
    private Comparator<T> comparator;

    public ShakerSort(Comparator<T> comparator){
        this.comparator = comparator;
    }

    @Override
    public List<T> sort(List<T> list) {
        boolean isSorted  = false;
        int bottomBound = 0;
        int lastSwapDown= 0;
        int lastSwapUp = bottomBound;
        int topBound = list.size();
        while (!isSorted){
            isSorted = true;
            for (int i = lastSwapDown; i < topBound-1; i++){
                if (this.comparator.compare(list.get(i), list.get(i+1)) > 0) {
                    swap(list, i, i+1);
                    isSorted = false;
                    lastSwapUp = i;
                }
            }
            topBound--;
            if (!isSorted){
                for (int i = lastSwapUp; i > bottomBound; i--){
                    if (this.comparator.compare(list.get(i-1), list.get(i)) > 0) {
                        swap(list, i, i-1);
                        isSorted = false;
                        lastSwapDown = i;
                    }
                }
                bottomBound++;
            }
        }
        return list;
    }

    @Override
    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public String getName() {
        return "Shaker Sort";
    }

    private static <T> void swap(List<T> list, int left, int right){
        T leftElem = list.get(left);
        list.set(left, list.get(right));
        list.set(right, leftElem);
    }
}
