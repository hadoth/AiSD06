package utils.strategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karol Pokomeda on 2017-04-27.
 */
public enum Strategy {
    PRATT("Pratt"){
        public <T> ArrayList<Integer> getStrategy(List<T> list){
            ArrayList<Integer> strategyList = new ArrayList<>();
            int nextOne = 1;
            while (nextOne <= list.size()/3){
                strategyList.add(nextOne);
                nextOne = 3 * nextOne + 1;
            }
            return strategyList;
        }
    },
    PAPERNOV("Papernov & Stasevich"){
        @Override
        public <T> ArrayList<Integer> getStrategy(List<T> list) {
            ArrayList<Integer> strategyList = new ArrayList<>();
            int nextOne = 1;
            boolean nextTimesTwo = true;
            int k = 1;
            while (nextOne <= list.size()/2){
                strategyList.add(nextOne);
                nextOne = 1;
                for (int i = 0; i < k; i++) nextOne *=2;
                nextOne +=1;
            }
            return strategyList;
        }
    },
    SHELL1("Shell 1"){
        @Override
        public <T> ArrayList<Integer> getStrategy(List<T> list) {
            ArrayList<Integer> strategyList = new ArrayList<>();
            int nextOne = list.size()/2;
            while (nextOne > 1){
                strategyList.add(0, nextOne);
                nextOne = nextOne/2;
            }
            strategyList.add(0, 1);
            return strategyList;
        }
    };

    private String name;

    Strategy(String name){
        this.name = name;
    }

    public abstract <T> ArrayList<Integer> getStrategy(List<T> list);

    public String getName() {
        return this.name;
    }
}
