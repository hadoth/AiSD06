package utils.strategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karol Pokomeda on 2017-04-27.
 */
public enum Strategy {
    PRATT2("Pratt 2"){
        public <T> ArrayList<Integer> getStrategy(List<T> list){
            ArrayList<Integer> strategyList = new ArrayList<>();
            int nextOne = 1;
            while (nextOne <= list.size()/3){
                strategyList.add(nextOne);
                nextOne = 3 * nextOne + 1;
            }
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
