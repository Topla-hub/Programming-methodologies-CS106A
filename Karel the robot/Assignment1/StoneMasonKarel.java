/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	//In this program, Karel will repair the damages caused by the earthquake.
		public void run(){
			
		repairAllColumns();
			
		}

		
	//pre:karel is standing on 1x1 facing west .
	//post:karel is standing at the end of the world facing west.
	private void repairAllColumns() {
		while (frontIsClear()){
			repairTheColumn();
			goToOtherColumn();
		}
		repairTheColumn();
	}


	//pre:karel is standing on 1x5 facing west.
	//post:karel is standing on 5x5 facing west.	
    private void goToOtherColumn() {
    	for (int i=0; i<4; i++){
    		move();
    	}
	}
    
    
	//pre:karel is standing on 1x1 facing west .
	//post:karel is standing on 1x5 facing west.
	private void repairTheColumn() {
		turnLeft();
		fillTheColmn();
		goDown();
	}

	
	//pre:karel is Standing on top of the column.
	//post:karel is standing at bottom of the column.
	private void goDown() {
		for (int i=0; i<4; i++){
			move();
		}
		turnLeft();
	}

	
	//pre:karel is standing at bottom of the column facing north.
	//post:karel is standing on top of the column facing south.
	private void fillTheColmn() {
		for (int i=0; i<4; i++){
    		if(noBeepersPresent()){
    			putBeeper();
    		}
			move();
    	}
		if(noBeepersPresent()){
			putBeeper();
		}
		turnAround();
	}
	

}
