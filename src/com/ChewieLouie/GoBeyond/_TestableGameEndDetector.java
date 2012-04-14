package com.ChewieLouie.GoBeyond;

public class _TestableGameEndDetector implements GameEndDetector {

	public int endDetectedCalledCount = 0;
	public boolean endDetectedReturnValue = false;

	@Override
	public boolean endDetected() {
		endDetectedCalledCount++;
		return endDetectedReturnValue;
	}

	@Override
	public void movePlayed(Move move) {
	}	
}
