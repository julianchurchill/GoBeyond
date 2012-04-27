package com.ChewieLouie.GoBeyond;

public class _TestableGameBrowserObserver implements GameBrowserObserver {

	public boolean positionChangedCalled = false;
	
	@Override
	public void browserPositionChanged() {
		positionChangedCalled = true;
	}

}
