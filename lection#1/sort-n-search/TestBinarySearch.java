import java.util.*;


public class TestBinarySearch {
	public static void main(String[] args) {
		System.out.println("Sort function test");
		double[] unsorted = {2.0,4.0,4.0,6.0,4.0,6.0,7.0,5.0,6.0,4.0,4.0,4.0,43.0,5.0,6.0,7.0,4.0,3.0,5.0,5.0,3.0};
		System.out.println("Initial array is: "+ Arrays.toString(unsorted));
		double[] sorted = Sort.sortArray(unsorted);
		System.out.println("Sorted array is: "+ Arrays.toString(sorted));
		int pos = BinarySearch.searchInArray(sorted, 4.0);
		System.out.println("4.0 is on index "+ pos);	
	}
}
