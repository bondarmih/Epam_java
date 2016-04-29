public class BinarySearch {
	public static int searchInArray(double[] sortedArray, double soughtForItem) {
		
		float rangeDirection = Math.signum(Double.compare(sortedArray[sortedArray.length-1], sortedArray[0]));
		
		if (((soughtForItem>sortedArray[sortedArray.length-1])&&(rangeDirection>0))||((soughtForItem<sortedArray[sortedArray.length-1])&&(rangeDirection<0))||((soughtForItem<sortedArray[0])&&(rangeDirection>0))||((soughtForItem>sortedArray[0])&&(rangeDirection<0))) {
			System.out.println("Item out of array bounds");
			return -1;
		}
		int pos = sortedArray.length-1;
		int prevPos;
		int counter = 0;
		int maxPos = sortedArray.length-1;
		int minPos = 0;
		do {
			
			prevPos = pos;
			pos = (maxPos + minPos)/2;
			System.out.println("Index "+ (pos) + ", Value "+ sortedArray[pos]);
			if (sortedArray[pos] == soughtForItem) return pos;
			if (((sortedArray[pos] <  soughtForItem)&&(rangeDirection>0))||((sortedArray[pos] >  soughtForItem)&&(rangeDirection<0))) minPos = pos;
			if (((sortedArray[pos] >  soughtForItem)&&(rangeDirection>0))||((sortedArray[pos] <  soughtForItem)&&(rangeDirection<0))) maxPos = pos;


			counter ++;
			
		} while (Math.abs(pos-prevPos) >= 1 && counter < 100);
		System.out.println("Coincident item not found");
		return -1;
		
	}
}