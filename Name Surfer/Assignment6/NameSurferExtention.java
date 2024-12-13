import acm.graphics.GLabel;
import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurferExtention extends Program implements NameSurferConstants {

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
	JButton DeleteButton = new JButton("Delete");
	// For adding classes.
	private NameSurferDataBase dataBase;
	private NameSurferEntry entry;
	private NameSurferGraphExtention graph;

	public void init() {

		add(Label, SOUTH);
		add(nameTextField, SOUTH);
		add(GraphButton, SOUTH);
		add(ClearButton, SOUTH);
		add(DeleteButton, SOUTH);
		addActionListeners();
		nameTextField.addActionListener(this);

		dataBase = new NameSurferDataBase(NAMES_DATA_FILE);

		graph = new NameSurferGraphExtention();
		add(graph, CENTER);
		graph.update();

	}

	/* Method: actionPerformed(e) */
	/**
	 * This class is responsible for detecting when the buttons are clicked, so
	 * you will have to define a method to respond to button actions.
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
		} else if (e.getSource() == DeleteButton) {
			String name = nameTextField.getText();
			if (!name.equals("")) {
				entry = dataBase.findEntry(name);
				// Name must be in dataBase to dalete graph.
				if (entry != null) {
					graph.delete(entry);
					graph.update();
				}
			}
		}

	}
}