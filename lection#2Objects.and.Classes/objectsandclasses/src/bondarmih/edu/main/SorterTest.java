package bondarmih.edu.main;

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
        double[] initialArray = {2.0,4.0,4.0,6.0,4.0,6.0,7.0,36.0,6.0,4.0,4.0,4.0,43.0,5.0,6.0,7.0,4.0,3.0,5.0,5.0,3.0};
        System.out.println("Sort and search test");
        System.out.println(testedInstance.sorterDescription());
        double itemToSearch = 4.0;
        int position;

        System.out.println("Initial array is: "+ Arrays.toString(initialArray));
        try {
            testedInstance.sort(initialArray);
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
        }
        System.out.println("Sorted array is:  "+ Arrays.toString(initialArray));

        position = testedInstance.search(initialArray, itemToSearch);
        System.out.println("Index of searched item " + itemToSearch +": "+ position);
        System.out.println("");
    }


    public static void main(String[] args) {
        testSorterInstance(new SelectionSorter());
        testSorterInstance(new CocktailSorter());
    }
}
