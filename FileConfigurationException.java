package edu.iastate.cs228.proj2;

/**
 * 
 * @author Timothy Ngo
 * This class is thrown when a file gives more than one character in one line for the alphabet list or when
 * there is a word that is created with characters that do no exist in the custom alphabet. 
 */
public class FileConfigurationException extends Exception {
	//TODO: implement appropriate message, etc. 
	public FileConfigurationException() {
		super("File Configuration is incorrect");
	}
	
}