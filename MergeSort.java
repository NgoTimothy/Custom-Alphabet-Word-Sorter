package edu.iastate.cs228.proj2;

import java.util.Comparator;
/**
 * 
 * @author Timothy Ngo
 *
 */
public class MergeSort extends SorterWithStatistics {
	
	//This method will be called by the base class sort() method to 
	// actually perform the sort. 
	@Override
	public void sortHelper(String[] words, Comparator<String> comp) {
		//TODO: implement mergeSort. 
		sort(words, 0, words.length-1, comp);
		
	}
	
	/**
	 * Finds midpoint and splits arrays in half and then recursively calls itself
	 * @param words
	 * @param l
	 * @param r
	 * @param comp
	 * @return
	 */
	private String[] sort(String[] words, int l, int r, Comparator<String> comp) {
		if (l < r) {
			//Grabs middle point
			int mid = l + (r-l) /2;
			
			//Splits arrays in halves
			sort(words, l, mid, comp);
			sort(words, mid+1, r, comp);
			
			merge(words, l, mid, r, comp);
			
		}
		
		return words;
	}
	
	/**
	 * Takes two sorted arrays and merges them based on the character's value based on the custom alphabet
	 * @param words
	 * @param l
	 * @param m
	 * @param r
	 * @param comp
	 */
	private void merge(String[] words, int l, int m, int r, Comparator<String> comp) {
		//Find size of two sub arrays
		int lSize = m-l+1;
		int rSize = r-m;
		
		//Create temp arrays
		String[] lArr = new String[lSize]; 
		String[] rArr = new String[rSize];
		
		//Copy data in temp arrays
        for (int i=0; i<lSize; ++i) 
            lArr[i] = words[l + i]; 
        for (int j=0; j<rSize; ++j) 
            rArr[j] = words[m + 1+ j]; 
		
        int i = 0;
        int j = 0;
        
        int k = l;
        while (i < lSize && j < rSize) {
        	if(comp.compare(lArr[i], rArr[j]) >= 0) {//This means left array at given index is greater
        		words[k] = rArr[j];
        		++j;
        		++k;
        	}
        	else {
        		words[k] = lArr[i];
        		++i;
        		++k;
        	}
        }
        
        //Copy rest of remaining elements into array
        while(i < lSize) {
        	words[k] = lArr[i];
        	++k;
        	++i;
        }
        while(j < rSize) {
        	words[k] = rArr[j];
        	++k;
        	++j;
        }
	}

}
