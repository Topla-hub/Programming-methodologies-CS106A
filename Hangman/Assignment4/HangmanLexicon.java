
/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import acm.util.*;
import acmx.export.java.io.FileReader;

public class HangmanLexicon extends Hangman {

	private RandomGenerator rgen = RandomGenerator.getInstance();
	private ArrayList<String> List = new ArrayList<String>();// for lexicon
																// list.

	// To add text file's words to List.
	public HangmanLexicon() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			while (true) {
				String word = reader.readLine();
				if (word == null) {
					break;
				}
				List.add(word);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			println("File not found");
		} catch (IOException e) {
			println("Error while reading");
		}
	}

	/** Returns the number of words in the lexicon for random generating. */
	public int getWordCount() {
		return List.size();
	}

	/** Returns the word at the specified index generated from the list. */
	public String getWord() {
		String word = List.get(rgen.nextInt(1, List.size()));
		return word;
	};
}
