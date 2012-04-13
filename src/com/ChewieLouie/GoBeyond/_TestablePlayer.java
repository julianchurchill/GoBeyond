package com.ChewieLouie.GoBeyond;

public class _TestablePlayer implements Player {

	public int generateMoveCalledCount = 0;

	@Override
	public void playMove() {
		generateMoveCalledCount++;
	}

}
