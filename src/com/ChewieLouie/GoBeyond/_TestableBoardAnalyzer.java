package com.ChewieLouie.GoBeyond;

public class _TestableBoardAnalyzer implements BoardAnalyzer {

	public boolean isStringAliveCalled = false;

	@Override
	public boolean isStringAlive(Board b, Coord c) {
		isStringAliveCalled  = true;
		return false;
	}

}
