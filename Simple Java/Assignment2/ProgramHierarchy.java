
/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {
	private static final int SIZEX = 170;
	private static final int SIZEY = 70;

	// This program will draw ProgramHierarchy graph.
	public void run() {
		drawGraph();
	}

	// This method will draw whole graph.
	private void drawGraph() {
		drawRectangles();
		writeProgramNames();
		drawLines();
		GLine line = new GLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
		add(line);
	}

	// This method will write program names in rectangles.
	private void writeProgramNames() {
		GLabel program = new GLabel("Program");
		add(program, getWidth() / 2 - program.getWidth() / 2, 1.5 * SIZEY + program.getAscent() / 2);

		GLabel consoleprogram = new GLabel("ConsoleProgram");
		add(consoleprogram, getWidth() / 2 - consoleprogram.getWidth() / 2,
				3.5 * SIZEY + consoleprogram.getAscent() / 2);

		GLabel graphicsprogram = new GLabel("GraphicsProgram ");
		add(graphicsprogram, getWidth() / 2 - 1.5 * SIZEX - graphicsprogram.getWidth() / 2,
				3.5 * SIZEY + graphicsprogram.getAscent() / 2);

		GLabel dialogprogram = new GLabel("DialogProgram");
		add(dialogprogram, getWidth() / 2 + 1.5 * SIZEX - dialogprogram.getWidth() / 2,
				3.5 * SIZEY + dialogprogram.getAscent() / 2);

	}

	// This method will connect rectangles with lines.
	private void drawLines() {
		GLine line1 = new GLine(getWidth() / 2, 2 * SIZEY, getWidth() / 2 - 1.5 * SIZEX, 3 * SIZEY);
		add(line1);
		GLine line2 = new GLine(getWidth() / 2, 2 * SIZEY, getWidth() / 2, 3 * SIZEY);
		add(line2);
		GLine line3 = new GLine(getWidth() / 2, 2 * SIZEY, getWidth() / 2 + 1.5 * SIZEX, 3 * SIZEY);
		add(line3);

	}

	// This method will draw rectangles.
	private void drawRectangles() {
		GRect rect1 = new GRect(getWidth() / 2 - SIZEX / 2, SIZEY, SIZEX, SIZEY);
		add(rect1);
		GRect rect2 = new GRect(getWidth() / 2 - SIZEX / 2, 3 * SIZEY, SIZEX, SIZEY);
		add(rect2);
		GRect rect3 = new GRect(getWidth() / 2 - 2 * SIZEX, 3 * SIZEY, SIZEX, SIZEY);
		add(rect3);
		GRect rect4 = new GRect(getWidth() / 2 + SIZEX, 3 * SIZEY, SIZEX, SIZEY);
		add(rect4);

	}
}
