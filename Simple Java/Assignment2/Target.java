
/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {
	private static final int R1 = 72;
	private static final double R2 = 72 * 1.65 / 2.54;
	private static final double R3 = 72 * 0.76 / 2.54;

	// This program will draw target.
	public void run() {
		drawTarget();
	}

	// This method will draw 3 circles
	private void drawTarget() {
		drawCircle1();
		drawCircle2();
		drawCircle3();
	}

	// This method will draw the smallest circle
	private void drawCircle3() {
		GOval circle3 = new GOval(getWidth() / 2 - R3, getHeight() / 2 - R3, 2 * R3, 2 * R3);
		add(circle3);
		circle3.setFilled(true);
		circle3.setColor(Color.RED);

	}

	// This method will draw the middle circle
	private void drawCircle2() {
		GOval circle2 = new GOval(getWidth() / 2 - R2, getHeight() / 2 - R2, 2 * R2, 2 * R2);
		add(circle2);
		circle2.setFilled(true);
		circle2.setColor(Color.WHITE);

	}

	// This method will draw the biggest circle
	private void drawCircle1() {
		GOval circle1 = new GOval(getWidth() / 2 - R1, getHeight() / 2 - R1, 2 * R1, 2 * R1);
		add(circle1);
		circle1.setFilled(true);
		circle1.setColor(Color.RED);
	}

}
