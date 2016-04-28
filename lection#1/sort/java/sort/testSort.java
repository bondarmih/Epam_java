import java.util.*;


public class testSort {
	public static void main(String[] args) {
		System.out.println("Sort function test");
		double[] unsorted = {5.0, 0.1, 28.3, 5.6, 3.0};
		System.out.println("Initial array is: "+ Arrays.toString(unsorted));
		double[] sorted = Sort.sortArray(unsorted);
		System.out.println("Sorted array is: "+ Arrays.toString(sorted));	
	}
}
