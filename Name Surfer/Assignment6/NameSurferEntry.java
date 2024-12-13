
/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;
import acmx.export.java.io.FileReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

	/* Constructor: NameSurferEntry(line) */
	/**
	 * Creates a new NameSurferEntry from a data line as it appears in the data
	 * file. Each line begins with the name, which is followed by integers
	 * giving the rank of that name for each decade.
	 */
	private String name = null;
	private ArrayList<Integer> ranksList = new ArrayList<>();

	public NameSurferEntry(String line) {
		String[] subStrings = line.split(" ");// Splits line into substrings of
												// name and ranks. and makes
												// array of this substrings .
		name = subStrings[0];
		// Fills arraylist of ranksList .
		for (int i = 1; i < 12; i++) {
			int rank = Integer.parseInt(subStrings[i]);
			ranksList.add(rank);
		}

	}

	/* Method: getName() */
	/**
	 * Returns the name associated with this entry.
	 */
	public String getName() {
		return name;
	}

	/* Method: getRank(decade) */
	/**
	 * Returns the rank associated with an entry for a particular decade. The
	 * decade value is an integer indicating how many decades have passed since
	 * the first year in the database, which is given by the constant
	 * START_DECADE. If a name does not appear in a decade, the rank value is 0.
	 */
	public int getRank(int decade) {
		return ranksList.get(decade - 1);
	}

	/* Method: toString() */
	/**
	 * Returns a string that makes it easy to see the value of a
	 * NameSurferEntry.
	 */
	public String toString() {
		String ranks = ranksList.toString().replaceAll(",", "");
		return name + ranks;
	}
}
