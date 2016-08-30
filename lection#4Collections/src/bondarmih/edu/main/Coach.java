package bondarmih.edu.main;

import bondarmih.edu.utility.RandomInt;
import bondarmih.edu.utility.TimeCounter;

import java.util.AbstractList;
import java.util.Comparator;
import java.util.InputMismatchException;

/**
 * Created by bondarm on 17.05.16.
 *
 */
public class Coach {

    private static final int defaultElementCount = 10000;
    private static final int maximumElementValue = 1000;
    private static final int minimumElementValue = -1000;

    private AbstractList<Integer> list;

    public Coach(AbstractList<Integer> list) {
        this.list = list;
    }

    private static void checkForListPopulation(AbstractList<Integer> list) {
            checkForListIsNull(list);
            if (list.size() == 0) throw new IllegalArgumentException("List must be populated");
    }

    private static void checkForListIsNull(AbstractList<Integer> list) {
        if (list == null) throw new IllegalArgumentException("List must be not Null");
    }

    private static void checkForNullElements (AbstractList<Integer> list) {
        checkForListPopulation(list);
        for (Integer i : list ) {
            if (i == null) throw new IllegalArgumentException("Elements must be not Null");
        }
    }

    public long populateSequentialTime(int elementCount) {
        checkForListIsNull(this.list);
        TimeCounter timeCounter = new TimeCounter();
        for (int i = 0; i < elementCount; i++) {
            this.list.add(RandomInt.randInt(minimumElementValue,maximumElementValue));
        }
        return timeCounter.stop();
    }

    public long populateSequentialTime() {
        return populateSequentialTime(defaultElementCount);
    }

    public long populateRandomTime(int elementCount) {
        checkForListIsNull(this.list);
        TimeCounter timeCounter = new TimeCounter();
        for (int i = 0; i < elementCount; i++ ) {
            this.list.add(RandomInt.randInt(0,i), RandomInt.randInt(minimumElementValue,maximumElementValue) );
        }
        return timeCounter.stop();
    }

    public long populateRandomTime() {
        return populateRandomTime(defaultElementCount);
    }

    public long iteratingTime() {
        checkForListPopulation(this.list);
        TimeCounter timeCounter = new TimeCounter();
        int dummy = 0;
        for (Integer i: this.list) {
            //System.out.print(i + ", ");
            dummy++;
        }
        return timeCounter.stop();
    }

    public long sortTime() {
        checkForNullElements(this.list);
        TimeCounter timeCounter = new TimeCounter();
        this.list.sort(new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        return timeCounter.stop();
    }

    public long removeRandomTime() {
        checkForListPopulation(list);
        TimeCounter timeCounter = new TimeCounter();
        int listSize = this.list.size();
        for (int i = 0; i < listSize; i++ ) {
            this.list.remove(RandomInt.randInt(0,this.list.size() - 1));
        }
        return timeCounter.stop();
    }

    public long getRandomTime() {
        checkForNullElements(this.list);
        TimeCounter timeCounter = new TimeCounter();
        int dummy = 0;
        for (int i = 0; i < list.size(); i++) {
            //System.out.println(list.get(RandomInt.randInt(0, this.list.size()-1)));
            dummy++;
        }
        return timeCounter.stop();
    }
}
