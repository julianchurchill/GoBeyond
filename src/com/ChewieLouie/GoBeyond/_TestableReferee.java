package com.ChewieLouie.GoBeyond;

public class _TestableReferee implements Referee {

	public boolean submitMoveCalled = false;
	public Move submitMoveArg;
	public int endAfterThisManyGameEndDetections = 0;
	public int endDetectedCalledCount = 0;
	public Board boardReturn;
	public boolean isLegalCalled = false;
	public Move isLegalCalledWithMove;
	public Board isLegalCalledWithBoard;
	public GameHistory isLegalCalledWithHistory;
	public boolean isLegalReturn = false;

	@Override
	public MoveStatus submitMove(Move m) {
		submitMoveCalled = true;
		submitMoveArg = m;
		return null;
	}

	@Override
	public boolean endDetected() {
		endDetectedCalledCount++;
		return endDetectedCalledCount >= endAfterThisManyGameEndDetections;
	}

	@Override
	public Board board() {
		return boardReturn;
	}

	@Override
	public boolean isLegal(Move move, Board board, GameHistory history) {
		isLegalCalled = true;
		isLegalCalledWithMove = move;
		isLegalCalledWithBoard = board;
		isLegalCalledWithHistory = history;
		return isLegalReturn;
	}

}
