package com.ChewieLouie.GoBeyond;

public class TestableBoard implements Board {

	public boolean playStoneCalled = false;
	public Point playStonePoint;
	public int playStoneX = 0;
	public int playStoneY = 0;

	@Override
	public Point getContentsOfPoint(int x, int y) {
		return null;
	}

	@Override
	public void playStone(Point p, int x, int y) {
		playStoneCalled = true;
		playStonePoint = p;
		playStoneX = x;
		playStoneY = y;
	}

	@Override
	public void removeStone(int x, int y) {
	}
}
