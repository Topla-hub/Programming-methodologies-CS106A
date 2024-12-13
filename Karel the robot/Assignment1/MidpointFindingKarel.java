/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	// In this program , Karel will find midpoint of the first string and put beeper.
			public void run(){
				goToMidpoint();
				
			}
			//pre:karel is standing on 1x1 facing east .
			//post:karel is standing on the midpoint of the first string .
			private void goToMidpoint() {
				fillFirstString();
				takeLastOnes();
				takeExtraOnes();
				putBeeper();
			}
			
			
			//pre:karel is standing on 2x1 facing west.
			//post:karel is standing on the midpoint of the first string .
			private void takeExtraOnes() {
				//If the world has an odd length Karel will be standing to the left of the midpoint facing west.
				//If the world has an even length Karel will be standing one of the midpoints.
				while (beepersPresent()){
					move();
					if(noBeepersPresent()){
						turnAround();
						move();
						pickBeeper();
						move();
					}
				}
				//this will move karel to the midpoint if the world will have odd length.
				if (facingWest()){
					turnAround();
					move();
				}
				
			}
			
			
			//pre:karel is standing in the end of the string facing west.
			//post:karel is standing on 2x1 facing west.
			private void takeLastOnes() {
				turnAround();
				pickBeeper();
				while (frontIsClear()){
					move();
				}
				pickBeeper();
				turnAround();
				move();
			}
			
			
			//pre:karel is standing on 1x1 facing west.
			//post:karel is standing in the end of the string facing west.
			private void fillFirstString(){
				while (frontIsClear()){
					putBeeper();
					move();
				}
				if (frontIsBlocked()){
					putBeeper();
				}
			}

}
