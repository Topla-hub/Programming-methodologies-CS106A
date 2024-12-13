import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

public class NameSurferDataBase implements NameSurferConstants {

	/* Constructor: NameSurferDataBase(filename) */
	/**
	 * Creates a new NameSurferDataBase and initializes it using the data in the
	 * specified file. The constructor throws an error exception if the
	 * requested file does not exist or if an error occurs as the file is being
	 * read.
	 */
	// For saving data.
	private HashMap<String, String> dataBase = new HashMap<>();

	public NameSurferDataBase(String filename) {
		// For reading given file.
		try {
			BufferedReader rd = new BufferedReader(new FileReader(filename));
			while (true) {
				String line = rd.readLine();
				if (line == null) {
					break;
				}
				String name = line.substring(0, line.indexOf(" "));// Gets name
																	// from
																	// line.
				dataBase.put(name, line);// Fills hashmap with information.
			}
			rd.close();

		} catch (FileNotFoundException e) {
			System.out.println("file not found ");
		} catch (IOException e) {
			System.out.println("error while reading");
		}
	}

	/* Method: findEntry(name) */
	/**
	 * Returns the NameSurferEntry associated with this name, if one exists. If
	 * the name does not appear in the database, this method returns null.
	 */
	public NameSurferEntry findEntry(String name) {
		if (name.equals("")) {
			return null;
		} else {
			for (String key : dataBase.keySet()) {
				if (key.equals(changedName(name))) {
					String line = dataBase.get(key);
					return new NameSurferEntry(line);
				}
			}
		}
		return null;
	}

	// This method changes users input name in to proper form, to search it in
	// names database.(If the user enters the name in different case letters)
	private String changedName(String name) {
		String step1 = name.toUpperCase();
		String step2 = name.substring(1);
		String step3 = step1.charAt(0) + step2.toLowerCase();
		return step3;
	}
}
