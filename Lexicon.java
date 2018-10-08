package edu.iastate.cs228.proj2;

import java.util.Comparator;

/**
 * This interface is used only by LexiconImpl. The implementing methods are 
 * already represented in the template (see LexiconImpl). The interface isn't important to 
 * the correct functioning of your code, but your LexiconImpl must implement this
 * interface to facilitate automated grading. (Some tests will replace your 
 * implementation with one we know to be correct, so that we can test other
 * parts of your program independently from your implementation.) 
 *
 */
public interface Lexicon extends Comparator<String>{

	

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	int compare(String a, String b);

	/**
	 * Uses binary search to find the order of key.
	 * @param key
	 * @return ordering value for key or -1 if key is an invalid character.
	 */
	int getCharacterOrdering(char key);

	/**
	 * Returns whether or not word is valid.
	 * @param word word to be checked.
	 * @return
	 */
	boolean isValid(String word);
	
}
