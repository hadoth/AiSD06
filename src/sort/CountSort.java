package sort;

import utils.comparator.Comparator;

import java.util.List;

/**
 * Created by Karol Pokomeda on 2017-04-28.
 */
public class CountSort implements ListSorter<Integer> {
    private int[] lastIndexList;
    private int[] countList;
    private  Comparator<Integer> comparator;

    public CountSort(Comparator<Integer> comparator, int maxInt){
        this.comparator = comparator;
        this.lastIndexList = new int[maxInt];
        this.lastIndexList = new int[maxInt];
    }

    @Override
    public List<Integer> sort(List<Integer> list) {
        return null;
    }

    @Override
    public void setComparator(Comparator<Integer> comparator) {

    }

    @Override
    public String getName() {
        return null;
    }
}
