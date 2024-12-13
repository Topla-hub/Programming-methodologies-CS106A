/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	// In this program, Karel will create a checkerboard.
	public void run(){
	fillBoard();
	}
	
	
    //pre:karel is standing on 1x1 facing east.
	//post:In any world beepers will be arranged in a checkerboard pattern.
	private void fillBoard(){
		fillStrings();
		fillColumn();
	}
	
	
	//pre:karel is standing on 1x1 facing east.
	//post:karel will fill this column with beepers leaving one out.
	private void fillColumn() {
		if (frontIsBlocked()){
			turnLeft();
			while (facingNorth()){
				if (frontIsClear()){
					putBeeper();
					move();
				}else{
						putBeeper();
						turnLeft();
				}
				if (frontIsClear()){
					move();
				}else{
					turnLeft();
					if (frontIsClear()){
						move();
						turnLeft();
					}
				}
			}
		}	
	}
	
	
	//pre:karel is standing on 1x1 facing east.
	//post:karel will fill this strings with beepers leaving one out.
	private void fillStrings() {
		while (frontIsClear()){
			fillRightWay();
			fillLeftWay();
		}
	}
	
	
	//pre:karel is standing on 1x1 facing east.
	//post:karel will fill one string in right direction with beepers leaving one out.
	private void fillRightWay(){
		while (facingEast()){
			if (frontIsClear()){
				putBeeper();
				move();
			}else{
					putBeeper();
					turnLeft();
			}
			if (frontIsClear()){
				move();
				if (facingNorth()){
					turnLeft();
					move();
				}
			}else{
				turnLeft();
				if (frontIsClear()){
					move();
					turnLeft();
				}
			}
			}
	}
	
	
	//pre:karel is standing on (the end of the first string x 2) facing west.
	//post:karel will fill one string in left direction with beepers leaving one out.
	private void fillLeftWay(){
		while (facingWest()){
			if (frontIsClear()){
				putBeeper();
				move();
			}else{
					putBeeper();
					turnRight();
			}
			if (frontIsClear()){
				move();
			}else{
				turnRight();
				if (frontIsClear()){
				move();
				turnRight();
				}
			}
		}
	}
	
}