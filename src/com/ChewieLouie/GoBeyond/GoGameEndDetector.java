package com.ChewieLouie.GoBeyond;

public class GoGameEndDetector implements GameEndDetector, MoveObserver {

	private int maxMovesAllowed;
	private int moveCount = 0;

	public GoGameEndDetector(int maxMovesAllowed) {
		this.maxMovesAllowed = maxMovesAllowed;
	}

	@Override
	public boolean endDetected() {
		return moveCount  >= maxMovesAllowed;
	}

	@Override
	public void movePlayed(Move move) {
		moveCount++;
	}

}
