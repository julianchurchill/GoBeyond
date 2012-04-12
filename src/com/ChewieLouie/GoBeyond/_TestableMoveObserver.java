package com.ChewieLouie.GoBeyond;

public class _TestableMoveObserver implements MoveObserver {

	public boolean movePlayedCalled = false;
	public Move movePlayedMove = null;

	@Override
	public void movePlayed(Move move) {
		movePlayedCalled = true;
		movePlayedMove = move;
	}

}
