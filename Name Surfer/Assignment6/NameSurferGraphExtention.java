import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraphExtention extends GCanvas implements NameSurferConstants, ComponentListener {

	/**
	 * Creates a new NameSurferGraph object that displays the data.
	 */

	public NameSurferGraphExtention() {

		addComponentListener(this);

	}

	/**
	 * Clears the list of name surfer entries stored inside this class.
	 */
	public void clear() {
		entriesList.clear();
	}

	// This method deletes specific line graph.
	public void delete(NameSurferEntry entry) {
		String name = entry.getName();
		// Deletes specific name and entry form hashmap.
		entriesList.remove(name);
	}

	/* Method: addEntry(entry) */
	/**
	 * Adds a new NameSurferEntry to the list of entries on the display. Note
	 * that this method does not actually draw the graph, but simply stores the
	 * entry; the graph is drawn by calling update.
	 */
	// For saving data of entries which we will use to draw line graphs.
	private HashMap<String, int[]> entriesList = new HashMap<>();

	private HashMap<String, Integer> nameColor = new HashMap<>();// For coloring
	// specific
	// graphs.
	private int colorvalue = 0;

	public void addEntry(NameSurferEntry entry) {
		String name = entry.getName();
		int[] ranksList = new int[11];
		fillranksList(ranksList, entry);
		// Fills hashmap of entriesList.
		if (!entriesList.containsKey(name)) {
			entriesList.put(name, ranksList);
		}
		// Fills hashmap of nameColor.
		if (!nameColor.containsKey(name)) {
			nameColor.put(name, colorvalue);
			colorvalue++;
		}
	}

	// This method makes array of ranks for each given name.
	private void fillranksList(int[] ranksList, NameSurferEntry entry) {
		for (int i = 0; i < 11; i++) {
			ranksList[i] = entry.getRank(i + 1);
		}
	}

	/**
	 * Updates the display image by deleting all the graphical objects from the
	 * canvas and then reassembling the display according to the list of
	 * entries. Your application must call update after calling either clear or
	 * addEntry; update is also called whenever the size of the canvas changes.
	 */
	public void update() {
		removeAll();
		drawLines();
		drawDecadeLables();
		drawGraphs(entriesList);
	}

	// This method draws background for line graphs .
	private void drawLines() {
		for (int i = 0; i < 11; i++) {
			GLine vertical = new GLine(0 + i * getWidth() / NDECADES, 0, 0 + i * getWidth() / NDECADES, getHeight());
			add(vertical);
		}
		GLine upperLine = new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);
		add(upperLine);
		GLine lowerLine = new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE);
		add(lowerLine);
	}

	// this method adds decade labels on the background at bottom.
	private void drawDecadeLables() {
		for (int i = 0; i < 11; i++) {
			int k = START_DECADE + i * 10;
			GLabel decade = new GLabel(Integer.toString(k));
			Font font = new Font("decade", Font.PLAIN, calculateFont(getHeight(), getWidth()));
			decade.setFont(font);
			add(decade, 0 + i * getWidth() / NDECADES, getHeight() - 5);
		}

	}

	// This method draws line graphs with its labels for every name .
	private void drawGraphs(HashMap<String, int[]> entriesList2) {
		// For coloring line graphs in 4 colors.
		ArrayList<Color> Colors = new ArrayList<>();
		Colors.add(Color.BLACK);
		Colors.add(Color.RED);
		Colors.add(Color.BLUE);
		Colors.add(Color.GREEN);// Yellow is not clearly visible.
		for (String name : entriesList.keySet()) {
			// Gets array of ranks form hashmap, for getting line's Y.
			// coordinates.
			int[] ranksList = entriesList.get(name);
			// This for loop draws lines.
			for (int i = 0; i < 10; i++) {
				double startx = 0 + i * getWidth() / NDECADES;
				double starty;
				if (ranksList[i] == 0) {
					starty = getHeight() - GRAPH_MARGIN_SIZE;
				} else {
					starty = GRAPH_MARGIN_SIZE + (getHeight() - 2 * GRAPH_MARGIN_SIZE) * ranksList[i] / MAX_RANK;
				}
				double endx = 0 + (i + 1) * getWidth() / NDECADES;
				double endy;
				if (ranksList[i + 1] == 0) {
					endy = getHeight() - GRAPH_MARGIN_SIZE;
				} else {
					endy = GRAPH_MARGIN_SIZE + (getHeight() - 2 * GRAPH_MARGIN_SIZE) * ranksList[i + 1] / MAX_RANK;
				}
				GLine line = new GLine(startx, starty, endx, endy);
				line.setColor(Colors.get(nameColor.get(name) % 4));
				add(line);
			}
			// This for loop draws labels.
			for (int i = 0; i < 11; i++) {
				double x = 0 + i * getWidth() / NDECADES;
				if (ranksList[i] == 0) {
					double y = getHeight() - GRAPH_MARGIN_SIZE;
					GLabel nameRank = new GLabel(name + " *");
					nameRank.setColor(Colors.get(nameColor.get(name) % 4));
					add(nameRank, x, y);
				} else {
					double y = GRAPH_MARGIN_SIZE + getHeight() * ranksList[i] / MAX_RANK;
					GLabel nameRank = new GLabel(name + " " + ranksList[i]);
					nameRank.setColor(Colors.get(nameColor.get(name) % 4));
					Font font = new Font("nameRank", Font.PLAIN, calculateFont(getHeight(), getWidth()));
					nameRank.setFont(font);
					add(nameRank, x, y);
				}
			}
		}

	}

	// This method regulates font size according to windows height and width.
	public int calculateFont(int height, int width) {
		int size = height / 100 + width / 100;
		return size;
	}

	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) {
	}

	public void componentMoved(ComponentEvent e) {
	}

	public void componentResized(ComponentEvent e) {
		update();
	}

	public void componentShown(ComponentEvent e) {
	}
}