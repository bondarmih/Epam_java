package sort;

public class testSort {
	public static void Main(String[] args) {
	System.out.println("Sort function test");
	double[] unsorted = {5D, 0.1D, 28.3D, 5.6D, 3D};
	System.out.println("Initial array is: "+ unsorted.toString());
	double[] sorted = new Sort.sortArray(unsorted);
	System.out.println("Sorted array is: "+ sorted.toString());	
	}
}
