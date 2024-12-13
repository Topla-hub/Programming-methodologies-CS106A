/*
 * File: CollectNewspaperKarel.java
 * --------------------------------
 * At present, the CollectNewspaperKarel subclass does nothing.
 * Your job in the assignment is to add the necessary code to
 * instruct Karel to walk to the door of its house, pick up the
 * newspaper (represented by a beeper, of course), and then return
 * to its initial position in the upper left corner of the house.
 */

import stanford.karel.*;

public class CollectNewspaperKarel extends SuperKarel {

	// In this program Karel will take the newspaper and return home.
			public void run() {
				getOutside();
				pickNewspaper();
				getInside();
			}
			
			
			//pre:Karel is standing outside the house facing west with a newspaper in bag(6x3).
			//post:Karel is standing in the upper left corner of the house(3x1).
		private void getInside() {
				while (frontIsClear()){
					move();
				}
				if (frontIsBlocked()){
					turnRight();
				}
				while (frontIsClear()){
					move();
				}
				turnRight();
		}
			

			//pre:Karel is standing outside the house on a newspaper facing east(6x3).
			//post:Karel is standing outside the house facing west with a newspaper in bag(6x3).
		private void pickNewspaper(){
				pickBeeper();
				turnAround();
		}


			//pre:Karel is standing in the upper left corner of the house(3x4).
			//post:Karel is standing outside the house on a newspaper(6x3).
		private void getOutside() {
			while (frontIsClear()){
				move();
			}
			if (frontIsBlocked()){
				turnRight();
			}
			while (leftIsBlocked()){
				move();
			}
			turnLeft();
			move();	
		}

}
