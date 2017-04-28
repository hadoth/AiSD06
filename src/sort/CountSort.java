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
        this.countList = new int[maxInt];
    }

    @Override
    public List<Integer> sort(List<Integer> list) {
        for (int i = 0; i < list.size(); i++){
            int number = list.get(i);
            this.countList[number]++;
            this.lastIndexList[number] = i;
        }

        for (int i = 0; i < lastIndexList.length; i++){
            int numberIndex = this.lastIndexList[i];
            while (this.countList[i] > 0){
                list.set(numberIndex, i);
                this.countList[i]--;
            }
        }
        return list;
    }

    @Override
    public void setComparator(Comparator<Integer> comparator) {
        this.comparator = comparator;
    }

    @Override
    public String getName() {
        return "Count Sort (Integer)";
    }
}
