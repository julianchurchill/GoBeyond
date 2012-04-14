package com.ChewieLouie.GoBeyond;

public class GoGameEndDetector implements GameEndDetector {

	private int maxMovesAllowed;
	private int moveCount = 0;
	private boolean lastMoveWasAPass = false;
	private boolean lastTwoMovesWerePasses = false;
	private Rules rules;

	public GoGameEndDetector(int maxMovesAllowed, Rules rules) {
		this.maxMovesAllowed = maxMovesAllowed;
		this.rules = rules;
	}

	@Override
	public boolean endDetected() {
		return rules.isLegalMoveAvailable() == false || 
				lastTwoMovesWerePasses ||
				moveCount >= maxMovesAllowed;
	}

	@Override
	public void movePlayed(Move move) {
		boolean isPass = Move.passMove( move.colour() ).equals( move );
		lastTwoMovesWerePasses = isPass && lastMoveWasAPass;
		lastMoveWasAPass = isPass;
		moveCount++;
	}

}
