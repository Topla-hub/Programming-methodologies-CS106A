
import java.awt.Font;

import acm.graphics.GCanvas;
import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GOval;

public class HangmanCanvasExtention extends GCanvas {
	/** Resets the display so that only the scaffold appears */
	public void reset() {
		removeAll();
		buildScaffold();
	}

	/**
	 * Updates the word on the screen to correspond to the current state of the
	 * game. The argument string shows what letters have been guessed so far;
	 * unguessed letters are indicated by hyphens.
	 */
	private GLabel guessedLetters = null;

	public void displayWord(String word) {
		if (guessedLetters != null) {
			remove(guessedLetters);
		}
		guessedLetters = new GLabel(word, getWidth() / 2.0 - BEAM_LENGTH - 25, getHeight() - 35);
		Font size = new Font(word, Font.BOLD, 18);
		guessedLetters.setFont(size);
		add(guessedLetters);
	}

	/**
	 * Updates the display to correspond to an incorrect guess by the user.
	 * Calling this method causes the next body part to appear on the scaffold
	 * and adds the letter to the list of incorrect guesses that appears at the
	 * bottom of the window.
	 */
	private GLabel incorrectLettersList;

	public void noteIncorrectGuess(int turns, String letter) {
		incorrectLettersList = new GLabel(letter, getWidth() / 2.0 - BEAM_LENGTH - 25, getHeight() - 20);
		if (turns == 7) {
			drawHead();
			add(incorrectLettersList);
		} else if (turns == 6) {
			drawBody();
			add(incorrectLettersList);
		} else if (turns == 5) {
			drawLeftArm();
			add(incorrectLettersList);
		} else if (turns == 4) {
			drawRightArm();
			add(incorrectLettersList);
		} else if (turns == 3) {
			drawLeftLeg();
			add(incorrectLettersList);
		} else if (turns == 2) {
			drawRightLeg();
			add(incorrectLettersList);
		} else if (turns == 1) {
			drawLeftFoot();
			add(incorrectLettersList);
		} else if (turns == 0) {
			drawRightFoot();
			add(incorrectLettersList);
		}
	}

	/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

	private void buildScaffold() {
		GLine scaffold = new GLine(getWidth() / 2.0 - BEAM_LENGTH, 10, getWidth() / 2.0 - BEAM_LENGTH,
				10 + SCAFFOLD_HEIGHT);
		GLine beam = new GLine(getWidth() / 2.0 - BEAM_LENGTH, 10, getWidth() / 2.0, 10);
		GLine rope = new GLine(getWidth() / 2.0, 10, getWidth() / 2.0, 10 + ROPE_LENGTH);
		add(scaffold);
		add(beam);
		add(rope);
	}

	private void drawHead() {
		GOval head = new GOval(getWidth() / 2.0 - HEAD_RADIUS, 10 + ROPE_LENGTH, 2 * HEAD_RADIUS, 2 * HEAD_RADIUS);
		add(head);
	}

	private void drawBody() {
		GLine body = new GLine(getWidth() / 2.0, 10 + ROPE_LENGTH + 2 * HEAD_RADIUS, getWidth() / 2.0,
				10 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH);
		add(body);
	}

	private void drawLeftArm() {
		GLine shoulder = new GLine(getWidth() / 2.0 - UPPER_ARM_LENGTH,
				10 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD, getWidth() / 2.0,
				10 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD);
		add(shoulder);
		GLine arm = new GLine(getWidth() / 2.0 - UPPER_ARM_LENGTH,
				10 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD, getWidth() / 2.0 - UPPER_ARM_LENGTH,
				10 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
		add(arm);
	}

	private void drawRightArm() {
		GLine shoulder = new GLine(getWidth() / 2.0, 10 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD,
				getWidth() / 2.0 + UPPER_ARM_LENGTH, 10 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD);
		add(shoulder);
		GLine arm = new GLine(getWidth() / 2.0 + UPPER_ARM_LENGTH,
				10 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD, getWidth() / 2.0 + UPPER_ARM_LENGTH,
				10 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
		add(arm);
	}

	private void drawLeftLeg() {
		GLine hip = new GLine(getWidth() / 2.0 - HIP_WIDTH, 10 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH,
				getWidth() / 2.0, 10 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH);
		add(hip);
		GLine leg = new GLine(getWidth() / 2.0 - HIP_WIDTH, 10 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH,
				getWidth() / 2.0 - HIP_WIDTH, 10 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
		add(leg);
	}

	private void drawRightLeg() {
		GLine hip = new GLine(getWidth() / 2.0, 10 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH,
				getWidth() / 2.0 + HIP_WIDTH, 10 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH);
		add(hip);
		GLine leg = new GLine(getWidth() / 2.0 + HIP_WIDTH, 10 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH,
				getWidth() / 2.0 + HIP_WIDTH, 10 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
		add(leg);
	}

	private void drawLeftFoot() {
		GLine foot = new GLine(getWidth() / 2.0 - HIP_WIDTH - FOOT_LENGTH,
				10 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH, getWidth() / 2.0 - HIP_WIDTH,
				10 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
		add(foot);
	}

	private void drawRightFoot() {
		GLine foot = new GLine(getWidth() / 2.0 + HIP_WIDTH,
				10 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH,
				getWidth() / 2.0 + HIP_WIDTH + FOOT_LENGTH,
				10 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
		add(foot);
	}

}
