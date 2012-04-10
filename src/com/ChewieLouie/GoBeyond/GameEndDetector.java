package com.ChewieLouie.GoBeyond;

public class GameEndDetector {

	public int endDetectedCalledCount = 0;

	private int endAfterThisManyMoves = 0;
	private int moveCount = 0;

	public boolean endDetected() {
		endDetectedCalledCount++;
		return moveCount >= endAfterThisManyMoves;
	}

	public void addMove() {
		moveCount++;
	}
	
	public void setMaximumMovesBeforeEnd( int m ) {
		endAfterThisManyMoves = m;
	}
}
