package com.ChewieLouie.GoBeyond;

public class _TestableBoardObserver implements BoardObserver {

	public boolean boardChangedCalled = false;

	@Override
	public void boardChanged() {
		boardChangedCalled = true;
	}

}
