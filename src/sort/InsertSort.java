package sort;

import utils.comparator.Comparator;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by Karol Pokomeda on 2017-04-22.
 */
public class InsertSort<T> implements ListSorter<T> {
    private Comparator<T> comparator;

    public InsertSort(Comparator<T> comparator){
        this.comparator = comparator;
    }

    @Override
    public List<T> sort(List<T> list) {
        List<T> temp = null;
        try {
            temp = list.getClass().getDeclaredConstructor(list.getClass()).newInstance(list); //http://stackoverflow.com/a/34202075/6808086
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        list.clear();
        while (temp.size() > 0) {
            insertInOrder(list, temp.remove(0),comparator);
        }
        return list;
    }

    @Override
    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public String getName() {
        return "Insert Sort";
    }

    private static <T> void insertInOrder(List<T> list, T t, Comparator<T> comparator){
        for (int i = 0; i < list.size(); i++){
            if (comparator.compare(t, list.get(i)) <= 0){
                list.add(i, t);
                return;
            }
        }
        list.add(t);
    }
}