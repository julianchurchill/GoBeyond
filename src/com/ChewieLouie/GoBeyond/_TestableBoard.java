package com.ChewieLouie.GoBeyond;

public class _TestableBoard implements Board {

	public boolean playStoneCalled = false;
	public Point playStonePoint;
	public int playStoneX = 0;
	public int playStoneY = 0;

	@Override
	public void playStone(Point p, Coord c) {
		playStoneCalled = true;
		playStonePoint = p;
		playStoneX = c.x();
		playStoneY = c.y();
	}

	@Override
	public void removeStone(Coord c) {
	}

	public Point getContentsOfPoint(Coord c) {
		return null;
	}

	@Override
	public Board duplicate() {
		return null;
	}
}
