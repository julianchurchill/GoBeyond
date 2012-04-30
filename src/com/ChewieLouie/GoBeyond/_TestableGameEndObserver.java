package com.ChewieLouie.GoBeyond;

public class _TestableGameEndObserver implements GameEndObserver {

	public boolean gameEndedCalled = false;

	@Override
	public void gameEnded() {
		gameEndedCalled  = true;
	}
}
