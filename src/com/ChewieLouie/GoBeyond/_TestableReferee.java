package com.ChewieLouie.GoBeyond;

public class _TestableReferee implements Referee {

	public boolean submitMoveCalled = false;
	public Move submitMoveArg;

	@Override
	public MoveStatus submitMove(Move m) {
		submitMoveCalled = true;
		submitMoveArg = m;
		return null;
	}

}
