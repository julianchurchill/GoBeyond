package com.ChewieLouie.GoBeyond;

public class _TestableRefereeMoveObserver implements RefereeMoveObserver {

	public boolean moveAcceptedCalled = false;
	public Move moveAcceptedCalledWithMove;
	public Board moveAcceptedCalledWithBoard;

	@Override
	public void moveAccepted( Move m, Board b ) {
		moveAcceptedCalled  = true;
		moveAcceptedCalledWithMove = m;
		moveAcceptedCalledWithBoard = b;
	}
}
