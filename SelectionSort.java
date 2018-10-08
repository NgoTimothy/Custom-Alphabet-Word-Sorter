package edu.iastate.cs228.proj2;

import java.util.Comparator;
/**
 * 
 * @author Timothy Ngo
 *
 */
public class SelectionSort extends SorterWithStatistics {
	
	//This method will be called by the base class sort() method to 
	// actually perform the sort. 
	@Override
	public void sortHelper(String[] words, Comparator<String> comp) {
		//TODO: implement SelectionSort
		int k = 0;
		while (k < words.length) {
		int maxIndex = 0;
		for(int i = 1; i < words.length-k; ++i) {
			if (comp.compare(words[maxIndex], words[i]) < 0) {
				maxIndex = i;
			}
		}
		String temp = words[words.length-k-1];
		words[words.length-k-1] = words[maxIndex];
		words[maxIndex] = temp;
		++k;
		}
	}
}
