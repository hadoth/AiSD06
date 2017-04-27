package sort;

import utils.comparator.Comparator;
import utils.strategy.Strategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karol Pokomeda on 2017-04-27.
 */
public class ShellSort<T> implements ListSorter<T> {
    private Strategy strategy;
    private Comparator<T> comparator;

    public ShellSort(Comparator<T> comparator){
        this.comparator = comparator;
        this.strategy = Strategy.DEFAULT;
    }

    public ShellSort(Comparator<T> comparator, Strategy strategy){
        this.comparator = comparator;
        this.strategy = strategy;
    }


    @Override
    public List<T> sort(List<T> list) {
        ArrayList<Integer> strategyList = strategy.getStrategy(list);
        for (int strategyIndex = strategyList.size()-1; strategyIndex >=0; strategyIndex--){                        // select strategy element
            int strategyElem = strategyList.get(strategyIndex);                                                     // get current strategy element
            for (int sublistStartIndex = strategyElem; sublistStartIndex < list.size(); sublistStartIndex++){       // iterate over whole list and sort element inside subList; sublist start at index i and consist of all predecessing elements with distance from i specified with strategy
                T comparedElement;                                                                                  // element of sublist with index lower than that of checkedElement
                T checkedElement = list.get(sublistStartIndex);                                                     // checked element which might be moved backwards in the sublist, accordingly to the effect of comparison
                int sublistIndex = sublistStartIndex;                                                               // set current sublist index
                while(                                                                                              // iterate while:
                    sublistIndex >= strategyElem &&                                                                 // index of compared element is greater than 0
                    comparator.compare(checkedElement, comparedElement = list.get(sublistIndex - strategyElem)) < 0 // checked element is smaller than element n*strategyElement to the left of it
                ){
                    list.set(sublistIndex, comparedElement);                                                        // move compared by strategyElem right
                    sublistIndex -= strategyElem;                                                                   // decrease index of compared element by strategyElement
                }
                list.set(sublistIndex, checkedElement);                                                             // set checked element at the right place in sublist place
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
        return "Shell Sort";
    }

    private static <T> void swap(List<T> list, int left, int right){
        T leftElem = list.get(left);
        list.set(left, list.get(right));
        list.set(right, leftElem);
    }
}