
/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		int n = readInt("Enter number :");
		// We will use k variable to count steps .
		int k = 0;
		// In this loop variable b will be used for odd numbers and c for even
		// numbers.
		while (n != 1) {
			if (n % 2 == 1) {
				int b = 3 * n + 1;
				println(n + "is odd, so i make 3n +1:" + b);
				n = b;
				k = k + 1;
			}

			if (n % 2 == 0) {
				int c = n / 2;
				if (c == 1) {
					println(n + "is even , so i take half : " + c);
					break;
				}
				println(n + "is even , so i take half :" + c);
				n = c;
				k = k + 1;
			}

		}
		println("The process took " + (k + 1) + " to reach1");
	}

}
