package bootstrap;

import sort.ListSorter;
import sort.ShakerSort;
import utils.Benchmark;
import utils.comparator.NaturalComparator;

import java.util.ArrayList;

/**
 * Created by Karol Pokomeda on 2017-04-23.
 */
public class ListFiveExThreeTest {
    public static void main(String[] args){
        int numberOfElements = 1000;

        ArrayList<Integer> listToSort;
        for (int i = 0; i < numberOfElements; i++) {
            listToSort = new ArrayList<>();

            for (int j = 0; j < numberOfElements; j++) listToSort.add((int) (Math.random() * numberOfElements * 2));

            ListSorter<Integer> sorter = new ShakerSort<>(new NaturalComparator<Integer>());

//        System.out.println(Arrays.toString(listToSort.toArray()));

            Benchmark benchmark = new Benchmark();

            benchmark.evaluate(sorter, new NaturalComparator<Integer>(), listToSort);

            System.out.println(benchmark.report());

            Benchmark.assertSorted(listToSort, new NaturalComparator<Integer>());

//        System.out.println(Arrays.toString(listToSort.toArray()));
        }
    }
}
