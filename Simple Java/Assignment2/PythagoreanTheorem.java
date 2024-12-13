
/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {

	// This program will count value of hypotenuse.
	public void run() {
		println("Enter values of catheters.");
		int a = readInt("a:");
		int b = readInt("b:");
		// If the user enters negative values, counting the hypotenuse is
		// pointless. So program should bring appropriate message.
		if (a < 0 || b < 0) {
			println("catheters lenght cant be negative enter correct values");
		}
		// This loop will work when user enters correct values and it will count
		// hypotenuse.
		if (a > 0 & b > 0) {
			int k = a * a + b * b;
			double c = Math.sqrt(k);
			println("hypotenuse =" + c);
		}
	}
}
