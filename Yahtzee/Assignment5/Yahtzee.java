
/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import java.util.Arrays;

import acm.graphics.GObject;
import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {

	private int[][] scoreList;// This matrix will save each player's scores.
	private int[][] selectedCategoriesList;// This matrix will save categories
											// which are selected
	private int[] totalScoresList;// This array is for totalscores to find out
									// how is winner.

	public static void main(String[] args) {
		new Yahtzee().start(args);
	}

	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		scoreList = new int[17][nPlayers];
		selectedCategoriesList = new int[17][nPlayers];
		totalScoresList = new int[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		for (int i = 0; i < 13; i++) {
			for (int j = 1; j <= nPlayers; j++) {
				// Here is one player's one whole move.
				display.printMessage(playerNames[j - 1] + "'s turn! Click ''Roll Dice''button to roll dice.");
				display.waitForPlayerToClickRoll(j);
				firsRoll();
				display.printMessage(
						playerNames[j - 1] + " Select dices which you want to roll again.you have 2 tries.");
				display.waitForPlayerToSelectDice();
				RollOver();
				display.printMessage(
						playerNames[j - 1] + " Select dices which you want to rollagain.you have 1 try left.");
				display.waitForPlayerToSelectDice();
				RollOver();
				display.printMessage(playerNames[j - 1] + " chose category.");
				int category = display.waitForPlayerToSelectCategory();
				if (selectedCategoriesList[category - 1][j - 1] == 0) {
					countScore(category, dices);
					display.updateScorecard(category, j, countScore(category, dices));
					updateScores(j);
					fillAllLists(j, category, countScore(category, dices));
				} else {
					while (selectedCategoriesList[category - 1][j - 1] != 0) {
						display.printMessage("This catregory is already selected, select other one.");
						category = display.waitForPlayerToSelectCategory();
						if (selectedCategoriesList[category - 1][j - 1] == 0) {
							countScore(category, dices);
							display.updateScorecard(category, j, countScore(category, dices));
							updateScores(j);
							fillAllLists(j, category, countScore(category, dices));
							break;
						}
					}
				}
			}
		}

		bringWinner(totalScoresList, nPlayers);
	}

	// This method fills special matrices and array above with scores and 1s for
	// used categories.
	private void fillAllLists(int j, int category, int i) {
		fillScoreList(scoreList, category, countScore(category, dices), j);
		fillTotalScoreList(totalScoresList, j, updateTotalScore(scoreList, j));
		fillCategoriesList(selectedCategoriesList, category, j);
	}

	// This method fills only special parts of scoreList matrix.
	private void updateScores(int j) {
		updateUpperScore(scoreList, j);
		updateUpperBonus(scoreList, j);
		updateLowerScore(scoreList, j);
		updateTotalScore(scoreList, j);
	}

	// This method determines who won by totalScoresList array.
	private void bringWinner(int[] totalScoresList2, int nPlayers2) {
		int onewinner = 0;
		// To find player with max score
		for (int i = 0; i < totalScoresList2.length; i++) {
			if (totalScoresList2[i] > totalScoresList2[onewinner]) {
				onewinner = i;

			}
		}
		int twowinners = -1;
		// To check if there is second player with max score
		for (int j = 0; j < totalScoresList2.length; j++) {
			if (totalScoresList2[onewinner] == totalScoresList[j] && j != onewinner) {
				twowinners = j;
			}
		}
		// If there is only one player with max score.
		if (twowinners == -1) {
			display.printMessage("Congratulations, " + playerNames[onewinner]
					+ " ,you are the winner with a total score of " + totalScoresList2[onewinner]);
		}
		int threewinners = -1;
		if (twowinners != -1) {
			// To check if there is third player with max score.
			for (int k = 0; k < totalScoresList2.length; k++) {
				if (totalScoresList2[twowinners] == totalScoresList[k] && k != onewinner && k != twowinners) {
					threewinners = k;
				}
			}
		}
		// If there are 3 players with max score.
		if (twowinners != -1 && threewinners != -1 && nPlayers2 == 4) {
			display.printMessage("Congratulations, " + playerNames[onewinner] + ", " + playerNames[twowinners] + ", "
					+ playerNames[threewinners] + " ,you are winners with a total scores of "
					+ totalScoresList2[onewinner]);
		}
		// If there are 2 players with max score.
		if (twowinners != -1 && threewinners == -1 && nPlayers2 >= 3) {
			display.printMessage("Congratulations, " + playerNames[onewinner] + ", " + playerNames[twowinners]
					+ " ,you are winners with a total scores of " + totalScoresList2[onewinner]);
		}
		// If all players got same scores and it is tie.
		boolean tie = true;
		for (int m = 0; m < totalScoresList.length; m++) {
			if (totalScoresList2[0] != totalScoresList2[m]) {
				tie = false;
			}
		}
		if (tie && nPlayers2 != 1) {
			display.printMessage("It is tie. All player got same scores.");
		}
	}

	// This method fills only totalScoresList array from 0 to nPlaers - 1.
	private void fillTotalScoreList(int[] totalScoresList2, int j, int updateTotalScore) {
		totalScoresList2[j - 1] = updateTotalScore;
	}

	// This method fills only selectedCategoriesList whit 1s after category is
	// selected.
	private void fillCategoriesList(int[][] selectedCategoriesList2, int category, int j) {
		selectedCategoriesList2[category - 1][j - 1] = 1;
	}

	// This method calculates the bonus and updates bonusscorecard.
	private int updateUpperBonus(int[][] scoreList2, int j) {
		int bonus = 0;
		if (updateUpperScore(scoreList, j) >= 63) {
			display.updateScorecard(UPPER_BONUS, j, 35);
			bonus = 35;
		} else {
			display.updateScorecard(UPPER_BONUS, j, 0);
		}
		return bonus;
	}

	// This method calculates total score and updates totalscorecard.
	private int updateTotalScore(int[][] scoreList2, int j) {
		int totalscore = 0;
		totalscore = scoreList2[UPPER_SCORE - 1][j - 1] + scoreList2[UPPER_BONUS - 1][j - 1]
				+ scoreList2[LOWER_SCORE - 1][j - 1];
		display.updateScorecard(TOTAL, j, totalscore);
		return totalscore;
	}

	// This method calculates lower score and updates lowerscorecard.
	private int updateLowerScore(int[][] scoreList2, int i) {
		int lowerscore = 0;
		for (int j = 8; j < 15; j++) {
			lowerscore += scoreList2[j][i - 1];
		}
		display.updateScorecard(LOWER_SCORE, i, lowerscore);
		return lowerscore;
	}

	// This method calculates upper score and updates upperscorecard.
	private int updateUpperScore(int[][] scoreList2, int i) {
		int upperscore = 0;
		for (int j = 0; j < 6; j++) {
			upperscore += scoreList2[j][i - 1];
		}
		display.updateScorecard(UPPER_SCORE, i, upperscore);
		return upperscore;
	}

	// This method fills only scoreList martix .
	private int[][] fillScoreList(int[][] scoreList2, int category, int countScore, int j) {
		scoreList2[category - 1][j - 1] = countScore(category, dices);
		scoreList2[UPPER_SCORE - 1][j - 1] = updateUpperScore(scoreList, j);
		scoreList2[UPPER_BONUS - 1][j - 1] = updateUpperBonus(scoreList, j);
		scoreList2[LOWER_SCORE - 1][j - 1] = updateLowerScore(scoreList, j);
		return scoreList2;
	}

	// This method calculates score based on categories.
	private int countScore(int category, int[] dices2) {
		int score = 0;
		if (category >= ONES && category <= SIXES) {
			for (int i = 0; i < N_DICE; i++) {
				if (dices[i] == category) {
					score += dices[i];
				}
			}
		} else if (category == THREE_OF_A_KIND) {
			if (threeOfaKind(dices)) {// checking
				for (int i = 0; i < N_DICE; i++) {
					score += dices[i];
				}
			} else {
				score = 0;
			}
		} else if (category == FOUR_OF_A_KIND) {
			if (FourOfaKind(dices)) {// checking
				for (int i = 0; i < N_DICE; i++) {
					score += dices[i];
				}
			} else {
				score = 0;
			}
		} else if (category == FULL_HOUSE) {
			if (FullHouse(dices)) {// checking
				score = 25;
			} else {
				score = 0;
			}
		} else if (category == SMALL_STRAIGHT) {
			if (SmallStraight(dices)) {// checking
				score = 30;
			} else {
				score = 0;
			}
		} else if (category == LARGE_STRAIGHT) {
			if (LargeStraight(dices)) {// checking
				score = 40;
			} else {
				score = 0;
			}
		} else if (category == YAHTZEE) {
			if (FiveOfaKind(dices)) {// checking
				score = 50;
			} else {
				score = 0;
			}
		} else if (category == CHANCE) {
			for (int i = 0; i < N_DICE; i++) {
				score += dices[i];
			}
		}
		return score;
	}

	// This method checks SmallStraight category.
	private boolean SmallStraight(int[] dices) {
		int k = 0;
		int m = 0;
		Arrays.sort(dices);
		for (int i = 0; i < dices.length - 1; i++) {
			if (dices[i] + 1 == dices[i + 1]) {
				k++;
			}
			if (k == 3) {
				return true;
			}
		}
		for (int i = 1; i < dices.length - 1; i++) {
			if (dices[i] + 1 == dices[i + 1]) {
				m++;
			}
			if (m == 3) {
				return true;
			}
		}
		return false;
	}

	// This method checks LargeStraight category.
	private boolean LargeStraight(int[] dices) {
		int k = 0;
		Arrays.sort(dices);
		for (int i = 0; i < dices.length - 1; i++) {
			if (dices[i] + 1 == dices[i + 1]) {
				k++;
			}
			if (k == 4) {
				return true;
			}
		}
		return false;
	}

	// This method checks FullHouse category.
	private boolean FullHouse(int[] dices) {
		Arrays.sort(dices);
		int a1 = dices[0];
		int a2 = dices[1];
		int a3 = dices[2];
		int a4 = dices[3];
		int a5 = dices[4];
		if (a1 == a2 && a1 == a3 && a1 == a4 && a1 == a5) {
			return false;
		}
		if ((a1 == a2 && a3 == a4 && a4 == a5) || (a1 == a2 && a2 == a3 && a4 == a5)) {
			return true;
		}
		return false;
	}

	// This method checks threeOfaKind category.
	private boolean threeOfaKind(int[] dices) {
		for (int i = 0; i < dices.length; i++) {
			int k = 0;
			for (int j = 0; j < dices.length; j++) {
				if (dices[i] == dices[j]) {
					k++;
					if (k >= 3) {
						return true;
					}
				}
			}

		}
		return false;
	}

	// This method checks FourOfaKind category.
	private boolean FourOfaKind(int[] dices) {
		for (int i = 0; i < dices.length; i++) {
			int k = 0;
			for (int j = 0; j < dices.length; j++) {
				if (dices[i] == dices[j]) {
					k++;
					if (k >= 4) {
						return true;
					}
				}
			}

		}
		return false;
	}

	// This method checks Yahtzee category.
	private boolean FiveOfaKind(int[] dices) {
		for (int i = 0; i < dices.length; i++) {
			int k = 0;
			for (int j = 0; j < dices.length; j++) {
				if (dices[i] == dices[j]) {
					k++;
					if (k == 5) {
						return true;
					}
				}
			}

		}
		return false;
	}

	private int[] dices = new int[N_DICE];// This array is for rolled dices.

	// This method rolls over selected dices.
	private void RollOver() {
		for (int i = 0; i < N_DICE; i++) {
			if (display.isDieSelected(i)) {
				int rollednumber = rgen.nextInt(1, 6);
				dices[i] = rollednumber;
			}
		}
		display.displayDice(dices);

	}

	// This method displays firstrolled dices.
	private void firsRoll() {
		for (int i = 0; i < N_DICE; i++) {
			int rollednumber = rgen.nextInt(1, 6);
			dices[i] = rollednumber;
		}
		display.displayDice(dices);
	}

	/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();

}
