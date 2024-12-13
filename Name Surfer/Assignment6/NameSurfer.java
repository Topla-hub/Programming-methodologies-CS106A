
/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.graphics.GLabel;
import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

	/* Method: init() */
	/**
	 * This method has the responsibility for reading in the data base and
	 * initializing the interactors at the bottom of the window.
	 */
	// For buttons .
	JLabel Label = new JLabel("Name");
	JTextField nameTextField = new JTextField(10);
	JButton GraphButton = new JButton("Graph");
	JButton ClearButton = new JButton("Clear");
	// For adding classes.
	private NameSurferDataBase dataBase;
	private NameSurferEntry entry;
	private NameSurferGraph graph;

	public void init() {

		add(Label, SOUTH);
		add(nameTextField, SOUTH);
		add(GraphButton, SOUTH);
		add(ClearButton, SOUTH);
		addActionListeners();
		nameTextField.addActionListener(this);

		dataBase = new NameSurferDataBase(NAMES_DATA_FILE);

		graph = new NameSurferGraph();
		add(graph, CENTER);
		graph.update();

	}

	/* Method: actionPerformed(e) */
	/**
	 * This class is responsible for detecting when the buttons are clicked.
	 */

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == GraphButton || e.getSource() == nameTextField) {
			String name = nameTextField.getText();
			if (!name.equals("")) {
				entry = dataBase.findEntry(name);
				// Name must be in dataBase to draw graph.
				if (entry != null) {
					graph.addEntry(entry);
					graph.update();
				}
			}
		} else if (e.getSource() == ClearButton) {//Clears all graphs.
			graph.clear();
			graph.update();
		}
	}
}
