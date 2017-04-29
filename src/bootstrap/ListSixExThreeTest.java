package bootstrap;

import sort.CountSort;
import sort.ListSorter;
import sort.ShakerSort;
import utils.Benchmark;
import utils.comparator.NaturalComparator;

import java.util.ArrayList;

/**
 * Created by Karol Pokomeda on 2017-04-23.
 */
public class ListSixExThreeTest {
    public static void main(String[] args){
        int numberOfElements = 1000000;

        ArrayList<Integer> listToSort;
        for (int i = 0; i < 100; i++) {
            listToSort = new ArrayList<>();

            for (int j = 0; j < numberOfElements; j++) listToSort.add((int) (Math.random() * 101));

            ListSorter<Integer> sorter = new CountSort(new NaturalComparator<>(), 100);

//        System.out.println(Arrays.toString(listToSort.toArray()));

            Benchmark benchmark = new Benchmark();

            benchmark.evaluate(sorter, new NaturalComparator<>(), listToSort);

            System.out.println(benchmark.report());

            Benchmark.assertSorted(listToSort, new NaturalComparator<>());

//        System.out.println(Arrays.toString(listToSort.toArray()));
        }
    }
}
