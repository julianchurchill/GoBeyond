package com.ChewieLouie.GoBeyond;

public class _TestableRules implements Rules {

	public boolean isLegalCalled = false;
	public Move isLegalCalledWithMove;
	public boolean isLegalReturnValue = true;
	public boolean isLegalMoveAvailableCalled = false;
	public boolean isLegalMoveAvailableReturnValue = true;
	public int endDetectedCalledCount = 0;
	public boolean endDetectedReturnValue = false;
	public GameHistory endDetectedHistory;
	public GameHistory isLegalCalledWithHistory;
	public Board isLegalCalledWithBoard;

	@Override
	public boolean isLegal( Move m, Board b, GameHistory history ) {
		isLegalCalled = true;
		isLegalCalledWithMove = m;
		isLegalCalledWithBoard = b;
		isLegalCalledWithHistory = history;
		return isLegalReturnValue;
	}
	
	public boolean endDetected(GameHistory history) {
		endDetectedCalledCount++;
		endDetectedHistory = history;
		return endDetectedReturnValue;
	}
}
