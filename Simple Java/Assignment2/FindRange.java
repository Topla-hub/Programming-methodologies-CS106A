
/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	// With this static variable user will the stop the program.
	private static final int SENTINEL = 0;

	// After the user uses the sentinel , the program chooses max and min
	// between the given numbers.
	public void run() {
		println("This program finds the largest and smallest numbers.");
		int n = readInt("?");
		if (n == SENTINEL) {
			println("You entered not enough nubers to compare.");
		}

		if (n != SENTINEL) {
			// We will use c variable to find min.
			// We will use d variable to find max.
			// It is not necessary c and b to be 0s , We are free to assign any
			// integer value to them
			int c = 0;
			int d = 0;
			while (n != SENTINEL) {
				int b = readInt("?");
				if (b == SENTINEL) {
					break;
				}
				// If b > n max value becomes b
				if (b > n) {
					d = b;
				}
				// If b < n min value becomes b
				if (b < n) {
					c = b;
				}

			}
			if (n != 0) {
				println("smallest " + c);
				println("biggest " + d);
			}
		}
	}

}
