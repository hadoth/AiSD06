package utils;

import sort.ListSorter;
import sort.QueueSort;
import utils.comparator.Comparator;
import utils.comparator.ObservableComparator;
import utils.list.ObservableList;
import utils.observer.Observable;
import utils.observer.Observer;
import utils.queue.PriorityQueue;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Karol Pokomeda on 2017-04-22.
 */
public class Benchmark implements Observer {
    private int compareCounter;
    private int compareMilCounter;
    private int changeCounter;
    private int changeMilCounter;
    private String sorterName;

    public Benchmark(){
        this.clear();
    }

    public<T> void evaluate(ListSorter<T> sorter, Comparator<T> comparator, List<T> list){
        ObservableList<T> internalList = new ObservableList<>(list);
        this.evaluateInternally(sorter, comparator, internalList);
    }

    public void evaluate(ListSorter<Integer> sorter, Comparator<Integer> comparator, String filePath){
        ObservableList<Integer> internalList = new ObservableList<>(this.loadList(filePath));
        this.evaluateInternally(sorter, comparator, internalList);
    }

    private <T> void evaluateInternally(ListSorter<T> sorter, Comparator<T> comparator, ObservableList<T> internalList){
        this.clear();
        ObservableComparator<T> internalComparator = new ObservableComparator<>(comparator);

        internalComparator.addObserver(this);
        internalList.addObserver(this);

        if (sorter instanceof QueueSort){
            PriorityQueue<T> queue = ((QueueSort<T>)(sorter)).getPriorityQueue();
            ObservableList<T> queueList = new ObservableList<T>(new ArrayList<T>());
            queueList.addObserver(this);
            queue.setInternalList(queueList);
            queue.setComparator(internalComparator);
            ((QueueSort<T>)(sorter)).setPriorityQueue(queue);
        }
        sorter.setComparator(internalComparator);
        sorter.sort(internalList);
        Benchmark.assertSorted(internalList, comparator);
        this.sorterName = sorter.getName();
    }

    private void clear(){
        this.compareCounter = 0;
        this.compareMilCounter = 0;
        this.changeCounter = 0;
        this.changeMilCounter = 0;
        this.sorterName = null;
    }

    @Override
    public void update(Observable.SortingEvent event){
        if (!event.equals(Observable.SortingEvent.COMPARE)) {
            this.changeCounter++;
            if (this.changeCounter%2000000 == 0) {
                this.changeCounter = 0;
                System.out.println(++this.changeMilCounter + "M changes");
            }
        }
        if (event.equals(Observable.SortingEvent.COMPARE)){
            this.compareCounter++;
            if (this.compareCounter%1000000 == 0) {
                this.compareCounter = 0;
                System.out.println(++this.compareMilCounter + "M compares");
            }
        }
    }

    public String report(){
        StringBuilder builder = new StringBuilder();
        builder.append("|   ");
        builder.append(this.sorterName);
        while (builder.length() < 40) builder.append(" ");
        builder.append("|   ");
        builder.append(this.concatenate(this.changeMilCounter, this.changeCounter/2));
        while (builder.length() < 60) builder.append(" ");
        builder.append("|   ");
        builder.append(this.concatenate(this.compareMilCounter, this.compareCounter));
        while (builder.length() < 80) builder.append(" ");
        builder.append("|");
        return builder.toString();
    }

    public static String reportTemplate(){
        StringBuilder builder1 = new StringBuilder();
        builder1.append("| NAME:");
        while (builder1.length() < 40) builder1.append(" ");
        builder1.append("| NO OF SWAPS:");
        while (builder1.length() < 60) builder1.append(" ");
        builder1.append("| NO OF COMPARISONS");
        while (builder1.length() < 80) builder1.append(" ");
        builder1.append("|\n");

        StringBuilder builder2 = new StringBuilder();
        builder2.append("|");
        while (builder2.length() < 40) builder2.append("-");
        builder2.append("|");
        while (builder2.length() < 60) builder2.append("-");
        builder2.append("|");
        while (builder2.length() < 80) builder2.append("-");
        builder2.append("|");

        builder1.append(builder2);

        return builder1.toString();
    }

    private String concatenate(int milPart, int rest){
        if (milPart == 0) return String.valueOf(rest);
        String result = String.valueOf(milPart);
        for (int i = String.valueOf(rest).length(); i < 7; i++) result += "0";
        return result + rest;
    }

    private List<Integer> loadList(String filePath){
        List<Integer> result = new ArrayList<>();
        File inputFile = new File(filePath);
        try(FileReader fileIn = new FileReader(inputFile);
            Scanner dataIn = new Scanner(fileIn)){
            while(dataIn.hasNextLine()){
                String[] inputText = dataIn.nextLine().split(",");
                for (String number : inputText) result.add(Integer.valueOf(number));
            }
        } catch (IOException e){
            throw new IllegalArgumentException("File not found or data corrupted");
        }
        return result;
    }

    public static  <T> void assertSorted(List<T> listToCheck, Comparator<T> template){
        for (int i = 1; i < listToCheck.size(); i++){
            if (template.compare(listToCheck.get(i-1), listToCheck.get(i)) > 0)
                throw new AssertionError(
                    "element " + (i-1) + " = " + listToCheck.get(i-1) +
                    ";\nelement " + i + " = " + listToCheck.get(i)
                );
        }
    }
}