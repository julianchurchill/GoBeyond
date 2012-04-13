package com.ChewieLouie.GoBeyond;

public class _TestableReferee implements Referee {

	public boolean submitMoveCalled = false;
	public Move submitMoveArg;
	public int endAfterThisManyGameEndDetections = 0;
	public int endDetectedCalledCount = 0;

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

}
