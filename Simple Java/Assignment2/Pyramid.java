
/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

	/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

	/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

	/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 15;

	// This program will draw pyramid.
	public void run() {
		// We will need this variable in case when bricks in base are odd
		double k = 0.5 * (BRICKS_IN_BASE % 2);
		// In this loop the amount and level of the bricks changes through the
		// variables j, i and k
		for (int j = 0; j <= BRICKS_IN_BASE; j++) {
			for (int i = 1; i <= BRICKS_IN_BASE - j; i++) {
				GRect brick = new GRect(getWidth() / 2 - ((BRICKS_IN_BASE / 2 + k - 0.5 * j) - (i - 1)) * BRICK_WIDTH,
						getHeight() - (j + 1) * BRICK_HEIGHT, BRICK_WIDTH, BRICK_HEIGHT);
				add(brick);
			}
		}

	}

}
