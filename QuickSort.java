package edu.iastate.cs228.proj2;

import java.util.Comparator;
/**
 * 
 * @author Timothy Ngo
 *
 */
public class QuickSort extends SorterWithStatistics {

	//This method will be called by the base class sort() method to 
	// actually perform the sort. 
	@Override
	public void sortHelper(String[] words, Comparator<String> comp) {
		//TODO: implement QuickSort;
		quickSort(words, 0, words.length-1, comp);
		
	}
	
	
	/**
     * Swap elements at indexes {@code i} and {@code j}
     * in the give array 
     * 
     * @param array
     * @param i
     * @param j
     */
    private void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    /**
     * Takes the String array and passes it through after testing to see if it is a valid list
     * @param list
     * @param comp
     */
    private void quickSort(String[] list, Comparator<String> comp)
	{
		if (list == null || list.length == 0)
			throw new RuntimeException("Null pointer or zero size");
		if (list.length == 1)
			return;


		quickSort(list, 0, list.length - 1, comp);
	}

    /**
     * Recursively calls itself, and finds a pivot index. Then splits the array in two.
     * @param list
     * @param first
     * @param last
     * @param comp
     */
	private void quickSort(String[] list, int first, int last, Comparator<String> comp)
	{
		if (last > first)
		{
			int pivotIndex = partition(list, first, last, comp);

			
			quickSort(list, first, pivotIndex - 1, comp);
			quickSort(list, pivotIndex + 1, last, comp);
		}
	}

	/** Partition the array list[first..last] */
	
	
	private int partition(String[] list, int first, int last, Comparator<String> comp)
	{
		String pivot = list[first]; // Choose the first element as the pivot
		int low = first + 1; // Index for forward search
		int high = last; // Index for backward search

		while (high > low)
		{
			// Search forward from left
			while (low <= high && comp.compare(list[low], pivot) <= 0) low++;

			// Search backward from right
			while (low <= high && comp.compare(list[high], pivot) > 0) high--;

			// Swap two elements in the list
			if (high > low)
			{
				swap(list, high, low);
			}
		}

		while (high > first && comp.compare(list[high], pivot) >= 0) high--;

		// Swap pivot with list[high]
		if (comp.compare(pivot, list[high]) > 0)
		{
			list[first] = list[high];
			list[high] = pivot;			
			return high;
		} 
		else
		{
			return first;
		}
	} // end partition
}
