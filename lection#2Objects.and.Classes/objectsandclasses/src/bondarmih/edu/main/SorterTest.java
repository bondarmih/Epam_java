package bondarmih.edu.main;

import bondarmih.edu.genetator.RandomArrayGenerator;
import bondarmih.edu.sorters.CocktailSorter;
import bondarmih.edu.sorters.SelectionSorter;
import bondarmih.edu.sorters.Sorter;

import java.util.Arrays;

/**
 *
 * Created by bondarm on 04.05.16 .
 */
public class SorterTest{

    private static void testSorterInstance(Sorter testedInstance) {
        //double[] initialArray = null;
        double[] initialArray = RandomArrayGenerator.generateArray(100);
        System.out.println("Sort and search test");
        System.out.println(testedInstance.sorterDescription());
        System.out.println("Initial array is: "+ Arrays.toString(initialArray));
        try {
            testedInstance.sort(initialArray);
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        System.out.println("Sorted array is:  "+ Arrays.toString(initialArray));

        double itemToSearch = initialArray[(int)(Math.random()*(initialArray.length-1))];
        System.out.println("Searching item " + itemToSearch);
        int position = testedInstance.search(initialArray, itemToSearch);
        if (position == -1) {
            System.out.println("Item " + itemToSearch + "not found");
        }
        System.out.println("Index of searched item " + itemToSearch +": "+ position);
        System.out.println("");
    }


    public static void main(String[] args) {
        testSorterInstance(new SelectionSorter());
        testSorterInstance(new CocktailSorter());
    }
}
