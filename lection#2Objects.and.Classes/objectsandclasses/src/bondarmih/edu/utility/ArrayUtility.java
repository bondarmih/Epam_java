package bondarmih.edu.utility;
/**
 *
 * Created by bondarm on 04.05.16.
 */
public class ArrayUtility {
    public static int findFirstEntry (int initPos, double[] array) {
        int pos = initPos;
        while (array[pos] == array[initPos]) {
            pos = pos - 1;
        }
        pos = pos + 1;
        return pos;
    }
    public static void swap (double[] array, int firstSwappedItem, int secondSwappedItem) {
        double swapValue = array[firstSwappedItem];
        array[firstSwappedItem] = array[secondSwappedItem];
        array[secondSwappedItem] = swapValue;
    }
}
