package internaltest;

import sort.ListSorter;
import sort.ShellSortBubble;
import utils.Benchmark;
import utils.comparator.NaturalComparator;

import java.util.ArrayList;

/**
 * Created by Karol Pokomeda on 2017-04-22.
 */
public class TestClass {
    public static void main(String[] args){
        int numberOfElements = 1000;

        ArrayList<Integer> listToSort;
        for (int i = 0; i < 1000; i++) {
            listToSort = new ArrayList<>();

            for (int j = 0; j < numberOfElements; j++) listToSort.add((int) (Math.random() * numberOfElements * 2));

            ListSorter<Integer> sorter = new ShellSortBubble<>(new NaturalComparator<Integer>());

//            System.out.println(Arrays.toString(listToSort.toArray()));

            Benchmark benchmark = new Benchmark();

            benchmark.evaluate(sorter, new NaturalComparator<Integer>(), listToSort);

            System.out.println(benchmark.report());

            Benchmark.assertSorted(listToSort, new NaturalComparator<Integer>());

//            System.out.println(Arrays.toString(listToSort.toArray()));

            System.out.println();
        }
    }
}
