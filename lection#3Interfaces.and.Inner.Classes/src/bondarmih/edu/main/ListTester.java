package bondarmih.edu.main;

import bondarmih.edu.list.DoubleLinkedListImpl;
import bondarmih.edu.utility.Function;
import bondarmih.edu.utility.TwoWayIterator;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by bondarm on 16.05.16.
 *
 */
public class ListTester {
    public static void main(String[] args) {
        DoubleLinkedListImpl<Integer> list = new DoubleLinkedListImpl<>();

        System.out.println("Add, set, and drop operations test:");
        list.add(1,0);
        list.add(2,0);
        list.add(3,0);
        list.add(4,0);
        list.add(5,0);
        list.add(6,0);
        list.add(7,0);
        list.add(200);
        list.add(0);
        list.set(100,1);
        list.drop(3);
        for (Integer i : list) {
            System.out.println(i);
        }
        System.out.println("Size = " + list.getSize());
        System.out.println();

        System.out.println("Sort operation test:");
        try {
            list.sort();
            for (Integer i : list) {
                System.out.println(i);
            }
        }
        catch(Exception e) {
            System.out.println("Can not output sorted list: " + e.getMessage());
        }

        System.out.println();
        System.out.println("Reversible cyclical iterator test:");

        TwoWayIterator<Integer> myTwoWayIterator = list.twoWayIterator();
        int j=0;
        while (myTwoWayIterator.hasPrevious()&& j<20) {
            j++;
            System.out.println(myTwoWayIterator.previousItem());
        }

        System.out.println();
        System.out.println("Anon function Int to Double test: ");

        Function<Integer, Double> intToDouble = new Function<Integer, Double>() {
            @Override
            public Double apply(Integer integer) {
                return (double)integer;
            }
        };

        for (Integer i : list) {
            System.out.println(intToDouble.apply(i));
        }

    }
}
