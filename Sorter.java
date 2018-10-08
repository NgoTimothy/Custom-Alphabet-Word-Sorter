package edu.iastate.cs228.proj2;

import java.util.Comparator;

public interface Sorter {
	/**
	 * Sorts words according to the Comparator comp.
	 * 
	 * Note that Lexicon implements Comparator. It knows
	 * the alphabet and character order, so it is the 
	 * object you need to pass to this method. 
	 * 
	 * @param words words to be sorted.
	 * @param comp Comparator used to sort words.
	 */
	public void sort(String[] words, Comparator<String> comp);
}
