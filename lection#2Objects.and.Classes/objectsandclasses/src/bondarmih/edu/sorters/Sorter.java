package bondarmih.edu.sorters;

public abstract class Sorter {
    public abstract void sort (double[] unsorted);
    public abstract int search(double[] sortedArray, double soughtForItem);
    public abstract String sorterDescription();
}
