package com.ChewieLouie.GoBeyond;

public class GoGameEndDetector implements GameEndDetector, MoveObserver {

	private int maxMovesAllowed;
	private int moveCount = 0;
	private boolean lastMoveWasAPass = false;
	private boolean lastTwoMovesWerePasses = false;

	public GoGameEndDetector(int maxMovesAllowed) {
		this.maxMovesAllowed = maxMovesAllowed;
	}

	@Override
	public boolean endDetected() {
		return lastTwoMovesWerePasses || moveCount >= maxMovesAllowed;
	}

	@Override
	public void movePlayed(Move move) {
		boolean isPass = Move.passMove( move.colour() ).equals( move );
		lastTwoMovesWerePasses = isPass && lastMoveWasAPass;
		lastMoveWasAPass = isPass;
		moveCount++;
	}

}
