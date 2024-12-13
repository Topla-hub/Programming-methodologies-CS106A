
/** This extention gives user opportunity to play game how many times he wants  */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;
import java.util.ArrayList;

public class HangmanExtention extends ConsoleProgram {
	private HangmanLexicon choose;
	private HangmanCanvas canvas;
	private int turns = 8;
	private String word;
	private String Dashes = "";
	private boolean playAgain = false;

	// Adds a canvas to the game.
	public void init() {
		canvas = new HangmanCanvas();
		add(canvas);
	}

	public void run() {
		while (true) {
			createHangman();
			play();
			if (playAgain == false) {
				break;
			}
		}

	}

	// This method creates starting phase of the game.
	// draws scaffold , chooses word .
	private void createHangman() {
		canvas.reset();
		word = chooseWord();
		println("Welcome to Hangman !");
		int n = word.length();
		for (int i = 0; i < n; i++) {
			Dashes += "-";
		}
		canvas.displayWord(Dashes);
		println("The word now looks like this : " + Dashes);
		println("You have " + turns + " guesses left ");
	}

	// This method chooses word from lexicon text file.
	private String chooseWord() {
		choose = new HangmanLexicon();
		String choosenWord = choose.getWord();
		String finalWord = choosenWord
				.toUpperCase();/**
								 * for user entering lowercase and uppercase
								 * letters
								 */
		return finalWord;
	}

	// For collecting guessed letters.
	private ArrayList<String> guessedLetters = new ArrayList<String>();

	// In this method there are game stages and user inputs reviewed.
	private void play() {
		String incorect = "";// This string is for collecting incorrect guesses.
		while (turns > 0) {
			String s = readLine("Your guess is: ");
			if (isNotLetter(s)) {
				println("Please enter letter ");
			} else if (s.length() != 1) {
				println("Please enter only one letter");
			} else {
				String b = s.toUpperCase();/**
											 * for user entering lowercase and
											 * uppercase letters
											 */
				if (word.contains(b) && !guessedLetters.contains(b)) {
					changeDashes(b);
					println("You have " + turns + " guesses left ");
					canvas.displayWord(Dashes);
					guessedLetters.add(b);
				} else if (guessedLetters.contains(b)) {
					println("You have already guessed this letter enter different one");
				} else if (!word.contains(b)) {
					incorect += b;
					turns--;
					canvas.noteIncorrectGuess(turns, incorect);
					println("There are no " + s + "'s in the word ");
					println("The word now looks like this : " + Dashes);
					println("You have " + turns + " guesses left ");
				}

			}
			// for winning game
			if (!Dashes.contains("-")) {
				println("you won");
				break;
			}
		}
		// for losing game.
		if (turns == 0) {
			println("you are completely hung :(");
			println("The word was : " + word);
			println("You lose ");
			println("Do you want to play again");
			String answer = readLine();
			if (answer.equals("yes")) {
				playAgain = true;
				turns = 8;
			}
		}

	}

	// This method returns is users input is letter or not .
	private boolean isNotLetter(String s) {
		char c = s.charAt(0);
		if (c >= 'a' && c < 'z') {
			return false;
		}
		if (c >= 'A' && c < 'Z') {
			return false;
		}

		return true;
	}

	// this method changes dashes every time user guesses the letter.
	private void changeDashes(String s) {
		for (int i = 0; i < word.length(); i++) {
			if (s.charAt(0) == word.charAt(i)) {
				Dashes = Dashes.substring(0, i) + s.charAt(0) + Dashes.substring(i + 1);
			}
		}
		println("The word now looks like this : " + Dashes);
	}

}
