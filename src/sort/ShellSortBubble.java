package sort;

import utils.comparator.Comparator;
import utils.strategy.Strategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karol Pokomeda on 2017-04-27.
 */
public class ShellSortBubble<T> implements ListSorter<T> {
    private Strategy strategy;
    private Comparator<T> comparator;

    public ShellSortBubble(Comparator<T> comparator){
        this.comparator = comparator;
        this.strategy = Strategy.PRATT2;
    }

    public ShellSortBubble(Comparator<T> comparator, Strategy strategy){
        this.comparator = comparator;
        this.strategy = strategy;
    }


    @Override
    public List<T> sort(List<T> list) {
        ArrayList<Integer> strategyList = strategy.getStrategy(list);
        for (int i = strategyList.size()-1; i >=0; i--){                                            // select strategy element
            int strategyElem = strategyList.get(i);
            for (int j = 0; j < strategyElem; j++){                                                 // select microQueue
                boolean isSorted;
                do {                                                                                // sort microQueue
                    isSorted = true;
                    for (int k = j + strategyElem; k < list.size(); k += strategyElem) {            // iterate microQueue
                        if (comparator.compare(list.get(k - strategyElem), list.get(k)) > 0){
                            swap(list, k - strategyElem, k);
                            isSorted = false;
                        }
                    }
                } while (!isSorted);
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
        return "Shell Sort (Bubble)";
    }

    private static <T> void swap(List<T> list, int left, int right){
        T leftElem = list.get(left);
        list.set(left, list.get(right));
        list.set(right, leftElem);
    }
}