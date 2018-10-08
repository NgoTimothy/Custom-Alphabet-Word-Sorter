package edu.iastate.cs228.proj2;

import java.util.Arrays;
import java.util.Comparator;
/**
 * 
 * @author Timothy Ngo
 *
 */
public class LexiconImpl implements Lexicon, Comparator<String> {

    /***
     * Lookup table mapping characters in lexicographical order to
     * to their input order. This is public to support automated grading. 
     */
	public CharacterValue[] characterOrdering; 

    /***
     * Creates an array of CharacterValue from characterOrdering.  Sorts
     * it using java.util.Arrays.sort().
     * @param characterOrdering character order from configuration file
     */	
	public LexiconImpl(char[] characterOrdering) {
		this.characterOrdering = new CharacterValue[characterOrdering.length];
		for(int i = 0; i < characterOrdering.length; ++i) {
			this.characterOrdering[i] = new CharacterValue(i, characterOrdering[i]);
		}
		Arrays.sort(this.characterOrdering, new Comparator<CharacterValue>() {
			/**
			 * Overrides comparator and makes array of type CharacterValue compare based off
			 * char types
			 * @param o1 first CharacterValue
			 * @param o2 second CharacterValue
			 */
			@Override
			public int compare(CharacterValue o1, CharacterValue o2) {
				if (o1.character > o2.character) {
					return 1;
				}
				else if (o1.character < o2.character){
					return -1;
				}
				else {
					return 0;
				}
			}
		});
	}


    /***
     * Compares two words based on the configuration
     * @param a first word
     * @param b second word
     * @return negative if a<b, 0 if equal, postive if a>b
     */
	@Override
	public int compare(String a, String b) {
		int shortStrLength = 0;
		if (a.length() > b.length()) {
			shortStrLength = b.length();
		}
		else {
			shortStrLength = a.length();
		}
		for (int i = 0; i < shortStrLength; ++i) {
			if (getCharacterOrdering(a.charAt(i)) > getCharacterOrdering(b.charAt(i))) {//Checks if character at a has a larger value
				return 1;
			}
			else if (getCharacterOrdering(a.charAt(i)) < getCharacterOrdering(b.charAt(i))) {//Checks if character at b has a larger value
				return -1;
			}
		}
		//At this stage all characters have been equal up until the shortest length
		if (b.length() != a.length()) {//If they are not equal length it does to this loop
			if (b.length() > a.length()) {//Returns -1 because b is greater
				return -1;
			}
			else {//Returns 1 because a is greater in this situation
				return 1;
			}
		}
		else {
		return 0;
		}
	}
	
	/**
	 * Uses binary search to find the order of key.
	 * @param key
	 * @return ordering value for key or -1 if key is an invalid character.
	 */
	public int getCharacterOrdering(char key) {
		if (characterOrdering.length >= 0) {
			int index = binarySearch(characterOrdering, 0, characterOrdering.length-1, key);
			if (index >= 0) {
				return characterOrdering[index].value;
			}
			else {
				return -1;
			}
		}
		else {
		return -1;
		}

	}
	
	/**
	 * This is my binary search helper method
	 * @param arr
	 * @param l
	 * @param r
	 * @param key
	 * @return
	 */
	private int binarySearch(CharacterValue[] arr, int l, int r, char key) {
		if (r >= l) {
			int mid = l + (r-l) /2;
			if (arr[mid].character == key) {
				return mid;
			}
			if (arr[mid].character > key) {
				return binarySearch(arr, l, mid-1, key);
			}
			return binarySearch(arr, mid+1, r, key);
		}
		return -1;
	}

	/**
	 * Searches characterOrdering for key via binary search.
	 * This is public only to facilitate automated grading. 
	 * @param characterOrdering the specified sort order
     * @param key the search term
	 * @return ordering value for key or -1 if key is an invalid character.
	 */
	public static class CharacterValue {
		public int value;
		public char character;
		
		public CharacterValue(int value, char character) {
			this.value = value;
			this.character = character;
		}
		
		public boolean equals(Object o) {
			if (o == null || o.getClass() != this.getClass()) {
				return false;
			}
			CharacterValue other = (CharacterValue) o;
			return value == other.value && character == other.character;
		}
		
	}
	
	/**
	 * Returns whether or not word is valid according to the alphabet
	 * known to this lexicon. 
	 * @param word word to be checked.
	 * @return true if valid. false otherwise
	 */
	public boolean isValid(String word) {
		boolean found = false;
		for (int j = 0; j < word.length(); ++j) { //Loops through all the characters the string 
			for(int i = 0; i < characterOrdering.length; ++i) {//Loops through all characters in characterOrdering array to check if string char is there
			if (characterOrdering[i].character == word.charAt(j)) {
				found = true;
				break;
			}
		}
		if (found != true) {//This means character was not in lexicon alphabet so word is invalid
			return false;
		}
		found = false; //Reset found
		}
		return true;//After passing all these conditions the word is valid 

	}
	
}
