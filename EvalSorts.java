package edu.iastate.cs228.proj2;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Timothy Ngo
 * In small data sets 100 and less, the selection sort was much faster
 * than quick sort and merge sort. 
 * Once the data sets were 1000 or more, selection sort was incredibly slow
 * compared to MergeSort and QuickSort.
 * One thing to note was that MergeSort was slightly faster than QuickSort 
 * in all of these cases.  I believe if that QuickSort was implemented
 * with a pivot at the end of the array or a random point in the middle QuickSort
 * would have performed better. 
 * Also selection sort for data sets 10,000 and above performed incredibly slow compared to 
 * QuickSort and MergeSort.  
 * This helps prove that performance O(n^2) for selection sort it kept growing at a much larger rate
 * compared to QuickSort and MergeSort's O(nlogn).
 * 
 */
public class EvalSorts {
	public static final int kNumberOfWordsToSort = 100000;

	/**
	 main is responsible only for extracting fileNames from args,
     reading the files, and constructing an instance of the this 
     class configured with the input data.
	 FileNotFoundException and FileConfigurationException exceptions 
	 should be handled in main, i.e., print out appropriate message
	 to the user.
	 * @throws FileConfigurationException 
	 * @throws FileNotFoundException 
	*/
	public static void main(String args[]) throws FileNotFoundException, FileConfigurationException {
		char[] alphabet   = null;  //ref to the Lexicon it creates. 
		String[] wordList = null;  //ref to the list of words to be sorted. 
		EvalSorts theApp  = null;  //ref to the app. 
		LexiconImpl comp  = null;  //the concrete lexicon your app uses. 
		

		alphabet = readCharacterOrdering(args[0]);
		comp = new LexiconImpl(alphabet);
		wordList = readWordsFile(args[1], comp);
		//TODO
		/*
		 * 
		 *      Here you should add code that extracts the file names from the args array,
		 *      opens and reads the data from the files,constructs an instance of Lexicon from the character order file, 
		 *      and then create an instance of this class (EvalSorts) to act as a configured
		 *      instance of the application. After you have constructed the configured
		 *      instance, you should start it running (see below). 
		 *      
		 *      
		 *   
		 *  
		*/		
		

		//configure an instance of the app
		theApp = new EvalSorts(comp, wordList, kNumberOfWordsToSort);
		//now execute that instance
		theApp.runSorts();
		
	}

	
	private String[] words; //ref to the word list
	private Lexicon lex;    //ef to the relevant lexicon	
	private int numWordsToSort = kNumberOfWordsToSort;
	
	/**
	 * This constructor configures an instance of EvalSorts to sort input read
	 * my main, using the character order read by main and now embedded in
	 * an instance of Lexicon
	 * @param lex the instance of lexicon to be used
	 * @param wordList the wordlist (as array of string)  to be sorted
	 * @param numWordsToSort each sort will be repeated until it has sorted
	 *                       this many words. 
	 */
	public EvalSorts(Lexicon lex, String[] wordList, int numWordsToSort) {
		//TODO
		this.lex = lex;
		words = wordList;
		this.numWordsToSort = numWordsToSort;
		
	}

	/**
	 * runSorts() performs the sort evaluation. 
	 * 
	 * Note: The three sorters extend a common base
	 * so they share the same interface for starting the sort and collecting statistics. 
	 * Thus, you should create instances of the sorter and save references to each in an 
	 * array of base type. This allows you to use a simple loop to run all the reports and 
	 * collect the statistics.   
	 */
	public void runSorts(){
		
		SorterWithStatistics[] sorters = new SorterWithStatistics[3];
		
		sorters[0] = new QuickSort();
		sorters[1] = new MergeSort();
		sorters[2] = new SelectionSort();
		
		for (int i = 0; i < sorters.length; ++i) {
			sorters[i].sort(words, lex);
			System.out.println(sorters[i].getReport());
		}

	}
	
	/**
	 * Reads the characters contained in filename and returns them as a character array.
	 * @param filename the file containing the list of characters
	 * @returns an char array representing the ordering of characters to be used 
	 *          or null if there is a FileNotFoundException.
	 */
	public static char[] readCharacterOrdering(String filename) 
			throws FileNotFoundException, FileConfigurationException {
		 
		try {
			File file = new File(filename);
			Scanner sc = new Scanner(file);
			char[] charArr = new char[kNumberOfWordsToSort];//Makes a char array at max number of elements for words
			int index = 0;
			while (sc.hasNextLine()) {
				String str = sc.nextLine();
				if (str.length() > 1) {
					throw new FileConfigurationException();
				}
				char c = str.charAt(0);
				int i = 0;
				while (charArr[i] != 0) {//If not null
					if (c == charArr[i]) {//If char already exists in char arr close file and throw exeception
					sc.close();
					throw new FileConfigurationException();
					}
					++i;
				}
				charArr[index] = c;//Otherwise add it to charArr and continue
				++index;
				
			}
			char[] returnedArr = new char[index+1];//Resizes array to fit just right
			for (int i = 0; i < returnedArr.length; ++i) {//Copy's elements into a returnable array
				returnedArr[i] = charArr[i];
			}
			sc.close();//Close File
			return returnedArr;//Return array
			
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("File Not Found");
		}  catch (FileConfigurationException e) {
			throw new FileConfigurationException();
		}
	}
	
	/**
	 * Reads the words from the file and returns them as a String array.
	 * @param filename file containing words
	 * @return the words contained in the file or null if there was a FileNotFoundException.
	 */
	public static String[] readWordsFile(String filename, Lexicon comp)
			throws FileNotFoundException, FileConfigurationException {
		 
		try {
			File file = new File(filename);
			Scanner sc = new Scanner(file);
			String[] words = new String[kNumberOfWordsToSort];
			int index = 0;
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				if (!comp.isValid(line)) {
					throw new FileConfigurationException();
				}
				words[index] = line;
				++index;
			}
			String[] returnWords = new String[index];
			for (int i = 0; i < returnWords.length; ++i) {
				returnWords[i] = words[i];
			}
			sc.close();
			return returnWords;
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("File Not Found");
		} catch (FileConfigurationException e) {
			throw new FileConfigurationException();
		}
	}

}
