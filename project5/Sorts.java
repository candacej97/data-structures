package project5;

//----------------------------------------------------------------------------
//Sorts.java               by Dale/Joyce/Weems                     Chapter 11
//
//Test harness used to run sorting algorithms.
//----------------------------------------------------------------------------

import java.util.*;
import java.text.DecimalFormat;

public class Sorts {
	static int SIZE = 10000; // size of array to be sorted
	static int[] values = new int[SIZE]; // values to be sorted
	
	// COUNTERS FOR THE SORT ALGO COMPARISONS
	static long ssComps = 0; // for collecting comparison numbers for selection sort
	static long bsComps = 0; // for collecting comparison numbers for bubble sort
	static long sbsComps = 0; // for collecting comparison numbers for short bubble sort
	static long isComps = 0; // for collecting comparison numbers for insertion sort
	static long msComps = 0; // for collecting comparison numbers for merge sort
	static long qsComps = 0; // for collecting comparison numbers for quick sort
	static long hsComps = 0; // for collecting comparison numbers for heap sort
	
	// COUNTERS FOR THE SORT ALGO SWAPS
	static long ssSwaps = 0; // for collecting swap numbers for selection sort
	static long bsSwaps = 0;
	static long sbsSwaps = 0;
	static long isSwaps = 0;
	static long msSwaps = 0;
	static long qsSwaps = 0;
	static long hsSwaps = 0;

	static void initValues()
	// Initializes the values array with random integers from 0 to 99.
	{
		Random rand = new Random();
		for (int index = 0; index < SIZE; index++)
			values[index] = Math.abs(rand.nextInt()) % SIZE*3;
	}

	static public boolean isSorted()
	// Returns true if the array values are sorted and false otherwise.
	{
		for (int index = 0; index < (SIZE - 1); index++)
			if (values[index] > values[index + 1])
				return false;
		return true;
	}

	static public void swap(int index1, int index2)
	// Precondition: index1 and index2 are >= 0 and < SIZE.
	//
	// Swaps the integers at locations index1 and index2 of the values array.
	{
		int temp = values[index1];
		ssSwaps++;
		qsSwaps++;
		hsSwaps++;
		values[index1] = values[index2];
		values[index2] = temp;
	}

	static public void printValues()
	// Prints all the values integers.
	{
		int value;
		DecimalFormat fmt = new DecimalFormat("00");
		System.out.println("The values array is:");
		for (int index = 0; index < SIZE; index++) {
			value = values[index];
			if (((index + 1) % 10) == 0)
				System.out.println(fmt.format(value));
			else
				System.out.print(fmt.format(value) + " ");
		}
		System.out.println();
	}

	/////////////////////////////////////////////////////////////////
	//
	// Selection Sort

	static int minIndex(int startIndex, int endIndex)
	// Returns the index of the smallest value in
	// values[startIndex]..values[endIndex].
	{
		int indexOfMin = startIndex;
		for (int index = startIndex + 1; index <= endIndex; index++){
			ssComps++;
			if (values[index] < values[indexOfMin])
				indexOfMin = index;
		}
		return indexOfMin;
	}

	static void selectionSort()
	// Sorts the values array using the selection sort algorithm.
	{
		int endIndex = SIZE - 1;
		for (int current = 0; current < endIndex; current++) {
			swap(current, minIndex(current, endIndex));
		}
	}

	/////////////////////////////////////////////////////////////////
	//
	// Bubble Sort... TODO Don't do this because its been done on the spec

	static void bubbleUp(int startIndex, int endIndex)
	// Switches adjacent pairs that are out of order
	// between values[startIndex]..values[endIndex]
	// beginning at values[endIndex].
	{
		for (int index = endIndex; index > startIndex; index--) {
			bsComps++;
			if (values[index] < values[index - 1]) {
				bsSwaps++;
				swap(index, index - 1);
			}
		}
	}

	static void bubbleSort()
	// Sorts the values array using the bubble sort algorithm.
	{
		int current = 0;

		while (current < (SIZE - 1)) {
			bubbleUp(current, SIZE - 1);
			current++;
		}
	}

	/////////////////////////////////////////////////////////////////
	//
	// Short Bubble Sort

	static boolean bubbleUp2(int startIndex, int endIndex)
	// Switches adjacent pairs that are out of order
	// between values[startIndex]..values[endIndex]
	// beginning at values[endIndex].
	//
	// Returns false if a swap was made; otherwise, returns true.
	{
		boolean sorted = true;
		for (int index = endIndex; index > startIndex; index--) {
			sbsComps++;
			if (values[index] < values[index - 1]) {
				sbsSwaps++;
				swap(index, index - 1);
				sorted = false;
			}
		}
		return sorted;
	}

	static void shortBubble()
	// Sorts the values array using the bubble sort algorithm.
	// The process stops as soon as values is sorted.
	{
		int current = 0;
		boolean sorted = false;
		while ((current < (SIZE - 1)) && !sorted) {
			sorted = bubbleUp2(current, SIZE - 1);
			current++;
		}
	}

	/////////////////////////////////////////////////////////////////
	//
	// Insertion Sort

	static void insertItem(int startIndex, int endIndex)
	// Upon completion, values[0]..values[endIndex] are sorted.
	{
		boolean finished = false;
		int current = endIndex;
		boolean moreToSearch = true;
		while (moreToSearch && !finished) {
			isComps++;
			if (values[current] < values[current - 1]) {
				isSwaps++;
				swap(current, current - 1);
				current--;
				moreToSearch = (current != startIndex);
			} else
				finished = true;
		}
	}

	static void insertionSort()
	// Sorts the values array using the insertion sort algorithm.
	{
		for (int count = 1; count < SIZE; count++)
			insertItem(0, count);
	}

	/////////////////////////////////////////////////////////////////
	//
	// Merge Sort TODO add the counter

	static void merge(int leftFirst, int leftLast, int rightFirst, int rightLast)
	// Preconditions: values[leftFirst]..values[leftLast] are sorted.
	// values[rightFirst]..values[rightLast] are sorted.
	//
	// Sorts values[leftFirst]..values[rightLast] by merging the two subarrays.
	{
		int[] tempArray = new int[SIZE];
		int index = leftFirst;
		int saveFirst = leftFirst; // to remember where to copy back

		while ((leftFirst <= leftLast) && (rightFirst <= rightLast)) {
			msComps++;
			if (values[leftFirst] < values[rightFirst]) {
				msSwaps++;
				tempArray[index] = values[leftFirst];
				leftFirst++;
			} else {
				msSwaps++;
				tempArray[index] = values[rightFirst];
				rightFirst++;
			}
			index++;
		}

		while (leftFirst <= leftLast)
		// Copy remaining items from left half.

		{
			msSwaps++;
			tempArray[index] = values[leftFirst];
			leftFirst++;
			index++;
		}

		while (rightFirst <= rightLast)
		// Copy remaining items from right half.
		{
			msSwaps++;
			tempArray[index] = values[rightFirst];
			rightFirst++;
			index++;
		}

		for (index = saveFirst; index <= rightLast; index++){
			msSwaps++;
			values[index] = tempArray[index];
		}

	}

	static void mergeSort(int first, int last)
	// Sorts the values array using the merge sort algorithm.
	{
		if (first < last) {
			int middle = (first + last) / 2;
			mergeSort(first, middle);
			mergeSort(middle + 1, last);
			merge(first, middle, middle + 1, last);
		}
	}

	/////////////////////////////////////////////////////////////////
	//
	// Quick Sort

	static int split(int first, int last) {
		int splitVal = values[first];
		int saveF = first;
		boolean onCorrectSide;

		first++;
		do {
			onCorrectSide = true;
			while (onCorrectSide) // move first toward last
				if (values[first] > splitVal)
					onCorrectSide = false;
				else {
					first++;
					onCorrectSide = (first <= last);
				}
			qsComps++;
			onCorrectSide = (first <= last);
			while (onCorrectSide) // move last toward first
				if (values[last] <= splitVal)
					onCorrectSide = false;
				else {
					last--;
					onCorrectSide = (first <= last);
				}
			qsComps++;
			if (first < last) {
				swap(first, last);
				first++;
				last--;
			}
		} while (first <= last);

		swap(saveF, last);
		return last;
	}

	static void quickSort(int first, int last) {
		if (first < last) {
			int splitPoint;

			splitPoint = split(first, last);
			// values[first]..values[splitPoint - 1] <= splitVal
			// values[splitPoint] = splitVal
			// values[splitPoint+1]..values[last] > splitVal

			quickSort(first, splitPoint - 1);
			quickSort(splitPoint + 1, last);
		}
	}

	/////////////////////////////////////////////////////////////////
	//
	// Heap Sort

	static int newHole(int hole, int lastIndex, int item)
	// If either child of hole is larger than item this returns the index
	// of the larger child; otherwise it returns the index of hole.
	{
		int left = (hole * 2) + 1;
		int right = (hole * 2) + 2;
		if (left > lastIndex)
			// hole has no children
			return hole;
		else if (left == lastIndex) {
			hsComps++;
			// hole has left child only
			if (item < values[left])
				// item < left child
				return left;
			else
				// item >= left child
				return hole;
		}
		else {
			hsComps++;
			// hole has two children
			if (values[left] < values[right]) {
				hsComps++;
				// left child < right child
				if (values[right] <= item)
					// right child <= item
					return hole;
				else
					// item < right child
					return right;
			}
			else {
				hsComps++;
				// left child >= right child
				if (values[left] <= item)
					// left child <= item
					return hole;
				else
					// item < left child
					return left;
			}	
		}
	}

	static void reheapDown(int item, int root, int lastIndex)
	// Precondition: Current root position is "empty".
	//
	// Inserts item into the tree and ensures shape and order properties.
	{
		int hole = root; // current index of hole
		int newhole; // index where hole should move to

		newhole = newHole(hole, lastIndex, item); // find next hole
		while (newhole != hole) {
			hsSwaps++;
			values[hole] = values[newhole]; // move value up
			hole = newhole; // move hole down
			newhole = newHole(hole, lastIndex, item); // find next hole
		}
		values[hole] = item; // fill in the final hole
	}

	static void heapSort()
	// Sorts the values array using the heap sort algorithm.
	{
		int index;
		// Convert the array of values into a heap.
		for (index = SIZE / 2 - 1; index >= 0; index--)
			reheapDown(values[index], index, SIZE - 1);

		// Sort the array.
		for (index = SIZE - 1; index >= 1; index--) {
			swap(0, index);
			reheapDown(values[0], 0, index - 1);
		}
	}

	/////////////////////////////////////////////////////////////////
	//
	// Main

	public static void main(String[] args) {
		initValues(); //sets the initial values
//		printValues(); // prints all values
		if (isSorted() == true)
			System.out.println("Values ARE sorted.");
		else
			System.out.println("Values are NOT sorted.");
//		System.out.println("values is sorted: " + isSorted());
		System.out.println();

		// TODO make call to sorting method here (just remove //)
//		 selectionSort();
//		 bubbleSort(); // IN THE SPEC ALREADY!
//		 shortBubble();
//		 insertionSort();
//		 mergeSort(0, SIZE - 1);
//		 quickSort(0, SIZE - 1);
		 heapSort();

//		printValues(); //prints all values
//		System.out.println("values is sorted: " + isSorted());
		 if (isSorted() == true)
			System.out.println("Values ARE sorted.");
		else
			System.out.println("Values are NOT sorted.");
		System.out.println();
		
		System.out.println("Number of Comparisons:");
		System.out.println(hsComps);
		
		System.out.println("Number of Swaps:");
		System.out.println(hsSwaps);
	}
}
