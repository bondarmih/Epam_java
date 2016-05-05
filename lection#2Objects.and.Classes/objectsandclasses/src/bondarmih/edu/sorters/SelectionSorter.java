package bondarmih.edu.sorters;

import bondarmih.edu.utility.ArrayUtility;

public class SelectionSorter extends Sorter {
	public void sort (double[] unsorted)  throws IllegalArgumentException {
		if (unsorted == null) throw new IllegalArgumentException( "Array can`t be null" );
		else {
			for (int i = 0; i < unsorted.length; i++) {
				double min = unsorted[i];
				int minIndex = i;
				for (int k = i + 1; k < unsorted.length; k++) {
					if (unsorted[k] < min) {
						minIndex = k;
						min = unsorted[k];
					}
				}
				ArrayUtility.swap(unsorted, i, minIndex);
			}
		}
	}

	public int search(double[] sortedArray, double soughtForItem) {
		if (sortedArray == null) throw new IllegalArgumentException( "Array can`t be null" );
		else {
			if (sortedArray.length == 0) return -1;
			float rangeDirection = Math.signum(Double.compare(sortedArray[sortedArray.length - 1], sortedArray[0]));

			if (((soughtForItem > sortedArray[sortedArray.length - 1]) && (rangeDirection > 0)) || ((soughtForItem < sortedArray[sortedArray.length - 1]) && (rangeDirection < 0)) || ((soughtForItem < sortedArray[0]) && (rangeDirection > 0)) || ((soughtForItem > sortedArray[0]) && (rangeDirection < 0))) {
				System.out.println("Item out of array bounds");
				return -1;
			}
			int pos = sortedArray.length - 1;
			int prevPos;
			int counter = 0;
			int maxPos = sortedArray.length - 1;
			int minPos = 0;
			do {

				prevPos = pos;
				pos = (maxPos + minPos) / 2;
				if (sortedArray[pos] == soughtForItem) return ArrayUtility.findFirstEntry(pos, sortedArray);
				if (((sortedArray[pos] < soughtForItem) && (rangeDirection > 0)) || ((sortedArray[pos] > soughtForItem) && (rangeDirection < 0)))
					minPos = pos;
				if (((sortedArray[pos] > soughtForItem) && (rangeDirection > 0)) || ((sortedArray[pos] < soughtForItem) && (rangeDirection < 0)))
					maxPos = pos;


				counter++;

			} while (Math.abs(pos - prevPos) >= 1 && counter < 100);
			System.out.println("Coincident item not found");
			return -1;
		}
	}
	public String sorterDescription () {
		return "SelectionSorter: selection sort and binary search";
	}
}
