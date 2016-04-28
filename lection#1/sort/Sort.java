package sort;

public class Sort {
	public static double[] sortArray(double[] unsorted) {
		double[] sorted = new double[unsorted.length]; 
		for (int i=0; i<unsorted.length-1; i++) {
			double min = unsorted[i];
			for (int k=i+1; k<unsorted.length-1; k++) {
				if (unsorted[k]<min) {min = unsorted[i];}
			}
			sorted[i] = min;
		}
	return sorted;
	}
}
