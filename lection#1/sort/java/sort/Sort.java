import java.util.*;

public class Sort {
	public static double[] sortArray(double[] unsorted) {
		double[] sorted = new double[unsorted.length]; 
		for (int i=0; i<unsorted.length; i++) {
			double min = unsorted[i];
			int minIndex = i;
			for (int k=i+1; k<unsorted.length; k++) {
				if (unsorted[k]<min) {
					minIndex = k;
					min = unsorted[k];
				}
			}
			double swapValue = unsorted[i];
			unsorted[i] = unsorted[minIndex];
			unsorted[minIndex] = swapValue;
			sorted[i] = unsorted[i];
		}
	return sorted;
	}
}
