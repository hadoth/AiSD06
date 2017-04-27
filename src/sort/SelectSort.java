package sort;

import java.util.List;

/**
 * Created by Karol on 2017-04-22.
 */
public class SelectSort<T> implements ListSorter<T> {
    private utils.comparator.Comparator<T> comparator;

    public SelectSort(utils.comparator.Comparator<T> comparator){
        this.comparator = comparator;
    }

    @Override
    public List<T> sort(List<T> list) {
        int leftEdge = 0;
        int rightEdge = list.size() - 1;
        while (rightEdge > leftEdge){
            int minIndex = leftEdge;
            int maxIndex = leftEdge;
            for( int i = leftEdge+1; i <= rightEdge; i++){
                if (this.comparator.compare(list.get(minIndex), list.get(i)) > 0) minIndex = i;
                if (this.comparator.compare(list.get(maxIndex), list.get(i)) < 0) maxIndex = i;
            }
            swap(list, minIndex, leftEdge);
            if (maxIndex == leftEdge) maxIndex = minIndex;
            swap(list, maxIndex, rightEdge);
            leftEdge++;
            rightEdge--;
        }
        return list;
    }

    @Override
    public void setComparator(utils.comparator.Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public String getName() {
        return "Select Sort";
    }

    private static <T> void swap(List<T> list, int left, int right){
        T leftElem = list.get(left);
        list.set(left, list.get(right));
        list.set(right, leftElem);
    }
}
