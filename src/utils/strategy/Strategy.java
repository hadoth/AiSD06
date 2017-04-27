package utils.strategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karol Pokomeda on 2017-04-27.
 */
public enum Strategy {
    DEFAULT{
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

    public abstract <T> ArrayList<Integer> getStrategy(List<T> list);
}
