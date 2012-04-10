package com.ChewieLouie.GoBeyond;

public class _TestableGameEndDetector implements GameEndDetector, MoveObserver {

	public int endDetectedCalledCount = 0;
	public int endAfterThisManyMoves = 0;

	private int moveCount = 0;

	@Override
	public boolean endDetected() {
		endDetectedCalledCount++;
		return moveCount >= endAfterThisManyMoves;
	}

	@Override
	public void movePlayed(Move move) {
		moveCount++;
	}	
}
