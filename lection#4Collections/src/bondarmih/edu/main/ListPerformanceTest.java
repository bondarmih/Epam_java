package bondarmih.edu.main;

import java.util.*;

/**
 * Created by bondarm on 17.05.16.
 *
 */
public class ListPerformanceTest {
    private static void ListTest(AbstractList<Integer> list) {
        System.out.println("List performance test for "+ list.getClass().toString());
        Coach ListCoach = new Coach(list);
        long testTime = ListCoach.populateSequentialTime();
        System.out.println("Sequential populate time: " + testTime + " ms");
        testTime = ListCoach.removeRandomTime();
        System.out.println("Random remove time:       " + testTime + " ms");
        testTime = ListCoach.populateRandomTime();
        System.out.println("Random populate time:     " + testTime + " ms");
        testTime = ListCoach.iteratingTime();
        System.out.println("Iterating time:           " + testTime + " ms");
        testTime = ListCoach.getRandomTime();
        System.out.println("Get random time:          " + testTime + " ms");
        testTime = ListCoach.sortTime();
        System.out.println("Sort time:                " + testTime + " ms");
        System.out.println();
    }

    public static void main(String[] args) {
        try {
            ListTest(new ArrayList<Integer>());
        }
        catch (Exception e) {
            System.out.println("ArrayList test failed: " + e.getMessage());
        }
        try {
            ListTest(new LinkedList<Integer>());
        }
        catch (Exception e) {
            System.out.println("LinkedList test failed: " + e.getMessage());
        }
    }
}
