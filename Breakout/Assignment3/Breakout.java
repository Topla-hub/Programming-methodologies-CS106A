
/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	/** Width of a brick */
	private static final int BRICK_WIDTH = (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 3;

	private RandomGenerator rgen = RandomGenerator.getInstance();

	/** Runs the Breakout program. */
	public void run() {
		createBreakOutWorld();
		addMouseListeners();

	}

	// This method creates Breakout elements.
	private void createBreakOutWorld() {
		addMouseListeners();
		buildBricks();
		paddle.setFilled(true);
		add(paddle);
		makeBall();

	}

	// This method will return colliding objects from ball 4 coordinates .
	private GObject getCollidingObject() {
		if (getElementAt(ball.getX(), ball.getY()) != null) {
			return getElementAt(ball.getX(), ball.getY());
		} else if (getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY()) != null) {
			return getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY());
		} else if (getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS) != null) {
			return getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS);
		} else if (getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS) != null) {
			return getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS);
		}
		return null;

	}

	// t is time for pause.
	private int t = 10;
	// This variables are for wining and losing game.
	private int BRICKS_NUMBER = NBRICKS_PER_ROW * NBRICK_ROWS;
	private int currentTurns = NTURNS;
	// ballStartX and ballStartY are ball initial coordinates.
	private GOval ball;
	private static final double ballStartX = WIDTH / 2.0 - BALL_RADIUS;
	private static final double ballStartY = HEIGHT / 2.0 - BALL_RADIUS;
	// vx an vy are ball initial velocities.
	double vx = rgen.nextDouble(1.0, 3.0);
	double vy = 3.0;

	private void makeBall() {
		// Create ball.
		ball = new GOval(ballStartX, ballStartY, 2 * BALL_RADIUS, 2 * BALL_RADIUS);
		ball.setFilled(true);
		add(ball);
		if (rgen.nextBoolean(0.5))
			vx = -vx;
		while (currentTurns > 0 && BRICKS_NUMBER != 0) {
			ball.move(vx, vy);
			pause(t);
			boundaries();
			Collide();
			loseTurns();

		}
		// Winning game.
		if (BRICKS_NUMBER == 0) {
			GLabel YouWon = new GLabel("YOU WON");
			add(YouWon, getWidth() / 2.0 - YouWon.getWidth() / 2.0, getHeight() / 2.0 + YouWon.getAscent());
		}

	}

	// This method counts user's turns.
	private void loseTurns() {
		if (ball.getY() + 2 * BALL_RADIUS >= getHeight()) {// Losing turns.
			currentTurns--;
			remove(ball);
			pause(1000);
			if (currentTurns > 0) {
				ball = new GOval(ballStartX, ballStartY, 2 * BALL_RADIUS, 2 * BALL_RADIUS);
				ball.setFilled(true);
				add(ball);
				if (rgen.nextBoolean(0.5))
					vx = -vx;
			} else {
				GLabel GameOver = new GLabel("GAME OVER");
				add(GameOver, getWidth() / 2.0 - GameOver.getWidth() / 2.0, getHeight() / 2.0 + GameOver.getAscent());
			}
		}
	}

	// This method makes ball stay in boundaries.
	private void boundaries() {
		if (ball.getX() <= 0 || ball.getX() + 2 * BALL_RADIUS >= getWidth()) {
			vx = -vx;
			pause(t);
		} else if (ball.getY() <= 0) {
			vy = -vy;
			pause(t);
		}
	}

	// This method will distinguish colliders.
	private void Collide() {
		GObject collider = getCollidingObject();
		if (collider == paddle) {
			vy = -vy;
			ball.setLocation(ball.getX(), paddle.getY() - 2 * BALL_RADIUS);
			pause(t);
		} else if (collider != paddle && collider != null) {
			vy = -vy;
			pause(t);
			remove(collider);
			BRICKS_NUMBER--;

		}

	}

	// This variables are for creating paddle.
	// StartX and starY are paddle initial coordinates.
	private static final double startX = WIDTH / 2.0 - PADDLE_WIDTH / 2.0;
	private static final int startY = HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT;
	GRect paddle = new GRect(startX, startY, PADDLE_WIDTH, PADDLE_HEIGHT);
	
	// This method will move paddle center along mouse.
	public void mouseMoved(MouseEvent e) {
		if (paddle != null) {
			paddle.setLocation(e.getX() - PADDLE_WIDTH / 2.0, startY);
		}
		if (paddle.getX() <= 0) {
			paddle.setLocation(0, startY);
		}
		if (paddle.getX() >= WIDTH - PADDLE_WIDTH) {
			paddle.setLocation(WIDTH - PADDLE_WIDTH, startY);// To fix ball
																// sticking on
																// paddle.
		}

	}

	// Brick width from given values is 36 . So bricks starting x coordinate
	// must be k=2, because 4 is left.
	int k = (WIDTH - (NBRICKS_PER_ROW * BRICK_WIDTH + BRICK_SEP * (NBRICKS_PER_ROW - 1))) / 2;

	// This method will build bricks.
	private void buildBricks() {
		for (int i = 0; i < NBRICK_ROWS; i++) {
			for (int j = 0; j < NBRICK_ROWS; j++) {
				GRect r = new GRect(k + i * (BRICK_WIDTH + BRICK_SEP), BRICK_Y_OFFSET + j * 12, BRICK_WIDTH,
						BRICK_HEIGHT);
				add(r);
				paintBricks(r, i, j);
			}
		}
	}

	private void paintBricks(GRect r, int i, int j) {
		if (j <= 1) {
			r.setFilled(true);
			r.setColor(Color.RED);
		} else if (1 < j && j <= 3) {
			r.setFilled(true);
			r.setColor(Color.ORANGE);
		} else if (3 < j && j <= 5) {
			r.setFilled(true);
			r.setColor(Color.YELLOW);
		} else if (5 < j && j <= 7) {
			r.setFilled(true);
			r.setColor(Color.GREEN);
		} else if (7 < j && j <= 9) {
			r.setFilled(true);
			r.setColor(Color.CYAN);
		}

	}

}
