package bondarmih.edu.sorters;

import bondarmih.edu.utility.ArrayUtility;

public class CocktailSorter extends Sorter{
    public void sort (double[] unsorted) throws IllegalArgumentException {
        if (unsorted == null) throw new IllegalArgumentException( "Array can`t be null" );
        else {
            int bottomEdge = 0;
            int topEdge = unsorted.length - 1;
            while (bottomEdge <= topEdge) {
                for (int i = bottomEdge; i < topEdge; i++) {
                    if (unsorted[i] > unsorted[i + 1]) {
                        ArrayUtility.swap(unsorted, i, i + 1);
                    }
                }
                topEdge--;
                for (int i = topEdge; i > bottomEdge; i--) {
                    if (unsorted[i] < unsorted[i - 1]) {
                        ArrayUtility.swap(unsorted, i, i - 1);
                    }
                }
                bottomEdge++;
            }
        }
    }

    public int search(double[] sortedArray, double soughtForItem) {
        if (sortedArray.length == 0) return -1;
        int pos;
        int minPos = 0;
        int maxPos = sortedArray.length - 1;

        while (sortedArray[minPos] < soughtForItem && sortedArray[maxPos] > soughtForItem) {
            pos = (int) Math.round(minPos + (soughtForItem - sortedArray[minPos]) * (maxPos - minPos) / (sortedArray[maxPos] - sortedArray[minPos]));

            if (sortedArray[pos] < soughtForItem) minPos = pos + 1;
            else if (sortedArray[pos] > soughtForItem) maxPos = pos - 1;
            else return ArrayUtility.findFirstEntry(pos, sortedArray);
        }
        if (sortedArray[minPos] == soughtForItem) return ArrayUtility.findFirstEntry(minPos, sortedArray);
        else if (sortedArray[maxPos] == soughtForItem) return ArrayUtility.findFirstEntry(maxPos, sortedArray);
        else return -1;
    }

    public String sorterDescription() {
        return "CocktailSorter: cocktail sort and interpolation search";
    }
}
