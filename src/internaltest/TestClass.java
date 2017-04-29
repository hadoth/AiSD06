package internaltest;

import sort.ListSorter;
import sort.MergeSort;
import sort.ShellSortBubble;
import utils.Benchmark;
import utils.comparator.NaturalComparator;
import utils.queue.HeapQueue;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Karol Pokomeda on 2017-04-22.
 */
public class TestClass {
    public static void main(String[] args){
    for(int numberOfElements = 1; numberOfElements < 1000; numberOfElements++) {
        HeapQueue<Integer> heapQueue = new HeapQueue<Integer>(new NaturalComparator<Integer>());
        for (int j = 0; j < numberOfElements; j++) heapQueue.add((int) (Math.random()*10));
        for (int j = 0; j < numberOfElements; j++) System.out.print(heapQueue.remove() + ", ");
        System.out.println();
    }
//        ArrayList<Integer> listToSort;
//        for (int i = 0; i < 1000; i++) {
//            listToSort = new ArrayList<>();
//
//            for (int j = 0; j < numberOfElements; j++) listToSort.add((int) (Math.random() * numberOfElements * 2));
//
//            ListSorter<Integer> sorter = new MergeSort<>(new NaturalComparator<Integer>());
//
////            System.out.println(Arrays.toString(listToSort.toArray()));
//
//            Benchmark benchmark = new Benchmark();
//
//            benchmark.evaluate(sorter, new NaturalComparator<Integer>(), listToSort);
//
//            System.out.println(benchmark.report());
//
//            Benchmark.assertSorted(listToSort, new NaturalComparator<Integer>());
//
////            System.out.println(Arrays.toString(listToSort.toArray()));
//
//            System.out.println();
//        }
    }
}
