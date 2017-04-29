package bootstrap;

import sort.*;
import utils.Benchmark;
import utils.comparator.NaturalComparator;
import utils.queue.HeapQueue;
import utils.queue.SortedQueue;
import utils.queue.UnsortedQueue;
import utils.strategy.Strategy;

import java.util.ArrayList;

/**
 * Created by Karol Pokomeda on 2017-04-22.
 */
public class ListSixExOneTest {
    public static void main(String[] args) {
        String[] filePath = {
                "ALMOST_SORTED_1000_ASCENDING.csv",
                "ALMOST_SORTED_1000_DESCENDING.csv",
                "CONST_1000_ASCENDING.csv",
                "CONST_1000_DESCENDING.csv",
                "FULLY_RANDOM_1000_ASCENDING.csv",
                "FULLY_RANDOM_1000_DESCENDING.csv",
                "SORTED_1000_ASCENDING.csv",
                "SORTED_1000_DESCENDING.csv",
                "STAIRS_1000_ASCENDING.csv",
                "STAIRS_1000_DESCENDING.csv"
        };
        NaturalComparator<Integer> comparator = new NaturalComparator<>();

        ArrayList<ListSorter<Integer>> sorters = new ArrayList<>();
        sorters.add(new BubbleSort<>(comparator));
        sorters.add(new SelectSort<>(comparator));
        sorters.add(new InsertSort<>(comparator));
        sorters.add(new ShakerSort<>(comparator));
        sorters.add(new ShakerSort<>(comparator));
        sorters.add(new ShellSort<>(comparator));
        sorters.add(new ShellSort<>(comparator, Strategy.SHELL1));
        sorters.add(new ShellSort<>(comparator, Strategy.PAPERNOV));
        sorters.add(new ShellSortBubble<>(comparator));
        sorters.add(new QuickSort<>(comparator));
        sorters.add(new MergeSort<>(comparator));
        sorters.add(new QueueSort<>(comparator, new SortedQueue<>(comparator)));
        sorters.add(new QueueSort<>(comparator, new UnsortedQueue<>(comparator)));
        sorters.add(new QueueSort<>(comparator, new HeapQueue<>(comparator)));

        Benchmark sorterBenchmark = new Benchmark();

        for (String singleFilePath : filePath) {
            System.out.println(singleFilePath.replace(".csv", ""));
            System.out.println(Benchmark.reportTemplate());

            for (ListSorter<Integer> sorter : sorters) {
                sorterBenchmark.evaluate(sorter, comparator, singleFilePath);

                System.out.println(sorterBenchmark.report());

            }
            System.out.println();
        }
    }
}