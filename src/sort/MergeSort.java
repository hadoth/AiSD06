package sort;

import utils.comparator.Comparator;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Karol Pokomeda on 2017-04-28.
 */
public class MergeSort<T> implements ListSorter<T> {
    private Comparator comparator;

    public MergeSort(Comparator<T> comparator){
        this.comparator = comparator;
    }

    @Override
    public List<T> sort(List<T> list) {
        List<T> temp = this.splitAndMerge(list, 0, list.size() - 1);

        list.clear();
        for (T element : temp) list.add(element);       // additional list rewrite, necessary to ensure that the return
        return list;                                    // list is the same as the one provided in input as in every other
    }                                                   // implementation of ListSorter

    @Override
    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public String getName() {
        return "Merge Sort";
    }

    private List<T> splitAndMerge(List<T> list, int startIndex, int endIndex){
        if (startIndex >= endIndex) {
            List<T> result = this.emptyCopy(list);
            result.add(list.get(startIndex));
            return result;
        }
        int splitIndex = startIndex + (endIndex-startIndex)/2;
        return this.merge(
                this.splitAndMerge(list, startIndex, splitIndex),
                this.splitAndMerge(list, splitIndex+1, endIndex)
        );
    }

    private List<T> merge(List<T> leftList, List<T> rightList) {
        List<T> result = this.emptyCopy(leftList); //TODO: fix this method to present correct number of swaps;
        int i = 0;
        int j = 0;
        T leftCompared;
        T rightCompared;
        while (i < leftList.size() && j < rightList.size()){
            if (this.comparator.compare(leftCompared = leftList.get(i), rightCompared = rightList.get(j)) <= 0){
                result.add(leftCompared);
                i++;
            } else {
                result.add(rightCompared);
                j++;
            }
        }
        while(i < leftList.size()){
            result.add(leftList.get(i));
            i++;
        }
        while(j < rightList.size()){
            result.add(rightList.get(j));
            j++;
        }
        return result;
    }

    private static <T> List<T> emptyCopy(List<T> list){
        List<T> temp;
        try {
            temp = list.getClass().getDeclaredConstructor(list.getClass()).newInstance(list);   // http://stackoverflow.com/a/34202075/6808086
            temp.clear();
            return temp;
        } catch (NoSuchMethodException e) {                                                     // this code copies list assuring that both lists are of the same class
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Unable to create empty instance of provided list");
    }
}